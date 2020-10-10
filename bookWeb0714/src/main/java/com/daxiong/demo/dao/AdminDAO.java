package com.daxiong.demo.dao;
//MyBatis

import org.apache.ibatis.annotations.Param;

import com.daxiong.demo.entity.Admin;

public interface AdminDAO {
	//登录
	/**
	 * 
	 * @param admin  登录时输入的帐号，密码，构造的Admin对象
	 * @return
	 */
	//Admin selectAdmin(Admin admin);
	
	//使用param注解，给形参起别名
	Admin selectAdmin(@Param("user")String username,@Param("pwd") String password);

}
