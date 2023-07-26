package com.aem.demo.core.services;

import java.util.Iterator;

import com.day.cq.wcm.api.Page;

public interface DemoService {

	public Iterator<Page> getPages();
}
