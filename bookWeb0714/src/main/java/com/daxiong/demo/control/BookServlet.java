package com.daxiong.demo.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.daxiong.demo.entity.Book;
import com.daxiong.demo.service.BookService;
import com.daxiong.demo.service.impl.BookServiceImpl;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet(urlPatterns = "/book/*")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookService bookService = new BookServiceImpl();

    /**
     * Default constructor. 
     */
    public BookServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		HttpSession session= request.getSession();
		
		//查询所有书籍
		if ("/index".equals(pathInfo)) {
			List<Book> books = bookService.findAll();
			session.setAttribute("books", books);
			response.sendRedirect(request.getContextPath()+"/index.jsp");                         //这里忘记加“/”害死我了
		}
		
		//模糊查询（根据书名关键字查询）
		if ("/search".equals(pathInfo)) {
			String word = request.getParameter("word");
			List<Book> books = bookService.search(word);
			session.setAttribute("books", books);
			response.sendRedirect(request.getContextPath()+"/index.jsp"); 
		}
		
		//删除
		if ("/del".equals(pathInfo)) {
			int id = Integer.parseInt(request.getParameter("id"));
			bookService.del(id);
			//删完，重新查询书籍信息
			response.sendRedirect("index");   //这个是相对路径，执行的是上面的if判断中的index,进行查询后自动跳转到index.jsp主页上
		}
		
		//添加
		if ("/add".equals(pathInfo)) {
			String bno = request.getParameter("bno");
			String bname = request.getParameter("bname");
			String author = request.getParameter("author");
			double price = Double.parseDouble(request.getParameter("price"));
			String press = request.getParameter("press");
			Book book = new Book(0,bno,bname,author,price,press);    //id是数据库自增字段，我们给0就可以了，但不可以不写id的参数值，因为有参构造就一个，所有的参数都有
			bookService.addBook(book); 
			//增加完数据，重新查询书籍信息
			response.sendRedirect("index");
		}
		
		//根据书籍id查找 
		if ("/find".equals(pathInfo)) {
			int id = Integer.parseInt(request.getParameter("id"));
			Book book = bookService.find(id);  //id是唯一的，查到的只有一本书
			session.setAttribute("currBook", book);
			//跳转到更新页
			response.sendRedirect(request.getContextPath()+"/update.jsp");
		}
		
		//修改书籍信息
		if ("/update".equals(pathInfo)) {
			int id = Integer.parseInt(request.getParameter("id"));
			String bno = request.getParameter("bno");
			String bname = request.getParameter("bname");
			String author = request.getParameter("author");
			double price = Double.parseDouble(request.getParameter("price"));
			String press = request.getParameter("press");
			Book book = new Book(id, bno, bname, author, price, press);  //新的书籍信息
			bookService.update(book);
			//修改完数据，重新查询
			response.sendRedirect("index");
					
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
