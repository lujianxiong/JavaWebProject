package com.daxiong.book.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.daxiong.book.dao.BookDAO;
import com.daxiong.book.dao.BookTypeDAO;
import com.daxiong.book.dao.impl.BookDAOImpl;
import com.daxiong.book.dao.impl.BookTypeDAOImpl;
import com.daxiong.book.entity.Book;
import com.daxiong.book.entity.BookType;
import com.daxiong.book.entity.Order;
import com.daxiong.book.entity.User;
import com.daxiong.book.service.OrderService;
import com.daxiong.book.service.impl.OrderServiceImpl;
import com.daxiong.book.util.DateUtil;

/**
 * Servlet implementation class BookServlet
 * 处理与书籍相关的请求
 */
@WebServlet(urlPatterns = "/book/*")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookTypeDAO bookTypeDAO = new BookTypeDAOImpl();
	private BookDAO bookDAO = new BookDAOImpl();
	List<Book> car = new ArrayList<Book>();
	private OrderService orderService= new OrderServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		HttpSession session = request.getSession();
		
		//在首页展示查询第一种分类下的书籍信息
		if ("/index".equals(pathInfo)) {  //从登录页面跳转到index，查询所有书籍类型
			List<BookType> bookTypes = bookTypeDAO.findAll();
			session.setAttribute("booktypes", bookTypes);  //保存到session用于页面显示
			/*
			 * 这里特别需要注意：
			 * bookTypes.get(0).getId()  0表示的是第一行，千万不要写成了1；0是获取第一个对象的Id，1是获取第二个对象的Id
			 */
			int typeId = bookTypes.get(0).getId();  //数据库中第一种类型的id  
			//查询第一种类型的书籍
			List<Book> books = bookDAO.findBookByType(typeId);
			session.setAttribute("currBooks", books);
			response.sendRedirect(request.getContextPath()+"/index.jsp");  //将数据重定向到index页面
		}
		
		//点击类别，按类别查询
		if ("/findByType".equals(pathInfo)) {
			int typeId = Integer.parseInt(request.getParameter("typeid"));  //request返回的是字符串，需要转型
			List<Book> books = bookDAO.findBookByType(typeId);
			session.setAttribute("currBooks", books);
			response.sendRedirect(request.getContextPath()+"/books.jsp");
		}
		
		//按关键字搜索
		if ("/search".equals(pathInfo)) {
			String bookname = request.getParameter("bname");    //获取搜索框中输入的关键字
			System.out.println(bookname);
            List<Book> books = bookDAO.findBookByName(bookname);
            //这个一定要是currBooks，与上面的类别查询一致，并且与books.jsp中要遍历的session中的数据名相，否则找不到
            session.setAttribute("currBooks",books);                     
            response.sendRedirect(request.getContextPath()+"/books.jsp");
		}
		
		//加入购物车
		if ("/addCar".equals(pathInfo)) {
			int bid = Integer.parseInt(request.getParameter("bid"));
			Book book = bookDAO.findBook(bid);
			car.add(book);
			//将购物车保存在session中
			session.setAttribute("car", car);
			response.sendRedirect(request.getContextPath()+"/books.jsp");
		}
		
		//结算请求
		if ("/pay".equals(pathInfo)) {
			double price = Double.parseDouble(request.getParameter("totalPrice"));
			User user = (User)session.getAttribute("currUser");  //因为有拦截器，所以我们保证它是不为空的，可以直接强转
			Date date = new Date();
			String oid = "";
			//唯一的订单编号
			oid = DateUtil.dateToString(date, "yyyyMMddHHmmss")+user.getId();
			Random random = new Random();
			for (int i = 0; i < 3; i++) {
				oid += random.nextInt(10);
			}
			System.out.println(oid);
			//下单时间
			String otime = DateUtil.dateToString(date, "yyyy-MM-dd HH:mm:ss");
			//构造订单信息
			Order order = new Order(0,oid,user.getId(),price,otime);
			//订单详情中的书籍
			List<Book> books = new ArrayList<Book>();
			//获取购物车页面勾选的书籍
			String[] bids = request.getParameterValues("bid");  //获取复选框的name属性的值
			//构造订单详情
			for (String bid : bids) {
				int id = Integer.parseInt(bid);
				Book book = bookDAO.findBook(id);
				books.add(book);
				//重置购物车，将购物车已选商品删除
				for (int i = 0; i < car.size(); i++) {
					if (id == car.get(i).getId()) {  //这本书要购买，需要从购物车中删除
						car.remove(i);
					}
				}
			}
			//重置购物车
			session.setAttribute("car", car);  //这个car是新的购物车信息，保存到session
			orderService.addOrder(order, books);
			//跳转到OrderServlet,查询当前用户的所有历史订单
			response.sendRedirect(request.getContextPath()+"/order/selectByUser");
			
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
