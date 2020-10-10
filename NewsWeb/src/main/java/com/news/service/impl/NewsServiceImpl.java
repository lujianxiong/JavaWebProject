package com.news.service.impl;

import java.util.List;

import com.news.dao.NewsDAO;
import com.news.entity.News;
import com.news.service.NewsService;
import com.news.util.MyBatisUtil;

public class NewsServiceImpl implements NewsService{
	private NewsDAO newsDAO = MyBatisUtil.getSqlSession().getMapper(NewsDAO.class);
	
	@Override
	public void addNews(String title,String content) {
		
		System.out.println(newsDAO.addNews(title,content)>0?"添加成功！":"添加失败！");
	}

	@Override
	public List<News> findAll() {
		return newsDAO.findAll();
	}

	
	@Override
	public void deleteById(int id) {
		System.out.println(newsDAO.delete(id)>0?"删除成功!":"删除失败！");
	}

	@Override
	public News find(int id) {
		return newsDAO.findById(id);
	}

	@Override
	public void update(News news) {
		System.out.println(newsDAO.update(news)>0?"修改成功！":"修改失败");
	}

	@Override
	public List<News> search(String title) {
		return newsDAO.searchByTitle(title);
	}

}
