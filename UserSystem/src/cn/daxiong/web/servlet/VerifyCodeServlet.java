package cn.daxiong.web.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.vcode.utils.VerifyCode;

@WebServlet("/VerifyCodeServlet")
public class VerifyCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1、new一个验证码类对象
		VerifyCode vc = new VerifyCode();
		//2、得到验证码图片
		BufferedImage image = vc.getImage();
		//3、把图片上的文本保存到session中
		request.getSession().setAttribute("session_vcode", vc.getText());
		//4、把图片响应给客户端
		VerifyCode.output(image, response.getOutputStream());	
	}

}
