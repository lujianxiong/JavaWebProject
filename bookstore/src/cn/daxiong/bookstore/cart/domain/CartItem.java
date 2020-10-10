package cn.daxiong.bookstore.cart.domain;
import java.math.BigDecimal;

//购物车 条目类
import cn.daxiong.bookstore.book.domain.Book;

public class CartItem {
	private Book book;  //商品
	private int count;  //数量
	
	// 小计计算方法（不提供成员，但提供一个getter方法）
	public double getSubtotal() {
		// BigDecimal消除二进制计算中的误差问题
		BigDecimal d1 = new BigDecimal(book.getPrice()+"");  //double类型数据+空字符串，变成字符串
		BigDecimal d2 = new BigDecimal(count+"");  //int类型数据+空字符串，变成字符串
		return d1.multiply(d2).doubleValue();  //使用BigDecimal中的乘，结果是BigDecimal类型，使用doubleValue()方法转double类型
	}
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
