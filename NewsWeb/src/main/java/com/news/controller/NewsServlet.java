package com.news.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.news.entity.News;
import com.news.service.NewsService;
import com.news.service.impl.NewsServiceImpl;

/**
 * Servlet implementation class News
 */
@WebServlet(urlPatterns = "/news/*")
public class NewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsService newsService = new NewsServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		HttpSession session= request.getSession();
		PrintWriter out = response.getWriter();
		
		if ("/index.jsp".equals(pathInfo)) {
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}
		
		//添加书籍
		if ("/add".equals(pathInfo)) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			newsService.addNews(title, content);
			out.print("<script>alert('添加成功！');window.location.href='display';</script>");
		}
		
		//查询所有书籍
		if ("/display".equals(pathInfo)){
            List<News> news = newsService.findAll();
            session.setAttribute("news",news);
            response.sendRedirect(request.getContextPath()+"/display.jsp");
        }
		
		//根据id删除书籍
		if ("/delete".equals(pathInfo)){
			int id = Integer.parseInt(request.getParameter("id"));
			newsService.deleteById(id);
			out.print("<script>alert('delete 成功！');window.location.href='display';</script>");
        }
		
		//根据id查找书籍详细信息
		if ("/find".equals(pathInfo)) {
			int id = Integer.parseInt(request.getParameter("id"));
			News news = newsService.find(id);
			session.setAttribute("currNews", news);
			response.sendRedirect(request.getContextPath()+"/update.jsp");
		}
		
		//修改书籍信息
		if ("/update".equals(pathInfo)) {
			int id = Integer.parseInt(request.getParameter("id"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			News news = new News(id,title,content);
			newsService.update(news);
			out.print("<script>alert('update 成功！');window.location.href='display';</script>");
		}
		
		//根据书名关键字查询书籍
		if ("/search".equals(pathInfo)) {
			String title = request.getParameter("title");
			List<News> news = newsService.search(title);
			session.setAttribute("news", news);
			response.sendRedirect(request.getContextPath()+"/display.jsp");
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
