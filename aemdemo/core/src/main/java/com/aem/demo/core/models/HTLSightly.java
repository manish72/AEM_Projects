package com.aem.demo.core.models;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

public interface HTLSightly {
	public String[] getBooksArray();
	public List<String> getBooks();
	public Map<String,String> getBooksMap();
	public List<Map<String,String>> getBookDetailsWithMap();
	public Calendar getPublishDate();
}
