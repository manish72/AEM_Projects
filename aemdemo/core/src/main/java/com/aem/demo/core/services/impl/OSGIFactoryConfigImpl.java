package com.aem.demo.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.aem.demo.core.config.DemoOSGIFactoryConfig;
import com.aem.demo.core.services.OSGIFactoryConfig;

@Component(service = OSGIFactoryConfig.class, immediate = true)
@Designate(ocd = DemoOSGIFactoryConfig.class, factory = true)
public class OSGIFactoryConfigImpl implements OSGIFactoryConfig{
	private static final Logger LOG = LoggerFactory.getLogger(OSGIFactoryConfigImpl.class);
	
	private int configID;
	private String serviceName;
	private String serviceURL;
	private List<OSGIFactoryConfig> configList;
	
	@Activate
	@Modified
	protected void activate(DemoOSGIFactoryConfig demoOSGIFactoryConfig) {
		configID = demoOSGIFactoryConfig.configID();
		serviceName = demoOSGIFactoryConfig.serviceName();
		serviceURL = demoOSGIFactoryConfig.serviceURL();
	}
	
	@Reference(service = OSGIFactoryConfig.class, cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
	public void bindOSGIFactoryConfig(final OSGIFactoryConfig config) {
		if(configList == null) {
			configList = new ArrayList<>();
		}
		configList.add(config);
	}
	
	public void unbindOSGIFactoryConfig(final OSGIFactoryConfig config) {
		configList.remove(config);
	}
	
	@Override
	public int getConfigID() {
		// TODO Auto-generated method stub
		return configID;
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

	@Override
	public List<OSGIFactoryConfig> getAllConfigs() {
		// TODO Auto-generated method stub
		return configList;
	}

}
