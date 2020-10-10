package cn.daxiong.bookstore.user.web.servlet;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.daxiong.bookstore.cart.domain.Cart;
import cn.daxiong.bookstore.user.domain.User;
import cn.daxiong.bookstore.user.service.UserService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
//User web层
import cn.itcast.servlet.BaseServlet;

@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();

	// 注册功能
	public String regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1、封装表单数据到form对象中
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);

		// 2、补全uid，code
		form.setUid(CommonUtils.uuid());
		form.setCode(CommonUtils.uuid() + CommonUtils.uuid());

		// 3、输入校验（保存错误信息、form到request域，转发到regist.jsp）
		// 1)创建一个Map，封装错误信息，其中key为表单字段名称，值为错误信息
		Map<String, String> errors = new HashMap<String, String>();
		// 2)获取form中的username、password、email进行校验

		String username = form.getUsername();
		if (username == null || username.trim().isEmpty()) {
			errors.put("username", "用户名不能为空！");
		} else if (username.length() < 3 || username.length() > 10) {
			errors.put("username", "用户名长度必须在3～10之间！");
		}

		String password = form.getPassword();
		if (password == null || password.trim().isEmpty()) {
			errors.put("password", "密码不能为空！");
		} else if (password.length() < 3 || password.length() > 10) {
			errors.put("password", "密码长度必须在3～10之间！");
		}

		String email = form.getEmail();
		if (email == null || email.trim().isEmpty()) {
			errors.put("email", "Email不能为空！");
		} else if (!email.matches("\\w+@\\w+\\.\\w+")) {
			errors.put("email", "Email格式错误！");
		}
		// 3)判断是否存在错误信息
		if (errors.size() > 0) {
			// 保存错误信息
			request.setAttribute("errors", errors);
			// 保存表单数据
			request.setAttribute("form", form);
			// 转发到regist.jsp
			return "f:/jsps/user/regist.jsp"; // f表示转发

		}

		// 4、调用service方法完成注册 （保存错误信息、form到request域，转发到regist.jsp）
		try {
			userService.regist(form);
		} catch (Exception e) {
			// 保存异常信息
			request.setAttribute("msg", e.getMessage());
			// 保存form
			request.setAttribute("form", form);
			// 转发到regist.jsp
			return "f:/jsps/user/regist.jsp";
		}
		// 执行到这里说明userService执行成功，没有抛出异常
		
		// 5、发邮件    【需要准备配置文件】
		//   获取配置文件内容
		Properties props = new Properties();
		props.load(getClass().getClassLoader().getResourceAsStream("email_template.properties"));
		String host = props.getProperty("host");  //获取服务器主机
		String uname = props.getProperty("uname");  //获取用户名
		String pwd = props.getProperty("pwd");  //获取密码
		String from = props.getProperty("from");  //获取发件人
		String to = form.getEmail();  //获取发件人
		String subject = props.getProperty("subject");  //获取主题
		String content = props.getProperty("content");  //获取邮件内容
		content = MessageFormat.format(content, form.getCode());  //替换占位符{0}
		
		//   发邮件
		Session session = MailUtils.createSession(host, uname, pwd);  //得到session
		Mail mail = new Mail(from,to,subject,content);  //创建邮件对象
		try {
			MailUtils.send(session, mail);  //发邮件！
		} catch (Exception e) {
			
		}
		
		// 6、保存成功信息转发到msg.jsp
		request.setAttribute("msg", "恭喜，注册成功！请马上到邮箱激活～");
		return "f:/jsps/msg.jsp";
	}
	
	// 激活功能
	public String active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1、获取参数激活码
		String code = request.getParameter("code");
		// 2、调用Service方法完成激活
		try {
			userService.active(code);
		//  > 成功：保存成功信息到requst域，转发到msg.jsp
			request.setAttribute("msg", "恭喜！您激活成功了！请马上登录！");
		} catch (Exception e) {
		//  > 出现异常：保存异常信息到request域，转发到msg.jsp
			request.setAttribute("msg", e.getMessage());
		}
		return "f:/jsps/msg.jsp";
	}
	
	// 登录功能
	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1、封装表单数据到form中
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		// 2、输入校验（不写了...）
		// 3、调用service完成激活
		try {
			User user = userService.login(form);
		// 保存用户信息到session中
			request.getSession().setAttribute("session_user", user);
		// 给用户添加一辆购物车，即向session中保存一个Cart对象
			request.getSession().setAttribute("cart", new Cart());
		// 重定向到index.jsp中
			return "r:/index.jsp";   //r表示重定向，f表示转发
		} catch (Exception e) {
		//  保存错误信息、form、到request，转发到login.jsp
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			return "f:/jsps/user/login.jsp";
		}
	}
	
	// 退出登录功能
	public String quit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();  //销毁session
		return "r:/index.jsp";
	}
	
	

}
