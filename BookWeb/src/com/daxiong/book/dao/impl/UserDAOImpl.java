package com.daxiong.book.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.daxiong.book.dao.UserDAO;
import com.daxiong.book.entity.User;
import com.daxiong.book.util.JDBCUtil;

public class UserDAOImpl implements UserDAO{

	@Override
	public User login(String username, String password) {
		Connection conn =null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		User currUser = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select * from user where username=? and password=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, username);
			psmt.setString(2, password);
			rs = psmt.executeQuery();
			//密码是一次的，不用while，if就可以了
			if(rs.next()) {
				int id = rs.getInt(1);
				String user = rs.getString(2);
				String pwd = rs.getString(3);
				currUser = new User(id,user,pwd);
			}
			return currUser;
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
