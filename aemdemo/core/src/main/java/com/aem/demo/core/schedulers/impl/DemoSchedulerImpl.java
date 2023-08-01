package com.aem.demo.core.schedulers.impl;

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

@Component(immediate = true, service = Runnable.class)
@Designate(ocd = DemoSchedulerConfiguration.class)
public class DemoSchedulerImpl implements Runnable{
	private static final Logger LOG = LoggerFactory.getLogger(DemoSchedulerImpl.class);
	
	private int schedulerId;
	
	@Reference
	private Scheduler scheduler;
	
	@Activate
	protected void activate(DemoSchedulerConfiguration config) {
		schedulerId = config.schedulerName().hashCode();
		addScheduler(config);
	}
	
	@Deactivate
	protected void deactivate(DemoSchedulerConfiguration config) {
		removeScheduler();
	}

	private void removeScheduler() {
		// TODO Auto-generated method stub
		scheduler.unschedule(String.valueOf(schedulerId));
		
	}

	private void addScheduler(DemoSchedulerConfiguration config) {
		// TODO Auto-generated method stub
		ScheduleOptions  scheduleOptions = scheduler.EXPR(config.cronExpression());
		scheduleOptions.name(String.valueOf(schedulerId));
		scheduleOptions.canRunConcurrently(false);
		scheduler.schedule(this, scheduleOptions);
		LOG.info("\n ----------------- Scheduler added for AEM DEMO ----------------");
		ScheduleOptions scheduleOptionsnow = scheduler.NOW(3,5);
		scheduler.schedule(this, scheduleOptionsnow);
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		LOG.info("\n ================ RUN METHOD EXECUTING FOR AEM DEMO ======================");
		
	}

}
