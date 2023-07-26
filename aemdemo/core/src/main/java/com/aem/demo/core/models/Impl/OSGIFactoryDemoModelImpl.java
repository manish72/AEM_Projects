package com.aem.demo.core.models.Impl;

import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.aem.demo.core.models.OSGIFactoryDemoModel;
import com.aem.demo.core.services.OSGIFactoryConfig;

@Model(adaptables = SlingHttpServletRequest.class,
	adapters = OSGIFactoryDemoModel.class,
	defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class OSGIFactoryDemoModelImpl implements OSGIFactoryDemoModel{

	@OSGiService
	OSGIFactoryConfig osgiFactoryConfig;
	
	@Override
	public List<OSGIFactoryConfig> getAllOSGIConfigs() {
		// TODO Auto-generated method stub
		return osgiFactoryConfig.getAllConfigs();
	}

}
