package com.news.service.impl;

import com.news.dao.AdminDAO;
import com.news.entity.Admin;
import com.news.service.AdminService;
import com.news.util.MyBatisUtil;

public class AdminServiceImpl implements AdminService{
	private AdminDAO adminDAO = MyBatisUtil.getSqlSession().getMapper(AdminDAO.class);

	@Override
	public Admin login(String username, String password) {
		return adminDAO.selectAdmin(username,password);
	}

}
