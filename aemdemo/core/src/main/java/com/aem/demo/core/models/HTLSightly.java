package com.aem.demo.core.models;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.aem.demo.core.helper.AuthorBean;
import com.day.cq.wcm.api.Page;

public interface HTLSightly {
	public String[] getBooksArray();
	public List<String> getBooks();
	public Map<String,String> getBooksMap();
	public List<Map<String,String>> getBookDetailsWithMap();
	public Calendar getPublishDate();
	public int getNumbers();
	public AuthorBean getAuthorInfo();
	public List<String> getBooksList();
	public List<Page> getPageInfo();
	public Iterator<Page> getPageIterator();
}
