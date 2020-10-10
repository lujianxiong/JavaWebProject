package cn.daxiong.bookstore.book.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.daxiong.bookstore.book.service.BookService;
import cn.itcast.servlet.BaseServlet;

@WebServlet("/BookServlet")
public class BookServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private BookService bookService = new BookService();

	// 查询所有图书
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("bookList", bookService.findAll());
		return "f:/jsps/book/list.jsp";
	}

	// 根据cid进行分类查询图书
	public String findByCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cid = request.getParameter("cid");
		request.setAttribute("bookList", bookService.findByCategory(cid));
		return "f:/jsps/book/list.jsp";
	}

	// 根据bid进行图书查询
	public String load(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 得到参数bid
		String bid = request.getParameter("bid");
		// 查询得到Book,并保存到request域
		request.setAttribute("book", bookService.load(bid));
		// 转发到desc.jsp
		return "f:/jsps/book/desc.jsp";
	}

}
