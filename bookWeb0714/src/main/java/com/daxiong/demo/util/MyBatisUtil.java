package com.daxiong.demo.util;

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
		//mybatis默认情况下事务的自动提交是关闭的，我们设置为true之后，自动提交才生效
		//当insert update delete 这些语句是涉及到事务的管理，如果没有提交事务，那么数据库中的记录更新会失败的！（虽然它提示成功了，但其实失败了）
		return getSqlSessionFactory().openSession(true);
	}

}
