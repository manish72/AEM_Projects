package com.aem.demo.core.services;

import java.util.List;

public interface OSGIFactoryConfig {
	
	public int getConfigID();
	
	public String getServiceName();
	
	public String getServiceURL();
	
	public List<OSGIFactoryConfig> getAllConfigs();

}
