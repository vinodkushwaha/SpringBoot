package com.mindtree.IterateObject;

public class Book {
	
	private int bid;
	private String name;
	private Auther auther;
	@Override
	public String toString() {
		return "Book [bid=" + bid + ", name=" + name + ", auther=" + auther.getaName()
				+ ", price=" + price + "]";
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getName() {
		return name;
	}
	public Auther getAuther() {
		return auther;
	}
	public void setAuther(Auther auther) {
		this.auther = auther;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	private int price;


}
