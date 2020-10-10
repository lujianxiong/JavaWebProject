package com.news.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.news.entity.Admin;
import com.news.service.AdminService;
import com.news.service.impl.AdminServiceImpl;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminServiceImpl();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Admin admin = adminService.login(username, password);
		
		if(admin!=null) {  //登录成功
			session.setAttribute("currAdmin", admin);
			out.print("<script>alert('Login successful ！！');window.location='index.jsp';</script>");
			out.flush();
			out.close();
			
		}else {
			out.print("<script>alert('你怎么这么笨！连密码都记不住！！');window.location='login.jsp';</script>");
			out.flush();
			out.close();
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
