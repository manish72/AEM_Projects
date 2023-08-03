package com.aem.demo.core.config;

import org.apache.sling.caconfig.annotation.Configuration;
import org.apache.sling.caconfig.annotation.Property;

@Configuration(label="AEM Demo - Context Aware Configuration",description = "Context Aware Configuration for AEM DEMO.")
public @interface DemoCAConfig {

	
	@Property(label="AEM Demo Country Site",
			description = "Demo Site Name")
	String siteCountry() default "us";
	
	
	@Property(label="Demo Site Locale",
			description = "Demo Site for different languages")
	String siteLocale() default "en";
	
	@Property(label="Demo Site Admin",
			description = "Admin for updating country site.")
	String siteAdmin() default "aem-demo";
	
	@Property(label="Site Section",
			description = "Site section from AEM Demo site.")
	String siteSection() default "aem";
}
