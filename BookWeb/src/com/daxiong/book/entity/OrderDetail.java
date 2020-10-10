package com.daxiong.book.entity;

public class OrderDetail {
	 private int id;
	 private int oid;   //订单的编号
	 private int bid;   //书的id
	public OrderDetail() {
		super();
	}
	public OrderDetail(int id, int oid, int bid) {
		super();
		this.id = id;
		this.oid = oid;
		this.bid = bid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	 

}
