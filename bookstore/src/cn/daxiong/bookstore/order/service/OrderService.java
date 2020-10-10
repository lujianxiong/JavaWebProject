package cn.daxiong.bookstore.order.service;

import java.sql.SQLException;
import java.util.List;

import cn.daxiong.bookstore.order.dao.OrderDao;
import cn.daxiong.bookstore.order.domain.Order;
import cn.itcast.jdbc.JdbcUtils;

public class OrderService {
	private OrderDao orderDao = new OrderDao();
	
	// 添加订单（需要处理事务）
	public void add(Order order) {
		try {
			// 开启事务
			JdbcUtils.beginTransaction();
			
			orderDao.addOrder(order);  // 插入订单
			orderDao.addOrderItemList(order.getOrderItemList());  // 插入订单中的所有条目
			
			// 提交事务
			JdbcUtils.commitTransaction();
		} catch (Exception e) {
			// 回滚事务
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
			}
			throw new RuntimeException(e);
		}
	}

	
	// 我的订单
	public List<Order> myOrders(String uid) {
		return orderDao.findByUid(uid);
	}

	
	// 加载订单
	public Order load(String oid) {
		return orderDao.load(oid);
	}
	
	
	// 确认收货
	public void confirm(String oid) throws OrderException{
		// 1、校验订单状态，如果不是3，则抛出异常
		int state = orderDao.getStateByOid(oid);
		if (state != 3) throw new OrderException("订单确认失败，你不是什么好东西！");
		// 2、修改订单状态为4，表示交易成功！
		orderDao.updateState(oid, 4);
	}
	
}
