package com.daxiong.book.dao;

import com.daxiong.book.entity.User;

public interface UserDAO {
	//登录功能
	User login(String username,String password);

}
