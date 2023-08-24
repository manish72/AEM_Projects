package com.aem.demo.core.services;

import org.apache.sling.api.SlingHttpServletRequest;

public interface AuthorService {
	public String createAuthorNode(String country, SlingHttpServletRequest request);
}
