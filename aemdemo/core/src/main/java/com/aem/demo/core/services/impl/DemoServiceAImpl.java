package com.aem.demo.core.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.day.cq.wcm.api.Page;
import com.aem.demo.core.services.DemoService;
import com.aem.demo.core.services.DemoServiceA;
import com.aem.demo.core.services.MultiService;

@Component(service = DemoServiceA.class,immediate = true)
public class DemoServiceAImpl implements DemoServiceA{
	private static final Logger LOG = LoggerFactory.getLogger(DemoServiceImpl.class);
	
	@Reference
	DemoService demoService;
	
	@Reference(target = "(component.name=com.aem.demo.core.services.impl.MultiServiceBImpl)")
    MultiService multiService;
	
	public String getNameWithReference() {
		return "Response coming from "+multiService.getName();
	}

	@Override
	public List<String> getPages() {
		// TODO Auto-generated method stub
		List<String> listPages = new ArrayList<String>();
		
		try {
			Iterator<Page> pages = demoService.getPages();
			while(pages.hasNext()) {
				listPages.add(pages.next().getTitle());
			}
			return listPages;
		}
		catch(Exception e) {
			LOG.info("\n Exception {} ",e.getMessage());
		}
		return null;
	}	

}
