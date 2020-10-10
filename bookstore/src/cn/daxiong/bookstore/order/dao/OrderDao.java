package cn.daxiong.bookstore.order.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.daxiong.bookstore.book.domain.Book;
import cn.daxiong.bookstore.order.domain.Order;
import cn.daxiong.bookstore.order.domain.OrderItem;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;

public class OrderDao {
	private QueryRunner qr = new TxQueryRunner();
	
	// 添加订单
	public void addOrder(Order order) {
		try {
			String sql = "INSERT INTO orders values(?,?,?,?,?,?)";
			Timestamp timestamp = new Timestamp(order.getOrdertime().getTime());
			Object[] params = {order.getOid(),timestamp,
							   order.getTotal(),order.getState(),
							   order.getOwner().getUid(),order.getAddress()};
			qr.update(sql,params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	// 添加订单条目
	public void addOrderItemList(List<OrderItem> orderItemList) {
		try {
			// QueryRunner类的batch(String sql,Object[][] params)方法
			// 其中params是多个一维数组，每个一维数组都与sql在一起执行一次，多个一维数组就执行多次
			String sql = "INSERT INTO orderitem values(?,?,?,?,?)";
			// 将orderItemList转换成二维数组（即把一个OrderItem对象转换成一个一维数组）
			Object[][] params = new Object[orderItemList.size()][];  // 第一个参数是指定二维数组中一维数组的个数，第二个参数是指定一维数组中的元素个数。
			// 循环遍历orderItemList,将List中的每个orderItem对象为params中的每个一维数组赋值
			for (int i = 0; i < orderItemList.size(); i++) {
				OrderItem item = orderItemList.get(i);
				params[i] = new Object[] {item.getIid(),item.getCount(),item.getSubtotal(),
						item.getOrder().getOid(),item.getBook().getBid()};
			}
			qr.batch(sql, params);  // 执行批处理
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	// 按uid查询所有订单（我的订单）
	public List<Order> findByUid(String uid) {
		try {
			// 1、通过uid查询当前用户的所有订单List<Order>
			String sql = "SELECT * FROM orders WHERE uid=?";
			List<Order> orderList = qr.query(sql, new BeanListHandler<Order>(Order.class),uid);
			// 2、循环遍历每个Order，为其加载它的所有的OrderItem
			for (Order order : orderList) {
				loadOrderItems(order);  //loadOrderItems方法为Order对象添加它的所有订单条目
			}
			// 3、返回订单列表
			return orderList;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	
	// 加载指定的订单的所有订单条目
	private void loadOrderItems(Order order) throws Exception {
		// 查询的orderitem表中没有图书的信息，因此需要使用多表查询，并且要去笛卡尔积
		// 查询两张表：orderitem、book
		String sql = "SELECT * FROM orderitem i,book b WHERE i.bid=b.bid AND oid=?";
		// 因为一行结果集对应的不再是一个JavaBean，所以不能使用BeanListHandler，而是MapListhandler
		List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler(),order.getOid());
		// mapList是多个map，每个map对应一个行结果集
		// 一个map对应一行：{iid=32264A5875484A42952474AB38F75551,count=3,subtotal=90.00,oid=4F11542D7CBE4645AA7AE9037E3B2C84,bid-7,bname=精通Hibernate,price=30.0,author=孙卫琴,image=book_img/8991366-1_l.jpg,cid=2}
		
		//我们需要使用一个map生成两个对象：OrderItem、Book，然后在建立两者的关系（把Book设置给OrderItem）
		// 循环遍历每个Map，使用map生成两个对象，然后建立关系（最后的结果是一个OrderItem，OrderItem中有Book），把OrderItem保存起来
		List<OrderItem> orderItemList = toOrderItemList(mapList);  //这个方法将mapList转化成OrderItemList
		
		order.setOrderItemList(orderItemList);
	}

	// 把mapList中每行Map转换成每一个OrderItem，（将一大堆的map变成一大堆的OrderItem）
	private List<OrderItem> toOrderItemList(List<Map<String, Object>> mapList) {
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		for (Map<String, Object> map : mapList) {
			OrderItem item = toOrderItem(map);
			orderItemList.add(item);
		}
		return orderItemList;
	}

	// 把一个map转换成一个OrderItem对象
	private OrderItem toOrderItem(Map<String, Object> map) {
		// 将一个map变成两个对象，并建立关系，然后返回
		OrderItem orderItem = CommonUtils.toBean(map, OrderItem.class);
		Book book = CommonUtils.toBean(map, Book.class);
		orderItem.setBook(book);
		return orderItem;
	}


	// 加载一个订单
	public Order load(String oid) {
		try {
			// 1、通过uid查询当前用户的所有订单List<Order>
			String sql = "SELECT * FROM orders WHERE oid=?";
			Order order = qr.query(sql, new BeanHandler<Order>(Order.class),oid);
			// 2、加载订单的所有订单条目
			loadOrderItems(order);
			// 3、返回订单列表
			return order;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	// 通过oid查询订单状态
	public int getStateByOid(String oid) {
		try {
			String sql = "SELECT state FROM orders WHERE oid=?";
			Number num = (Number)qr.query(sql, new ScalarHandler(),oid);
			return num.intValue();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	

	// 修改订单状态
	public void updateState(String oid,int state) {
		try {
			String sql = "Update orders SET state=? WHERE oid=?";
			qr.update(sql,state,oid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
}
