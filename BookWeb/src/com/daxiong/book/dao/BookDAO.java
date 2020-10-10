package com.daxiong.book.dao;

import java.util.List;
import com.daxiong.book.entity.Book;

public interface BookDAO {
	List<Book> findBookByType(int typeId);

	List<Book> findBookByName(String bookname);
	
	Book findBook(int bid);
}
