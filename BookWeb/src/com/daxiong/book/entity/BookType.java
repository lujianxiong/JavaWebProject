package com.daxiong.book.entity;

public class BookType {
	private int id;
	private String booktype;
	
	public BookType(int id, String booktype) {
		super();
		this.id = id;
		this.booktype = booktype;
	}
	public BookType() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getbooktype() {
		return booktype;
	}
	public void setbooktype(String booktype) {
		this.booktype = booktype;
	}  


}
