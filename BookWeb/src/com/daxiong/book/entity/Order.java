package com.daxiong.book.entity;
//时间属性java.util.Date  也可也使用时间戳
//我们可以简化处理  时间使用字符串

public class Order {
	private int id;
	private String oid;    //订单编号
	private int uid;
	private double price;
	private String otime;  //下单时间
	
	public Order() {
		super();
	}
	public Order(int id, String oid, int uid, double price, String otime) {
		super();
		this.id = id;
		this.oid = oid;
		this.uid = uid;
		this.price = price;
		this.otime = otime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getoid() {
		return oid;
	}
	public void setoid(String oid) {
		this.oid = oid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getOtime() {
		return otime;
	}
	public void setOtime(String otime) {
		this.otime = otime;
	}
	

}
