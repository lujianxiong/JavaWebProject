package cn.daxiong.bookstore.cart.dao;

import org.apache.commons.dbutils.QueryRunner;

import cn.itcast.jdbc.TxQueryRunner;

public class CartDao {
private QueryRunner qr = new TxQueryRunner();
	
	// 修改库存
	public void updateStock(String bid,int stock) {
		try {
			String sql = "Update book SET stock=? WHERE bid=?";
			qr.update(sql,stock,bid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
