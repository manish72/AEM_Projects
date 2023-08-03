package com.aem.demo.core.listeners;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.event.jobs.Job;
import org.apache.sling.event.jobs.consumer.JobConsumer;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.demo.core.utils.ResolverUtil;

@Component(service = JobConsumer.class,
	immediate = true,
	property = {
			JobConsumer.PROPERTY_TOPICS + "=demo/job"
	}
)
public class DemoJobConsumer implements JobConsumer{
	private static final Logger LOG = LoggerFactory.getLogger(DemoJobConsumer.class);

	@Reference
	ResourceResolverFactory resourceResolverFactory;
	
	@Override
	public JobResult process(Job job) {
		// TODO Auto-generated method stub
		try {
			ResourceResolver resourceResolver = ResolverUtil.newResolver(resourceResolverFactory);
			String path = (String) job.getProperty("path");
			String event = (String) job.getProperty("event");
			String heropage = (String) job.getProperty("heropage");
			LOG.info("\n Job executing for : {}",resourceResolver.getResource(heropage).getName());
			return JobResult.OK;
		}
		catch(Exception e) {
			LOG.info("\n Error in Job Consumer : {} ",e.getMessage());
			return JobResult.FAILED;
		}
	}
}
