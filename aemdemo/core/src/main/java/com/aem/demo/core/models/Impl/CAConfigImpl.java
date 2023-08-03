package com.aem.demo.core.models.Impl;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.caconfig.ConfigurationBuilder;
import org.apache.sling.caconfig.ConfigurationResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.lang3.StringUtils;
import com.aem.demo.core.config.DemoCAConfig;
import com.aem.demo.core.models.CAConfig;
import com.day.cq.wcm.api.Page;

@Model(
		adaptables = {SlingHttpServletRequest.class},
		adapters = {CAConfig.class},
		resourceType = {CAConfigImpl.RESOURCE_TYPE},
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class CAConfigImpl implements CAConfig{
	private static final Logger LOG = LoggerFactory.getLogger(CAConfigImpl.class);
	protected static final String RESOURCE_TYPE = "/apps/aemdemo/components/author";
	
	@SlingObject
	ResourceResolver resourceResolver;
	
	@ScriptVariable
	Page currentPage;
	
	@OSGiService
	ConfigurationResolver configurationResolver;
	
	private String siteCountry;
	private String siteLocale;
	private String siteAdmin;
	private String siteSection;
	private DemoCAConfig demoCAConfig;
	@Override
	public String getSiteCountry() {
		// TODO Auto-generated method stub
		return siteCountry;
	}
	@Override
	public String getSiteAdmin() {
		// TODO Auto-generated method stub
		return siteAdmin;
	}
	@Override
	public String getSiteLocale() {
		// TODO Auto-generated method stub
		return siteLocale;
	}
	@Override
	public String getSiteSection() {
		// TODO Auto-generated method stub
		return siteSection;
	}
	
	@PostConstruct
	public void postConstruct() {
		// TODO Auto-generated method stub
		DemoCAConfig demoCAConfig = getContextAwareConfig(currentPage.getPath(),resourceResolver);
		siteCountry = demoCAConfig.siteCountry();
		siteAdmin = demoCAConfig.siteAdmin();
		siteLocale = demoCAConfig.siteLocale();
		siteSection = demoCAConfig.siteSection();
		
	}
	public DemoCAConfig getContextAwareConfig(String currentPage, ResourceResolver resourceResolver) {
		// TODO Auto-generated method stub
		String currentPath = StringUtils.isNotBlank(currentPage) ? currentPage : StringUtils.EMPTY;
		Resource contentResource = resourceResolver.getResource(currentPath);
		if(contentResource != null) {
			ConfigurationBuilder configurationBuilder = contentResource.adaptTo(ConfigurationBuilder.class);
			if(configurationBuilder != null) {
				return configurationBuilder.as(DemoCAConfig.class);
			}
		}
		return null;
	}
	
	
}
