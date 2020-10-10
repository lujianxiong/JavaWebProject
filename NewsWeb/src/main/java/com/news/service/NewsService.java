package com.news.service;

import java.util.List;

import com.news.entity.News;

public interface NewsService {
	void addNews(String title,String content);

	List<News> findAll();

	void deleteById(int id);

	News find(int id);

	void update(News news);

	List<News> search(String title);

}
