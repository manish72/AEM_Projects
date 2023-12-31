package com.aem.demo.core.workflows;

import javax.jcr.Node;
import javax.jcr.Session;

import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

@Component(
		service = WorkflowProcess.class,
		property = {
				"process.label" + " = Demo Workflow Step",
				Constants.SERVICE_VENDOR + "=AEM Demo",
				Constants.SERVICE_DESCRIPTION + " = Custom demo workflow step."
		}
)
public class DemoWorkflowStep implements WorkflowProcess{
	private static final Logger LOG = LoggerFactory.getLogger(DemoWorkflowStep.class);
	
	@Override
	public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap processArguements) throws WorkflowException {
		// TODO Auto-generated method stub
		LOG.info("\n =========== Custom Workflow Step =========== ");
		try {
			WorkflowData workflowData = workItem.getWorkflowData();
			if(workflowData.getPayloadType().equals("JCR_PATH")) {
				Session session = workflowSession.adaptTo(Session.class);
				String path = workflowData.getPayload().toString() + "/jcr:content";
				Node node = (Node) session.getItem(path);
				String brand = processArguements.get("BRAND","");
				boolean multinational = processArguements.get("MULTINATIONAL",false);
				LOG.info("\n BRAND : {}, MULTINATIONAL : {}",brand,multinational);
				String[] countries = processArguements.get("COUNTRIES",new String[]{});
				for(String country: countries) {
					LOG.info("\n Countries {} ", country);
				}
			}
		}
		catch(Exception e) {
			LOG.info("\n ERROR {} ",e.getMessage());
		}
	}

}
