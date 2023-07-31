package com.aem.demo.core.servlets;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import static org.apache.sling.api.servlets.ServletResolverConstants.*;

import java.io.IOException;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(
    service= {Servlet.class},
    property={
            "sling.servlet.methods="+ HttpConstants.METHOD_GET,
            SLING_SERVLET_METHODS+"="+HttpConstants.METHOD_POST,
            "sling.servlet.resourceTypes="+ "/apps/aemdemo/components/structure/page",
            SLING_SERVLET_PATHS+"="+"/demo/r5servlet",
            "sling.servlet.selectors=" + "demo",
            "sling.servlet.selectors=" + "ds",
            SLING_SERVLET_EXTENSIONS+"="+"xml",
            "sling.servlet.extensions"+"="+"txt"
})
public class DemoServlet extends SlingAllMethodsServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(DemoServlet.class);
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//final ResourceResolver resourceResolver = request.getResourceResolver();
		String reqSelectors = "SELECTORS => ";
		String reqExtensions = request.getRequestPathInfo().getExtension();
		try {
			String[] selectors = request.getRequestPathInfo().getSelectors();
			for(String selector : selectors) {
				reqSelectors = reqSelectors + " " + selector;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			LOG.info("\n ERROR {} ", e.getMessage());
		}
		response.setContentType("application/json");
		response.getWriter().write(reqSelectors + " # " + reqExtensions);
	}
	
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			LOG.info("\n----------------------- INSIDE DemoServlet using R5 Annotations STARTED POST----------------------");
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		response.setContentType("text/plain");
		response.getWriter().write("------------ INSIDE DemoPathTypeServlet using R5 Annotations POST EXITED-----------");
	}

}
