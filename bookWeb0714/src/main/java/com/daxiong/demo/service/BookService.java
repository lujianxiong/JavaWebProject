package com.daxiong.demo.service;

import java.util.List;

import com.daxiong.demo.entity.Book;

public interface BookService {
	List<Book> findAll();

	List<Book> search(String word);

	void del(int id);
	
	void addBook(Book book);

	Book find(int id);

	void update(Book book);

}
