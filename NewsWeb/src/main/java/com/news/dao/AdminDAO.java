package com.news.dao;
//MyBatis
import org.apache.ibatis.annotations.Param;
import com.news.entity.Admin;

public interface AdminDAO {
	
	Admin selectAdmin(@Param("user")String username,@Param("pwd") String password);

}
