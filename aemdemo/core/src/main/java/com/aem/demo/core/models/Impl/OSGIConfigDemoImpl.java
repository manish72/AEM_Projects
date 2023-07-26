package com.aem.demo.core.models.Impl;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.aem.demo.core.models.OSGIConfigDemo;
import com.aem.demo.core.services.OSGiConfig;
import com.aem.demo.core.services.OSGiConfigModule;

@Model(adaptables = SlingHttpServletRequest.class,
adapters = OSGIConfigDemo.class,	
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class OSGIConfigDemoImpl implements OSGIConfigDemo{
	
	@OSGiService
	OSGiConfig osGiConfig;
	
	@OSGiService
	OSGiConfigModule osGiConfigModule;
	
	@Override
	public int getServiceID() {
		// TODO Auto-generated method stub
		return osGiConfigModule.getServiceID();
	}
	
	@Override
	public String getServiceNameModule() {
		// TODO Auto-generated method stub
		return osGiConfigModule.getServiceName();
	}
	
	@Override
	public String getServiceURL() {
		// TODO Auto-generated method stub
		return osGiConfigModule.getServiceURL();
	}

	@Override
	public String getServiceName() {
		// TODO Auto-generated method stub
		return osGiConfig.getServiceName();
	}

	@Override
	public int getServiceCount() {
		// TODO Auto-generated method stub
		return osGiConfig.getServiceCount();
	}

	@Override
	public boolean isLiveData() {
		// TODO Auto-generated method stub
		return osGiConfig.isLiveData();
	}

	@Override
	public String[] getCountries() {
		// TODO Auto-generated method stub
		return osGiConfig.getCountries();
	}

	@Override
	public String getRunMode() {
		// TODO Auto-generated method stub
		return osGiConfig.getRunMode();
	}
}
