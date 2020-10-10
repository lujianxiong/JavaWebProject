package cn.daxiong.bookstore.book.dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.daxiong.bookstore.book.domain.Book;
import cn.itcast.jdbc.TxQueryRunner;

public class BookDao {
	private QueryRunner qr = new TxQueryRunner();
	
	// 查询所有图书
	public List<Book> findAll(){
		try {
			String sql = "SELECT * FROM book";
			return qr.query(sql, new BeanListHandler<Book>(Book.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// 按cid进行分类查询
	public List<Book> findByCategory(String cid) {
		try {
			String sql = "SELECT * FROM book WHERE cid=?";
			return qr.query(sql, new BeanListHandler<Book>(Book.class),cid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// 按bid查询图书详细信息
	public Book findByBid(String bid) {
		try {
			String sql = "SELECT * FROM book WHERE bid=?";
			return qr.query(sql, new BeanHandler<Book>(Book.class),bid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
}
