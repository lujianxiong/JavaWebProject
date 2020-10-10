package cn.daxiong.bookstore.cart.web.servlet;

import cn.daxiong.bookstore.book.domain.Book;
import cn.daxiong.bookstore.book.service.BookService;
import cn.daxiong.bookstore.cart.domain.Cart;
import cn.daxiong.bookstore.cart.domain.CartItem;
import cn.daxiong.bookstore.cart.service.CartService;
import cn.itcast.servlet.BaseServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CartServlet")
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private CartService cartService = new CartService();
	
	// 添加购物条目
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1、获取车
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		// 2、得到条目（图书和数量）
		//  - 先得到图书的bid，然后我们需要通过bid查询数据库得到Book
		String bid = request.getParameter("bid");
		Book book = new BookService().load(bid);
		//  - 数量从表单中获取
		int count = Integer.parseInt(request.getParameter("count"));
		//  从表单中获取库存
		int stock = Integer.parseInt(request.getParameter("stock"));
		int stock2 = stock-count;
		cartService.UpdateStock(bid, stock2);
		CartItem cartItem = new CartItem();
		cartItem.setBook(book);
		cartItem.setCount(count);
		// 3、把条目添加到车中
		cart.add(cartItem);
		return "f:/jsps/cart/list.jsp";
	}
	
	// 清空购物条目
	public String clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1、得到车
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		// 2、调用车中的清空方法
		cart.clear();
		return "f:/jsps/cart/list.jsp";
	}
	
	// 删除购物条目
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1、得到车
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		// 2、得到要删除的bid
		String bid = request.getParameter("bid");
		// 3、调用车中的删除方法
		cart.delete(bid);
		return "f:/jsps/cart/list.jsp";
	}
	
	
}
