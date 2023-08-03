package com.aem.demo.core.listeners;

import java.util.Iterator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.PageEvent;
import com.day.cq.wcm.api.PageModification;

@Component(
		service = EventHandler.class,
		immediate = true,
		property = {
				EventConstants.EVENT_TOPIC + "=" + PageEvent.EVENT_TOPIC
		}
)
public class PageEventHandler implements EventHandler{
	private static final Logger LOG = LoggerFactory.getLogger(PageEventHandler.class);

	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		try {
			Iterator<PageModification> pageInfo = PageEvent.fromEvent(event).getModifications();
			while(pageInfo.hasNext()) {
				PageModification pageModification = pageInfo.next();
				LOG.info("\n Type : {}, Page : {}",pageModification.getType(),pageModification.getPath());
				pageModification.getEventProperties().forEach((k,v)->LOG.info("\n key : {}, Value : {} ",k,v));
			}
		}
		catch(Exception e) {
			LOG.error("\n Error while Activating/Deactivating - {}",e.getMessage());
		}
	}
	
	
}
