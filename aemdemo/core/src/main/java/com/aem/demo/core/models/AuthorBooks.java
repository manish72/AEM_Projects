package com.aem.demo.core.models;

import java.util.List;
import java.util.Map;

import com.aem.demo.core.helper.MultifieldHelper;

public interface AuthorBooks {

	String getAuthorName();
	
	List<String> getAuthorBooks();
	
	List<Map<String,String>> getBookDetailsWithMap();
	
	List<MultifieldHelper> getBookDetailsWithBean();
	
	List<MultifieldHelper> getBookDetailsWithNestedMultifield();
}
