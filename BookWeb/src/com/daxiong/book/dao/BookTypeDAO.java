package com.daxiong.book.dao;

import java.util.List;

import com.daxiong.book.entity.BookType;

public interface BookTypeDAO {
	//查询所有书籍类型
	List<BookType> findAll();

}
