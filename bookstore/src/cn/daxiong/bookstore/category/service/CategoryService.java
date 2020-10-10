package cn.daxiong.bookstore.category.service;

import java.util.List;

import cn.daxiong.bookstore.category.dao.CategoryDao;
import cn.daxiong.bookstore.category.domain.Category;

public class CategoryService {
	private CategoryDao categoryDao = new CategoryDao();
	
	// 查询所有分类
	public List<Category> findAll(){
		return categoryDao.findAll();  //一句调用
	}
	
}
