package com.daxiong.book.dao;

import java.util.List;

import com.daxiong.book.entity.Order;

public interface OrderDAO {
	/**
	 * 新增订单
	 * @param order  订单
	 * @return     //新增订单的id值
	 */
	int InsertOrder(Order order);
	
	/**
	 * 根据用户来查询所有订单信息
	 * @param uid
	 * @return
	 */
	List<Order> selectOrdersByUser(int uid);

}
