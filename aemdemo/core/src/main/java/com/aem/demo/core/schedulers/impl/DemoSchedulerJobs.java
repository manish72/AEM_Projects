package com.aem.demo.core.schedulers.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.sling.commons.scheduler.Job;
import org.apache.sling.commons.scheduler.JobContext;
import org.apache.sling.commons.scheduler.Scheduler;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.demo.core.schedulers.DemoSchedulerConfiguration;

@Component(service = Job.class,immediate = true)
@Designate(ocd = DemoSchedulerConfiguration.class)
public class DemoSchedulerJobs implements Job{
	private static final Logger LOG = LoggerFactory.getLogger(DemoSchedulerJobs.class);
	
	private int schedulerJobId;
	
	@Reference
	private Scheduler scheduler;
	
	@Activate
	protected void activate(DemoSchedulerConfiguration config) {
		addSchedulerJob(config);
		schedulerJobId = config.schedulerName().hashCode();
	}
	
	@Deactivate
	protected void deactivate(DemoSchedulerConfiguration config) {
		removeSchedulerJob();
	}
	
	private void removeSchedulerJob() {
		scheduler.unschedule(String.valueOf(schedulerJobId));
	}
	private void addSchedulerJob(DemoSchedulerConfiguration config) {
		ScheduleOptions in = scheduler.EXPR("0 5 12 1/1 * ? *");
		//scheduleOptions.name(String.valueOf(schedulerJobId));
		Map<String, Serializable> inMap = new HashMap<>();
		inMap.put("country", "IN");
		inMap.put("url", "www.in.com");
		in.config(inMap);
		scheduler.schedule(this, in);
		
		ScheduleOptions de = scheduler.EXPR("0 7 12 1/1 * ? *");
		Map<String, Serializable> deMap = new HashMap<>();
		deMap.put("country", "DE");
		deMap.put("url", "www.de.com");
		de.config(deMap);
		scheduler.schedule(this, de);
		
		ScheduleOptions us = scheduler.EXPR("0 9 12 1/1 * ? *");
		Map<String, Serializable> usMap = new HashMap<>();
		usMap.put("country", "US");
		usMap.put("url", "www.us.com");
		us.config(usMap);
		scheduler.schedule(this, us);
	}

	@Override
	public void execute(JobContext jobContext) {
		// TODO Auto-generated method stub
		LOG.info("\n =================> COUNTRY : URL {} : {} ",jobContext.getConfiguration().get("country"),jobContext.getConfiguration().get("url"));	
	}

}
