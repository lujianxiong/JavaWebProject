package com.daxiong.book.service;

import java.util.List;

import com.daxiong.book.entity.Book;
import com.daxiong.book.entity.Order;

public interface OrderService {
	//下单
	void addOrder(Order order,List<Book> books);
	

}
