package cn.daxiong.web.servlet;
//UserServlet层
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.daxiong.domain.User;
import cn.daxiong.service.UserService;
import cn.itcast.commons.CommonUtils;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");  //处理请求编码（post）
		response.setContentType("text/html;charset=utf-8");  //处理响应编码
		
		UserService userService = new UserService();  //依赖UserService
		
		//1、封装表单数据到User中
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		//2、调用service的login()方法，得到返回的User对象
		try {
			//如果没有异常：保存返回值到session中，重定向到welcome.jsp中
			User user = userService.login(form);
			request.getSession().setAttribute("sessionUser", user);
			response.sendRedirect(request.getContextPath()+"/user/welcome.jsp");
		} catch (Exception e) {
			//如果抛出异常：保存异常信息，保存到request域，转发到login.jsp
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("user", form);
			request.getRequestDispatcher("/user/login.jsp").forward(request, response);
		}
	}

}
