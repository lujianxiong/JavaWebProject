package com.daxiong.book.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.daxiong.book.dao.OrderDAO;
import com.daxiong.book.entity.Book;
import com.daxiong.book.entity.Order;
import com.daxiong.book.util.JDBCUtil;
import com.mysql.jdbc.Statement;
import com.sun.xml.internal.bind.v2.model.core.ID;

public class OrderDAOImpl implements OrderDAO{

	@Override    
	public int InsertOrder(Order order) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int id = 0;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "insert into orders(oid,uid,price,otime) values(?,?,?,?)"; 
			psmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);  //在这里指明要返回的主键
			psmt.setString(1, order.getoid());
			psmt.setInt(2, order.getUid());
			psmt.setDouble(3, order.getPrice());
			psmt.setString(4, order.getOtime());
			psmt.executeUpdate();   //执行更新操作
			rs = psmt.getGeneratedKeys();   //获取主键
			if(rs.next()) {
				id = rs.getInt(1);
			}
			return id;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JDBCUtil.close(rs, psmt, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public List<Order> selectOrdersByUser(int uid) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Order> orders = new ArrayList<>();
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select * from orders where uid=?"; 
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, uid);
			rs = psmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(1);
				String oid = rs.getString(2);
				int uid1 = rs.getInt(3);
				double price = rs.getDouble(4);
				String otime = rs.getString(5);
				Order order = new Order(id,oid,uid1,price,otime);
				orders.add(order);
			}
			return orders;
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
