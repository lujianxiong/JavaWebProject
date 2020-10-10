package cn.daxiong.bookstore.order.domain;
//订单类
import java.util.Date;
import java.util.List;

import cn.daxiong.bookstore.user.domain.User;

public class Order {
	private String oid;
	private Date ordertime;  // 下单时间  // 这个Date导包要导util的，不能导sql的，只有dao层中可以有sql的东西！
	private double total;  // 合计
	private int state;  // 订单状态有四种：未付款、已付款但未发货、已发货但未确认收货、已确认收货交易成功！
	private User owner;  // 订单所有者
	private String address;  //收获地址
	private List<OrderItem> orderItemList;  // 当前订单下所有条目
	
	
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
