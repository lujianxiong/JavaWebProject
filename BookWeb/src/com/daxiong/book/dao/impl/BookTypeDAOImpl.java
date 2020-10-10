package com.daxiong.book.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.daxiong.book.dao.BookTypeDAO;
import com.daxiong.book.entity.BookType;
import com.daxiong.book.util.JDBCUtil;

public class BookTypeDAOImpl implements BookTypeDAO{

	@Override
	public List<BookType> findAll() {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<BookType> list = new ArrayList<>();
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select * from booktype";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(1);
				String typeName = rs.getString(2);
				BookType bookType = new BookType(id,typeName);
				list.add(bookType);
			}
			return list;
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
