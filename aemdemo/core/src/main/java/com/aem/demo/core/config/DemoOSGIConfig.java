package com.aem.demo.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name="AEM DEMO - Modular OSGI Configuration")
public @interface DemoOSGIConfig {
	
	@AttributeDefinition(
			name = "Service ID",
			description = "Enter service id.",
			type = AttributeType.INTEGER)
	int serviceID();
	
	@AttributeDefinition(
			name = "Service Name",
			description = "Enter service name",
			type = AttributeType.STRING)	
	public String serviceName() default "AEM DEMO Service";
	
	@AttributeDefinition(
			name = "Service URL",
			description = "Add Service URL.",
			type = AttributeType.STRING)	
	String serviceURL() default "localhost";

}
