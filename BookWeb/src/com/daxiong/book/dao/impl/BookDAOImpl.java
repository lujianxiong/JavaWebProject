package com.daxiong.book.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.daxiong.book.dao.BookDAO;
import com.daxiong.book.entity.Book;
import com.daxiong.book.entity.BookType;
import com.daxiong.book.util.JDBCUtil;

public class BookDAOImpl implements BookDAO{

	@Override
	public List<Book> findBookByType(int typeId) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Book> books = new ArrayList<>();
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select id,bname,author,price,press from book where typeid=?";  //这里的typeid字段是数据库中的字段名，一定要正确地对应上
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, typeId);
			rs = psmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(1);
				String bname = rs.getString(2);
				String author = rs.getString(3);
				double price = rs.getDouble(4);    //数据库中我们对price字段用的是Decimal，我们这里用double
				String press = rs.getString(5);
				Book book = new Book(id,bname,author,price,press);
				books.add(book);
			}
			return books;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JDBCUtil.close(rs, psmt, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<Book> findBookByName(String bookname) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Book> books = new ArrayList<>();
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select id,bname,author,price,press from book where bname like ?";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1,"%"+bookname+"%");
			rs = psmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(1);
				String bname = rs.getString(2);
				String author = rs.getString(3);
				double price = rs.getDouble(4);    //数据库中我们对price字段用的是Decimal，我们这里用double
				String press = rs.getString(5);
				Book book = new Book(id,bname,author,price,press);
				books.add(book);
			}
			return books;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JDBCUtil.close(rs, psmt, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Book findBook(int bid) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Book book = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select id,bname,author,price,press from book where id=?";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, bid);
			rs = psmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(1);
				String bname = rs.getString(2);
				String author = rs.getString(3);
				double price = rs.getDouble(4);    //数据库中我们对price字段用的是Decimal，我们这里用double
				String press = rs.getString(5);
			    book = new Book(id,bname,author,price,press);
			}
			return book;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JDBCUtil.close(rs, psmt, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
