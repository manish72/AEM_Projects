package com.aem.demo.core.listeners;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.SlingConstants;
import org.apache.sling.event.jobs.Job;
import org.apache.sling.event.jobs.JobManager;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(
		immediate = true,
		property = {
				EventConstants.EVENT_TOPIC + "=org/apache/sling/api/resource/Resource/ADDED",
				EventConstants.EVENT_FILTER + "=(path=/content/aemdemo/us/en/home/*)"
		}
)
public class DemoJobCreator implements EventHandler{
	private static final Logger LOG = LoggerFactory.getLogger(DemoJobCreator.class);
	
	@Reference
	JobManager jobManager;
	
	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		try {
			Map<String, Object> jobProperties = new HashMap<>();
			jobProperties.put("event", event.getTopic());
			jobProperties.put("path", event.getProperty(SlingConstants.PROPERTY_PATH));
			jobProperties.put("heropage", "heropage");
			Job job = jobManager.addJob("demo/job", jobProperties);
		}
		catch(Exception e) {
			LOG.error("\n Exception is : {} ",e.getMessage());
		}
	}

}
