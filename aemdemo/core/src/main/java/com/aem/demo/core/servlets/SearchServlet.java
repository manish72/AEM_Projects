package com.aem.demo.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.demo.core.services.SearchService;

/*Using R7 annotation */
@Component(service = Servlet.class)
@SlingServletPaths(
		value = {"/demo/search"}
)
public class SearchServlet extends SlingAllMethodsServlet{	
	private static final long serialVersionUID = 1L;	
	private static final Logger LOG = LoggerFactory.getLogger(SearchServlet.class);
	
	@Reference
	SearchService searchService;
	
	@Override
	protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject searchResult = null;
		try {
			// For SQL2 Code
			String searchPath = request.getRequestParameter("searchPath").getString();
			searchResult = searchService.searchResultSQL2(searchPath);
			
			// For QueryBuilder Code
			/*
			String searchtext = request.getRequestParameter("searchtext").getString();
			int pageNumber = Integer.parseInt(request.getRequestParameter("pageNumber").toString());
			int resultPerPage = Integer.parseInt(request.getRequestParameter("resultPerPage").toString());
			int startResult = pageNumber * resultPerPage;
			searchResult = searchService.searchResult(searchtext,startResult,resultPerPage);
			*/
		}
		catch (Exception e) {
			// TODO: handle exception
			LOG.info("\n ERROR {} ",e.getMessage());
		}
		
		response.setContentType("application/json");
		response.getWriter().write(searchResult.toString());
	}
}
