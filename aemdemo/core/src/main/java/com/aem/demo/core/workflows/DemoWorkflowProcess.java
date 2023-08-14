package com.aem.demo.core.workflows;

import java.util.Iterator;
import java.util.Set;

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
		immediate = true,
		property = {
				"process.label" + " = Demo Workflow Process",
				Constants.SERVICE_VENDOR + "=AEM Demo",
				Constants.SERVICE_DESCRIPTION + " = Custom demo workflow process"
		}
)
public class DemoWorkflowProcess implements WorkflowProcess{
	private static final Logger LOG = LoggerFactory.getLogger(DemoWorkflowProcess.class);
	
	@Override
	public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap processArguments) throws WorkflowException {
		// TODO Auto-generated method stub
		LOG.info("\n ======================================================= ");
		try {
			WorkflowData workflowData = workItem.getWorkflowData();
			if(workflowData.getPayloadType().equals("JCR_PATH")) {
				Session session = workflowSession.adaptTo(Session.class);
				String path = workflowData.getPayload().toString() + "/jcr:content";
				Node node =  (Node) session.getItem(path);
				String[] processArgs = processArguments.get("PROCESS_ARGS", "string").toString().split(",");
				MetaDataMap wfd = workItem.getWorkflow().getWorkflowData().getMetaDataMap();
				for(String wfArgs : processArgs) {
					String[] args = wfArgs.split(":");
					String prop = args[0];
					String value = args[1];
					if(node != null) {
						wfd.put(prop, value);
						node.setProperty(prop, value);
					}
				}
				
				Set<String> keyset = wfd.keySet();
				Iterator<String> i = keyset.iterator();
				while(i.hasNext()) {
					String key = i.next();
					LOG.info("\n ITEM key - {} , value - {}",key,wfd.get(key));
				}
			}
		}
		catch(Exception e) {
			
		}
		
	}

}
