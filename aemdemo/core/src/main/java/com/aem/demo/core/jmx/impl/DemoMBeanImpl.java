package com.aem.demo.core.jmx.impl;

import javax.management.DynamicMBean;
import javax.management.NotCompliantMBeanException;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.jmx.annotation.AnnotatedStandardMBean;
import com.aem.demo.core.jmx.DemoMBean;

@Component(
		immediate = true,
		service = DynamicMBean.class,
		property = {
				"jmx.objectname = com.aem.demo.core.jmx:type=Demo MBean"
		}
)
public class DemoMBeanImpl extends AnnotatedStandardMBean implements DemoMBean{
	
	private static final Logger LOG = LoggerFactory.getLogger(DemoMBeanImpl.class);
	
	public DemoMBeanImpl() throws NotCompliantMBeanException {
		super(DemoMBean.class);
	}

	@Override
	public String getAuthorName(String authorName) {
		// TODO Auto-generated method stub
		LOG.info("\n ================ Calling DemoMBean ================");
		return authorName;
	}
	
	
	
}
