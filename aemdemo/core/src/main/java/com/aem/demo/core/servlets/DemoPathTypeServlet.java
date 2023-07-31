package com.aem.demo.core.servlets;

import java.io.IOException;
import java.util.Iterator;


import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Component(service = Servlet.class)
@SlingServletPaths(
	value = {"/bin/pages","/demo/pages"}
)
public class DemoPathTypeServlet extends SlingAllMethodsServlet{
	private static final Logger LOG = LoggerFactory.getLogger(DemoPathTypeServlet.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(SlingHttpServletRequest request,SlingHttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		final ResourceResolver resourceResolver = request.getResourceResolver();
		Page page = resourceResolver.adaptTo(PageManager.class).getPage("/content/aemdemo/in/en");
		JSONArray pagesArray = new JSONArray();
		try {
			Iterator<Page> childPages = page.listChildren();
			while(childPages.hasNext()) {
				Page childPage = childPages.next();
				JSONObject pageObject = new JSONObject();
				pageObject.put(childPage.getTitle(), childPage.getPath().toString());
				pagesArray.put(pageObject);
			}
		}
		catch(Exception e) {
			LOG.info("\n ERROR {} ",e.getMessage());
		}
		response.setContentType("application/json");
		response.getWriter().write(pagesArray.toString());
	}
	
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			LOG.info("\n----------------------- INSIDE DemoPathTypeServlet STARTED POST----------------------");
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		response.setContentType("text/plain");
		response.getWriter().write("------------ INSIDE DemoPathTypeServlet POST EXITED-----------");
	}
}
