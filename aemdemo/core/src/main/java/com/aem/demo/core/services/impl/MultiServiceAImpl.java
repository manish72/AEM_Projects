package com.aem.demo.core.services.impl;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceRanking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.demo.core.services.MultiService;

@Component(service = MultiService.class,immediate = true,name = "serviceA")
@ServiceRanking(1001)
public class MultiServiceAImpl implements MultiService{
	private static final Logger LOG = LoggerFactory.getLogger(MultiServiceAImpl.class);

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "MultiServiceAImpl";
	}
	
	
}
