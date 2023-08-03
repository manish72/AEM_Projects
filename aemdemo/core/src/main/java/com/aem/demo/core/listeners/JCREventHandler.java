package com.aem.demo.core.listeners;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.observation.Event;
import javax.jcr.observation.EventIterator;
import javax.jcr.observation.EventListener;

import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true,service = EventListener.class)
public class JCREventHandler implements EventListener{

	private static final Logger LOG = LoggerFactory.getLogger(JCREventHandler.class);
	private Session session;
	
	@Reference
	SlingRepository slingRepository;
	
	@Activate
	public void activate() throws Exception{
		// TODO Auto-generated method stub
		try {
			String[] nodetypes = {"cq:PageContent"};
			/* Get default workspace for demoserviceuser loginService */
			session = slingRepository.loginService("demoserviceuser", null);
			
			session.getWorkspace().getObservationManager().addEventListener(
					this, 
					Event.NODE_ADDED | Event.PROPERTY_ADDED, 
					"/content/aemdemo/us/en/author", 
					true, 
					null, 
					nodetypes, 
					false);
		}
		catch(RepositoryException e) {
			LOG.info(" \n Error while adding Event Listener : {} ",e.getMessage());
		}

	}

	@Override
	public void onEvent(EventIterator eventIterator) {
		// TODO Auto-generated method stub
		try {
			while(eventIterator.hasNext() && !eventIterator.hasNext()) {
				if(eventIterator.nextEvent() != null) {
					
				}
				LOG.info("\n Type : {}, Path : {} ", eventIterator.nextEvent().getType(),eventIterator.nextEvent().getPath());
			}
		}
		catch(Exception e) {
			LOG.error("\n Error while processing events : {} ",e.getMessage());
		}
		
	}
	
}
