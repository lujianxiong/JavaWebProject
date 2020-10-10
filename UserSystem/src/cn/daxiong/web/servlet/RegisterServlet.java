package cn.daxiong.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.daxiong.domain.User;
import cn.daxiong.service.UserException;
import cn.daxiong.service.UserService;
import cn.itcast.commons.CommonUtils;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");  //处理请求编码（post）
		response.setContentType("text/html;charset=utf-8");  //处理响应编码
		
		UserService userService = new UserService();  //依赖UserService
		//封装表单数据（封装到JavaBean User类中）  【使用的是itcast包下的方法】
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		
	//一、校验表单
		//1、创建一个Map，用来装载所有的表单错误信息
		Map<String, String> errors = new HashMap<String, String>();
		//2、对表单数据进行校验，失败则向map中添加错误信息，其中key与表单的字段名称相对应
		//	对用户名进行校验
		String username = form.getUsername();  //获取表单的username
		if (username == null || username.trim().isEmpty()) {
			errors.put("username", "用户名不能为空！");  
		}else if (username.length()<3 || username.length()>10) {
			errors.put("username", "用户名长度必须在3～10之间！");  
		}
		//	对密码进行校验
		String password = form.getPassword();
		if (password == null || password.trim().isEmpty()) {
			errors.put("password", "密码不能为空！");  
		}else if (password.length()<6 || password.length()>12) {
			errors.put("password", "密码长度必须在6～12之间！");  
		}
		//	对验证码进行校验
		String verifyCode = form.getVerifyCode();
		String sessionVerifyCode = (String) request.getSession().getAttribute("session_vcode");
		if (verifyCode == null || verifyCode.trim().isEmpty()) {
			errors.put("verifyCode", "验证码不能为空！");  
		}else if (verifyCode.length() != 4) {
			errors.put("verifyCode", "验证码长度必须为4！");  
		}else if (!verifyCode.equalsIgnoreCase(sessionVerifyCode)) {
			errors.put("verifyCode", "验证码错误！"); 
		}
		//3、判断map是否为空，map不为空说明有错误信息;map为空，说明没有错误信息，向下执行！
		//保存map到request到，保存form到request域中，转发到regist.jsp页面
		if (errors != null && errors.size()>0) {
			//	保存errors到request域
			request.setAttribute("errors", errors);
			//	保存form到request域  【为了表单中回显】
			request.setAttribute("user", form);
			//	转发到regist.jsp页面
			request.getRequestDispatcher("/user/regist.jsp").forward(request, response);
			return;
		}
		
	//二、校验用户名重名
		try {
			//调用UserService的register()方法，传参form
			userService.register(form);
			//没有异常：输出注册成功！
			response.getWriter().print("<h1>注册成功！！</h1><a href='" + 
					request.getContextPath() + 
					"/user/login.jsp" + "'>点击这里，开始登录</a>");
		} catch (UserException e) {
			//获取异常信息，保存到request域
			request.setAttribute("msg", e.getMessage());
			//保存表单数据到request域   【在表单中回显】
			request.setAttribute("user", form);
			//转发到regist.jsp      【转发不用带项目名！！】
			request.getRequestDispatcher("/user/regist.jsp").forward(request, response);;
		}
	}

}
