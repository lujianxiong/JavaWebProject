package com.daxiong.book.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.daxiong.book.dao.OrderDAO;
import com.daxiong.book.dao.impl.OrderDAOImpl;
import com.daxiong.book.entity.Order;
import com.daxiong.book.entity.User;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/order/*")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDAO orderDAO = new OrderDAOImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathinfo = request.getPathInfo();
		HttpSession session = request.getSession();
		
		//根据用户查询订单信息
		if ("/selectByUser".equals(pathinfo)) {  
			//获取当前用户
			User currUser = (User)session.getAttribute("currUser");
			//根据用户id查询历史订单
			List<Order> orders = orderDAO.selectOrdersByUser(currUser.getId());
			session.setAttribute("orders", orders);
			//跳转到历史订单页
			response.sendRedirect(request.getContextPath()+"/orders.jsp");
		}
		
		//根据订单编号查询书籍信息
		if ("/selectByOid".equals(pathinfo)) {
			//获取当前订单编号
			session.getAttribute("orders");
			//根据订单编号查询书籍信息
			
			//跳转到books.jsp
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
