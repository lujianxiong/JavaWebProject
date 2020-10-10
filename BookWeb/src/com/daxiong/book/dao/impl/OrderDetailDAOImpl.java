package com.daxiong.book.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.daxiong.book.dao.OrderDetailDAO;
import com.daxiong.book.entity.OrderDetail;
import com.daxiong.book.util.JDBCUtil;

public class OrderDetailDAOImpl implements OrderDetailDAO{

	@Override
	public int InsertOrderDetail(OrderDetail orderDetail) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "insert into orderdetail(oid,bid) values(?,?)"; 
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, orderDetail.getOid());
			psmt.setInt(2, orderDetail.getBid());
			return psmt.executeUpdate();
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

}
