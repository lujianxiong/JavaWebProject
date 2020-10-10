package com.news.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	
	public static SqlSessionFactory getSqlSessionFactory() {
		try {
			String resource = "SqlMapConfig.xml";
			InputStream is = Resources.getResourceAsStream(resource);
			return new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static SqlSession getSqlSession() {
		return getSqlSessionFactory().openSession(true);
	}

}
