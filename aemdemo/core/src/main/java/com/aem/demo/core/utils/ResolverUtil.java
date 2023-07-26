package com.aem.demo.core.utils;

import java.util.HashMap;

import java.util.Map;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.LoginException;
/**
 *  resource resolver factory helper class
 */
public final class ResolverUtil {
	
	private ResolverUtil() {
		
	}
	
	public static final String DEMO_SERVICE_USER = "demoserviceuser";
	/**
     * @param  resourceResolverFactory factory
     * @return new resource resolver for AEM Demo service user 
     * @throws LoginException if problems
     */
	
	public static ResourceResolver newResolver(ResourceResolverFactory resourceResolverFactory) throws LoginException {
		final Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(ResourceResolverFactory.SUBSERVICE, DEMO_SERVICE_USER);
		
		//fetches the admin service resolver using service user
		ResourceResolver resolver = resourceResolverFactory.getServiceResourceResolver(paramMap);
		return resolver;		
	}

}
