package com.aem.demo.core.helper;

import java.util.List;

public class AuthorBean {
	private String firstName;
	private String lastName;
	private boolean isAuthor;
	private List<String> books;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isAuthor() {
		return isAuthor;
	}

	public void setAuthor(boolean author) {
		this.isAuthor = author;
	}

	public List<String> getBooks() {
		return books;
	}

	public void setBooks(List<String> books) {
		this.books = books;
	}
}