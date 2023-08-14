package com.aem.demo.core.jmx;

import com.adobe.granite.jmx.annotation.Description;

public interface DemoMBean {

	@Description("Please enter Author Name")
	String getAuthorName(String authorName);
}
