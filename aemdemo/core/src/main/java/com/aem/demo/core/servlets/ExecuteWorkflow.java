package com.aem.demo.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.model.WorkflowModel;

@Component(service = Servlet.class)
@SlingServletPaths(
		value = {"/bin/executedemoworkflow","/demo/executedemoworkflow"}
)
public class ExecuteWorkflow extends SlingSafeMethodsServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(ExecuteWorkflow.class);
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String status = "Workflow Executing";
		
		final ResourceResolver resourceResolver = request.getResourceResolver();
		
		String payload = request.getRequestParameter("page").getString();
		try {
			if(StringUtils.isNotBlank(payload)) {
				WorkflowSession workflowSession = resourceResolver.adaptTo(WorkflowSession.class);
				
				WorkflowModel workflowModel = workflowSession.getModel("/var/workflow/models/demo-page-version");
				
				WorkflowData workflowData = workflowSession.newWorkflowData("JCR_PATH", payload);
				
				status = workflowSession.startWorkflow(workflowModel, workflowData).getState();
			}
		}
		catch(Exception e) {
			LOG.info("\n ERROR IN WORKFLOW {} ",e.getMessage());
		}
		response.setContentType("application/json");
		response.getWriter().write(status);
	}
	
}
