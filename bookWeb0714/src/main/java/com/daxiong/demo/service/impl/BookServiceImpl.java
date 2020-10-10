package com.daxiong.demo.service.impl;

import java.util.List;

import com.daxiong.demo.dao.BookDAO;
import com.daxiong.demo.entity.Book;
import com.daxiong.demo.service.BookService;
import com.daxiong.demo.util.MyBatisUtil;

public class BookServiceImpl implements BookService{
	private BookDAO bookDAO = MyBatisUtil.getSqlSession().getMapper(BookDAO.class);
	
	@Override
	public List<Book> findAll() {
		return bookDAO.findAll();
	}

	@Override
	public List<Book> search(String word) {
		return bookDAO.selectByBname(word);
	}

	@Override
	public void del(int id) {
		System.out.println(bookDAO.delete(id)>0?"删除成功":"删除失败");
	}

	@Override
	public void addBook(Book book) {
		System.out.println(bookDAO.insertBook(book)>0?"添加成功":"添加失败");
	}

	@Override
	public Book find(int id) {
		return bookDAO.find(id);
	}

	@Override
	public void update(Book book) {
		System.out.println(bookDAO.updateBook(book)>0?"修改成功":"修改失败");
		
	}

}
