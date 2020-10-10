package cn.daxiong.bookstore.book.service;

import java.util.List;

import cn.daxiong.bookstore.book.dao.BookDao;
import cn.daxiong.bookstore.book.domain.Book;

public class BookService {
	private BookDao bookDao = new BookDao();
	
	// 查询所有图书
	public List<Book> findAll(){
		return bookDao.findAll();
	}

	// 按cid进行分类查询
	public List<Book> findByCategory(String cid) {
		return bookDao.findByCategory(cid);
	}

	public Book load(String bid) {
		return bookDao.findByBid(bid);
	}
	

}
