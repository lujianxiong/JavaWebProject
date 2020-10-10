package cn.daxiong.cstm.web.servlet;

//web层
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.daxiong.cstm.domain.Customer;
import cn.daxiong.cstm.service.CustomerService;
import cn.itcast.commons.CommonUtils;

@WebServlet("/CustomerServlet/*")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerService customerService = new CustomerService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // 处理post请求
		response.setContentType("text/html;charset=utf-8"); // 处理相应编码

		String pathInfo = request.getPathInfo();

		// 查询所有
		if ("/findAll".equals(pathInfo)) {
			// 1、调用service得到所有客户，并保存到request域中
			request.setAttribute("cstmList", customerService.findAll());
			// 3、转发到list.jsp
			request.getRequestDispatcher("/list.jsp").forward(request, response);
		}

		// 编辑之前的回显【根据jsp传的cid来做查询】
		if ("/preedit".equals(pathInfo)) {
			// 1、获取cid
			String cid = request.getParameter("cid");
			// 2、调用service方法，传参cid，得到Customer对象
			Customer cstm = customerService.load(cid);
			// 3、把Customer保存到request域中
			request.setAttribute("cstm", cstm);
			// 4、转发到edit.jsp，显示在表单中
			request.getRequestDispatcher("/edit.jsp").forward(request, response);
			;
		}

		// 删除客户【根据cid执行查询】
		if ("/delete".equals(pathInfo)) {
			// 1、获取cid
			String cid = request.getParameter("cid");
			// 2、调用service方法，传参cid，删除客户
			customerService.delete(cid);
			// 3、向request域中保存信息
			request.setAttribute("msg", "恭喜,删除客户成功！");
			// 4、转发到msg.jsp
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // 处理post请求
		response.setContentType("text/html;charset=utf-8"); // 处理相应编码

		String pathInfo = request.getPathInfo();

		// 添加客户
		if ("/add".equals(pathInfo)) {
			// 1、封装表单数据到Customer对象中
			Customer c = CommonUtils.toBean(request.getParameterMap(), Customer.class);
			// 2、补全cid，使用uuid
			c.setCid(CommonUtils.uuid());
			// 3、使用service方法完成添加工作
			customerService.add(c);
			// 4、向request域中保存成功信息
			request.setAttribute("msg", "恭喜,添加客户成功！");
			// 5、转发到msg.jsp
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
		}

		// 编辑客户
		if ("/edit".equals(pathInfo)) {
			// 1、封装表单数据到Customer对象中
			Customer c = CommonUtils.toBean(request.getParameterMap(), Customer.class);
			// 2、使用service方法完成修改工作
			customerService.edit(c);
			// 4、向request域中保存成功信息
			request.setAttribute("msg", "恭喜,编辑客户成功！");
			// 5、转发到msg.jsp
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
		}

		// 多条件组合查询客户
		if ("/query".equals(pathInfo)) {
			// 1、封装表单数据到Customer对象中 【四个属性：cname，gender，cellphone，email】
			Customer creteria = CommonUtils.toBean(request.getParameterMap(), Customer.class);
			// 2、使用service方法，传参customer，得到List<Customer>
			List<Customer> cstmList = customerService.query(creteria);
			// 4、将查询的结果集保存到request域中
			request.setAttribute("cstmList", cstmList);
			// 5、转发到list.jsp
			request.getRequestDispatcher("/list.jsp").forward(request, response);
		}

	}

}
