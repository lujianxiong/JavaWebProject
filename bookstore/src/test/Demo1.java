package test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.MessageFormat;

import org.junit.jupiter.api.Test;

public class Demo1 {
	@Test
	public void fun1() {
		// 第一个是模板，后面的是可变参数，可以有多个；  【包含了占位符的字符串就是模板】
		// 占位符：{0}、{1}、{2}
		// 可变参数：依次替换模板中占位符的值！有几个占位符就要提供几个参数
		String s = MessageFormat.format("{0}或{1}错误！", "用户名","密码");
		System.out.println(s);
	}
	
	// 这就是二进制误差！（二进制中对三分之一搞不清楚，它就无限循环，就像十进制中的10除以3，它也是搞不清楚...）
	//    在一般数学计算中这种误差是被允许的，但是在和钱有关的方面，就必须得解决这个问题
	@Test
	public void fun2() {
		System.out.println(2.0 - 1.1);
	}
	
	
	// BigInterger
	@Test
	public void fun3() {
		// BigInterger sum = new BigInterger("1");  //使用String构造器的效果与下面是一样的
		BigInteger sum = BigInteger.valueOf(1);
		for(int i=1; i<=100;i++) {
			BigInteger bi = BigInteger.valueOf(i);
			sum = sum.multiply(bi);
		}
		System.out.println(sum);
	}
	
	// BigDecimal
	@Test
	public void fun4() {
		// 1、创建BigDecimal对象的时候，必须使用String构造器
		BigDecimal d1 = new BigDecimal("2.0");
		BigDecimal d2 = new BigDecimal("1.1");
		System.out.println(d1.subtract(d2));
	}
	
	//测试生成hmac
	@Test
	public void fun5() {
		String hmac = PaymentUtil.buildHmac("Buy", "10012006921", "123456", "10", "CNY",
				"", "", "", "http://localhost:8088/bookstore/OrderServlet?method=back",
				"", "", "CMBCHINA-NET","1", "qV49014XHJ6Dc32Zu7x90V43gVP4C5061938W01t47S1AY734Dcr27011546");
		System.out.println(hmac);
	}

}
