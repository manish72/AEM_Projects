package com.aem.demo.core.services.impl;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceRanking;

import com.aem.demo.core.services.MultiService;

@Component(service = MultiService.class,immediate = true)
@ServiceRanking(1002)
public class MultiServiceBImpl implements MultiService{

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "MultiServiceBImpl";
	}
}
