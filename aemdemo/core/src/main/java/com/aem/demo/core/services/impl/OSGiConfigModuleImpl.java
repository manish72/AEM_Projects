package com.aem.demo.core.services.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

import com.aem.demo.core.config.DemoOSGIConfig;
import com.aem.demo.core.services.OSGiConfigModule;

@Component(service=OSGiConfigModule.class,immediate = true)
@Designate(ocd = DemoOSGIConfig.class)
public class OSGiConfigModuleImpl implements OSGiConfigModule{
	
	private int serviceId;
	private String serviceName;
	private String serviceURL;
	
	@Activate
	protected void activate(DemoOSGIConfig demoOSGIConfig) {
		serviceId = demoOSGIConfig.serviceID();
		serviceName = demoOSGIConfig.serviceName();
		serviceURL = demoOSGIConfig.serviceURL();
	}

	@Override
	public int getServiceID() {
		// TODO Auto-generated method stub
		return serviceId;
	}

	@Override
	public String getServiceName() {
		// TODO Auto-generated method stub
		return serviceName;
	}

	@Override
	public String getServiceURL() {
		// TODO Auto-generated method stub
		return serviceURL;
	}
	
	

}
