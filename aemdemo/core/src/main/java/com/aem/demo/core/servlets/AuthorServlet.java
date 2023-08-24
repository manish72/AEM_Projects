package com.aem.demo.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import com.aem.demo.core.constants.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.demo.core.services.AuthorService;
import com.aem.demo.core.utils.ServiceUtil;

@Component(service = Servlet.class)
@SlingServletResourceTypes(
		methods = {HttpConstants.METHOD_POST},
		resourceTypes = Constants.ADDAUTHOR_RESOURCE_TYPE,
		selectors = {Constants.ADDAUTHOR_SELECTORS},
		extensions = {Constants.ADDAUTHOR_EXTENSION}
)
public class AuthorServlet extends SlingAllMethodsServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(AuthorServlet.class);
	
	@Reference
	AuthorService authorService;
	
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String resp = authorService.createAuthorNode(ServiceUtil.getCountry(request),request);
			response.getWriter().write(resp);
		}
		catch(Exception e) {
			LOG.info("\n ERROR IN REQUEST {} ",e.getMessage());
		}
	}

}
