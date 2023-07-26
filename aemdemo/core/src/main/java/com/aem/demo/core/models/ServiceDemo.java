package com.aem.demo.core.models;

import java.util.Iterator;
import java.util.List;

import com.day.cq.wcm.api.Page;

public interface ServiceDemo {
	
	public Iterator<Page> getPagesList();
	
	public List<String> getPageTitleList();
	
	public String getNameFromService();
	
	public String getNameFromServiceB();
	
	public String getNameWithReference();
}
