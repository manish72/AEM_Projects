package com.aem.demo.core.models.Impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.demo.core.models.ServiceDemo;
import com.aem.demo.core.services.DemoService;
import com.aem.demo.core.services.DemoServiceA;
import com.aem.demo.core.services.MultiService;
import com.day.cq.wcm.api.Page;

@Model(adaptables = SlingHttpServletRequest.class,
adapters = ServiceDemo.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ServiceDemoImpl implements ServiceDemo{
	private static final Logger LOG = LoggerFactory.getLogger(ServiceDemoImpl.class);
	
	@OSGiService
	DemoService demoService;
	
	@Inject
	DemoServiceA demoServiceA;
	
	@OSGiService(filter = "(component.name=serviceA)")
	MultiService multiService;
	
	@OSGiService(filter = "(component.name=com.aem.demo.core.services.impl.MultiServiceBImpl)")
	MultiService multiServiceB;
	
	
	
	@Override
	public Iterator<Page> getPagesList() {
		// TODO Auto-generated method stub
		return demoService.getPages();
	}
	
	@PostConstruct
	protected void init() {
		
	}

	@Override
	public List<String> getPageTitleList() {
		// TODO Auto-generated method stub
		return demoServiceA.getPages();
	}

	@Override
	public String getNameFromService() {
		// TODO Auto-generated method stub
		return multiService.getName();
	}
	
	@Override
	public String getNameFromServiceB() {
		// TODO Auto-generated method stub
		return multiServiceB.getName();
	}
	
	@Override
    public String getNameWithReference() {
        return demoServiceA.getNameWithReference();
    }

}
