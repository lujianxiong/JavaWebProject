package com.news.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.news.entity.News;

public interface NewsDAO {
	int addNews(@Param("title")String title,@Param("content")String content);
	
	List<News> findAll();
	
	int delete(int id);

	News findById(int id);

	int update(News news);

	List<News> searchByTitle(String title);
	
}
