package com.daxiong.demo.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.daxiong.demo.dao.AdminDAO;
import com.daxiong.demo.entity.Admin;
import com.daxiong.demo.service.AdminService;
import com.daxiong.demo.service.impl.AdminServiceImpl;

/**
 * Servlet implementation class AdminServlet
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Admin admin = adminService.login(username, password);
		if(admin!=null) {
			//登录成功
			session.setAttribute("currAdmin", admin);
			//查询书籍
			response.sendRedirect(request.getContextPath()+"/book/index");
		}else {
			response.sendRedirect(request.getContextPath()+"/login.jsp");
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
