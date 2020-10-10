package com.daxiong.demo.dao;

import java.util.List;

import com.daxiong.demo.entity.Book;

public interface BookDAO {
	List<Book> findAll();

	List<Book> selectByBname(String word);

	int delete(int id);
	
	int insertBook(Book book);

	Book find(int id);

	int updateBook(Book book);

}
