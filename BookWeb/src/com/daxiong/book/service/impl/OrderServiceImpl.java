package com.daxiong.book.service.impl;
import java.util.ArrayList;
//业务层： 完成复杂的业务逻辑，事务管理
import java.util.List;

import com.daxiong.book.dao.OrderDAO;
import com.daxiong.book.dao.OrderDetailDAO;
import com.daxiong.book.dao.impl.OrderDAOImpl;
import com.daxiong.book.dao.impl.OrderDetailDAOImpl;
import com.daxiong.book.entity.Book;
import com.daxiong.book.entity.Order;
import com.daxiong.book.entity.OrderDetail;
import com.daxiong.book.service.OrderService;

public class OrderServiceImpl implements OrderService{
	private OrderDAO orderDAO = new OrderDAOImpl();
	private OrderDetailDAO detailDAO = new OrderDetailDAOImpl();
	
	@Override
	public void addOrder(Order order, List<Book> books) {
		//生成订单
		int oid = orderDAO.InsertOrder(order);  //返回的是主键
		//生成对应的订单详情
		List<OrderDetail> details = new ArrayList<OrderDetail>();
		//第一层循环：把书籍信息构成订单详情列表
		for(Book book: books) {
			OrderDetail detail = new OrderDetail(0,oid,book.getId());  //第一个是主键，我们不管，oid是上面返回的，bid是书的id
			details.add(detail);
		}
		//第二层循环：将详情列表加入到数据库中
		for (OrderDetail detail : details) {
			detailDAO.InsertOrderDetail(detail);
		}
		
		
	}

}
