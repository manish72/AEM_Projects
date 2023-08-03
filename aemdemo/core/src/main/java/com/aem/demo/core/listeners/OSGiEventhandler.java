package com.aem.demo.core.listeners;


import javax.jcr.Node;

import org.apache.sling.api.SlingConstants;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.demo.core.utils.ResolverUtil;



@Component(
		service = EventHandler.class,
		immediate = true,
		property = {
				EventConstants.EVENT_TOPIC + "=org/apache/sling/api/resource/Resource/ADDED",
				EventConstants.EVENT_TOPIC + "=org/apache/sling/api/resource/Resource/CHANGED",
				EventConstants.EVENT_TOPIC + "=org/apache/sling/api/resource/Resource/REMOVED",
				EventConstants.EVENT_FILTER + "=(path=/content/aemdemo/us/en/author/*)"
		}
)
public class OSGiEventhandler implements EventHandler{
	private static final Logger LOG = LoggerFactory.getLogger(OSGiEventhandler.class);

	@Reference
	ResourceResolverFactory resourceResolverFactory;
	
	@Override
	public void handleEvent(final Event event) {
		// TODO Auto-generated method stub
		LOG.info("\n Resource event : {} at: {}",event.getTopic(),event.getProperty(SlingConstants.PROPERTY_PATH));
		try {
			ResourceResolver resourceResolver = ResolverUtil.newResolver(resourceResolverFactory);
			Resource resource = resourceResolver.getResource(event.getProperty(SlingConstants.PROPERTY_PATH).toString());
			Node node = resource.adaptTo(Node.class);
			node.setProperty("eventhandlertask", "Event "+event.getTopic()+"by "+resourceResolver.getUserID());
			resourceResolver.commit();
			
			for(String prop : event.getPropertyNames()) {
				LOG.info("\n Property : {} , Value : {} ",prop,event.getProperty(prop));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
