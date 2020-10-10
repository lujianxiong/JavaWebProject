package com.news.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class EncodeFilter
 * 过滤器解决中文乱码
 * urlPatterns:要拦截的请求    “/*”表示任意请求
 */
@WebFilter(filterName = "/EncodeFilter",urlPatterns = "/*")
public class EncodeFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EncodeFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		//编码设置
		HttpServletRequest req = (HttpServletRequest)request;   //将父类的request转为子类
		HttpServletResponse resp = (HttpServletResponse)response;   //将父类的response转为子类
		//请求编码
		req.setCharacterEncoding("utf-8");
		//响应   (相应不设置也可以，因为在我们jsp页面上设置了编码为utf-8)
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		// pass the request along the filter chain
		chain.doFilter(req, resp);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
