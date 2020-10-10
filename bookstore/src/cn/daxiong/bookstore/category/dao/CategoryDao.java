package cn.daxiong.bookstore.category.dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.daxiong.bookstore.category.domain.Category;
import cn.itcast.jdbc.TxQueryRunner;

public class CategoryDao {
	private QueryRunner qr = new TxQueryRunner();

	//查询所有分类
	public List<Category> findAll() {
		try {
			String sql = "SELECT * FROM category";
			return qr.query(sql, new BeanListHandler<Category>(Category.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	

}
