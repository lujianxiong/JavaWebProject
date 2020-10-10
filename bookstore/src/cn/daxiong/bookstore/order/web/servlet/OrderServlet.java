package cn.daxiong.bookstore.order.web.servlet;

import cn.daxiong.bookstore.cart.domain.Cart;
import cn.daxiong.bookstore.cart.domain.CartItem;
import cn.daxiong.bookstore.order.domain.Order;
import cn.daxiong.bookstore.order.domain.OrderItem;
import cn.daxiong.bookstore.order.service.OrderService;
import cn.daxiong.bookstore.user.domain.User;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private OrderService orderService = new OrderService();
	
	
	// 添加订单
	// （把session中的车用来生成Order对象）
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1、从session中得到cart
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		// 2、使用cart生成Order对象
		//  - 创建Order对象，并设置属性
		//   Cart  ----->  Order
		Order order = new Order();
		order.setOid(CommonUtils.uuid());  // 设置编号（uuid生成随机编号）
		order.setOrdertime(new Date());  // 设置下单时间（系统时间）
		order.setState(1);  // 设置订单状态为1，表示未付款
		User user = (User)request.getSession().getAttribute("session_user");  // 从session中获取当前登录用户
		order.setOwner(user);  // 设置订单所有者
		order.setTotal(cart.getTotal());  // 设置订单的合计（从cart中获取）
		
		//  - 创建订单条目集合
		//   cartItemList   ----->  orderItemList
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		//    循环遍历cart中所有的CartItem，使用每一个CartItem对象创建OrderItem对象，并添加到集合中
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem oi = new OrderItem();  // 创建订单条目
			oi.setIid(CommonUtils.uuid());  // 设置条目的id
			oi.setCount(cartItem.getCount());  // 设置条目的数量
			oi.setBook(cartItem.getBook());  // 设置条目的图书
			oi.setSubtotal(cartItem.getSubtotal());  // 设置条目的小计
			oi.setOrder(order);  // 设置所属订单
			orderItemList.add(oi);  // 将订单条目添加到集合中
		}
		
		//  把所有的订单条目添加到订单中
		order.setOrderItemList(orderItemList);  
		
		// 清空购物车！
		cart.clear();
		
		// 3、调用service方法完成添加订单
		orderService.add(order);
		
		// 4、保存order到request域中，转发到/jsps/order/desc.jsp中
		request.setAttribute("order", order);
		return "/jsps/order/desc.jsp";
	}
	

	// 我的订单
	public String myOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1、从当前session中得到当前用户，在获取其uid
		User user = (User)request.getSession().getAttribute("session_user");
		// 2、使用uid调用orderService#myOrders(uid)，得到该用户的所有订单List<Order>
		List<Order> orderList = orderService.myOrders(user.getUid());
		// 3、把订单列表保存到request域中，转发到/jsps/order/list.jsp
		request.setAttribute("orderList", orderList);
		return "f:/jsps/order/list.jsp";
	}
	

	// 加载订单
	public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1、得到oid参数
		// 2、使用oid调用service方法得到Order
		// 3、保存到request域，转发到/jsps/order/desc.jsp
		request.setAttribute("order", orderService.load(request.getParameter("oid")));
		return "f:/jsps/order/desc.jsp";
	}
	
	// 确认收货
	public String confirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1、获取oid参数
		String oid = request.getParameter("oid");
		// 2、调用service方法
		try {
			orderService.confirm(oid);
		    // - 如果没有异常，保存成功信息，转发到msg.jsp
			request.setAttribute("msg", "恭喜！确认收货成功！交易成功！");
		} catch (Exception e) {
		    // - 如果有异常，保存异常信息，转发到msg.jsp
			request.setAttribute("msg", e.getMessage());
		}
		return "f:/jsps/msg.jsp";
	}
	
	
}
