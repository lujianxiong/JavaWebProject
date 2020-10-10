package com.daxiong.demo.entity;

public class Book {
	private int id;
	private String bno;
	private String bname;
	private String author;
	private double price;
	private String press;
	
	public Book() {
		super();
	}
	
	public Book(int id, String bno, String bname, String author, double price, String press) {
		super();
		this.id = id;
		this.bno = bno;
		this.bname = bname;
		this.author = author;
		this.price = price;
		this.press = press;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getauthor() {
		return author;
	}
	public void setauthor(String author) {
		this.author = author;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	
	
	public String getBno() {
		return bno;
	}

	public void setBno(String bno) {
		this.bno = bno;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", bname=" + bname + ", author=" + author + ", price=" + price
				+ ", press=" + press + "]";
	}
	
	
	

}
