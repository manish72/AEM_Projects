package com.aem.demo.core.services;


import org.json.JSONObject;

public interface SearchService {

	public JSONObject searchResult(String searchText,int startResult,int resultPerPage);
	public JSONObject searchResultSQL2(String searchPath);
}
