package com.rgt.simpleLibrary;

public class Book {
	private String title;
	private String author;
	private boolean available;
	private Patron barrower;

	public Book(String title, String author) {
		this.title = title;
		this.author = author;
		this.available = true;
		this.barrower = null;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Patron getBarrower() {
		return barrower;
	}

	public void setBarrower(Patron barrower) {
		this.barrower = barrower;
	}

	public Book() {

	}

}