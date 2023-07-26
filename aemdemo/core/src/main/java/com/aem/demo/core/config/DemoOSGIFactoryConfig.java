package com.aem.demo.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
		name="AEM Demo - OSGI Factory Configuration",
		description = "OSGI Factory Configuration demo.")
public @interface DemoOSGIFactoryConfig {

	@AttributeDefinition(
			name = "Config ID",
			description = "Enter config ID",
			type = AttributeType.INTEGER)
	int configID();

	@AttributeDefinition(
			name = "Service Name",
			description = "Enter service name.",
			type = AttributeType.STRING)
	public String serviceName() default "Service #";
	
	@AttributeDefinition(
			name = "Service URL",
			description = "Add Service URL",
			type = AttributeType.STRING)
	public String serviceURL() default "URL #";
	
}
