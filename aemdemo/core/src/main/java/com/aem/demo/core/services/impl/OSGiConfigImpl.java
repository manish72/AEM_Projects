package com.aem.demo.core.services.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.aem.demo.core.services.OSGiConfig;

@Component(service = OSGiConfig.class,immediate = true)
@Designate(ocd = OSGiConfigImpl.ServiceConfig.class)
public class OSGiConfigImpl implements OSGiConfig{
	private static final Logger LOG = LoggerFactory.getLogger(OSGiConfigImpl.class);
	
	@ObjectClassDefinition(name = "AEM Demo - OSGI Configuration",
			description = "OSGI Configuration demo..")
	public @interface ServiceConfig{
		
		 @AttributeDefinition(name="Service Name",
				 description = "Enter service name.",
				 type = AttributeType.STRING)
		 public String getServiceName() default "AEM Demo Service";
		 
		 @AttributeDefinition(name = "Service Count",
				 description = "Add Service Count..",
				 type = AttributeType.INTEGER)
		 public int getServiceCount() default 5;
		 
		 @AttributeDefinition(name = "Live Data",
				 description = "Check this to get Live Data",
				 type = AttributeType.BOOLEAN)
		 public boolean isLiveData();
		 
		 @AttributeDefinition(name = "Countries",
				 description = "Add Countries for which you want to run this service.",
				 type = AttributeType.STRING)
		 public String[] getCountries() default {"de","in"};
		 
		 @AttributeDefinition(
	                name = "Run Modes",
	                description = "Select Run Mode.",
	                options = {
	                        @Option(label = "Author",value = "author"),
	                        @Option(label = "Publish",value = "publish"),
	                        @Option(label = "Both",value = "both")
	                },
	                type = AttributeType.STRING)
	       public String getRunMode() default "both";	
	}
	
	public String serviceName;
	private int serviceCount;
	private boolean liveData;
	private String[] countries;
	private String runModes;
	
	@Activate
	protected void activate(ServiceConfig serviceConfig) {		
		serviceName = serviceConfig.getServiceName();
		serviceCount = serviceConfig.getServiceCount();
		liveData = serviceConfig.isLiveData();
		countries = serviceConfig.getCountries();
		runModes = serviceConfig.getRunMode();
		LOG.info("------------AEM DEMO OSGI Config Activated--------------");
	}
	
	
	@Override
	public String getServiceName() {
		// TODO Auto-generated method stub
		LOG.info("------------Service Name : {}--------------",serviceName);
		return serviceName;
	}

	@Override
	public int getServiceCount() {
		// TODO Auto-generated method stub
		return serviceCount;
	}

	@Override
	public boolean isLiveData() {
		// TODO Auto-generated method stub
		return liveData;
	}

	@Override
	public String[] getCountries() {
		// TODO Auto-generated method stub
		return countries;
	}

	@Override
	public String getRunMode() {
		// TODO Auto-generated method stub
		return runModes;
	}

}
