package com.daxiong.demo.service.impl;

import com.daxiong.demo.dao.AdminDAO;
import com.daxiong.demo.entity.Admin;
import com.daxiong.demo.service.AdminService;
import com.daxiong.demo.util.MyBatisUtil;

public class AdminServiceImpl implements AdminService{
	private AdminDAO adminDAO = MyBatisUtil.getSqlSession().getMapper(AdminDAO.class);

	@Override
	public Admin login(String username, String password) {
		return adminDAO.selectAdmin(username,password);
	}
	
	

}
