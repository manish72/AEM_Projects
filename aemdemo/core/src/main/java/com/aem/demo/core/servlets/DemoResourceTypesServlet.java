package com.aem.demo.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
//import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.jcr.JcrConstants;

import org.slf4j.Logger;
import org.apache.sling.api.resource.Resource;

/* Using R7 annotation of writing servlets */
@Component(service = {Servlet.class})
@SlingServletResourceTypes(
		resourceTypes = "/apps/aemdemo/components/structure/page",
		methods = {HttpConstants.METHOD_GET,HttpConstants.METHOD_POST},
		selectors = {"demo","test"},
		extensions = {"txt","xml"}
)
@ServiceDescription("First Servlet")
public class DemoResourceTypesServlet extends SlingAllMethodsServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(DemoResourceTypesServlet.class);
	
	@Override
    protected void doGet(final SlingHttpServletRequest req,
                         final SlingHttpServletResponse resp) throws ServletException, IOException {
		LOG.info("Inside Get method in DemoResourceTypesServlet");
		final Resource resource = req.getResource();
        resp.setContentType("text/plain");
        resp.getWriter().write("Page Title = " + resource.getValueMap().get(JcrConstants.JCR_TITLE));
    }
	
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			LOG.info("\n-----------------------STARTED POST----------------------");
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		response.setContentType("text/plain");
		response.getWriter().write("------------ POST EXITED-----------");
		
	}
}
