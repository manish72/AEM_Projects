package com.aem.demo.core.models;

public interface OSGIConfigDemo {
	public String getServiceNameModule();
	public int getServiceID();
	public String getServiceURL();
	
	public String getServiceName();
    public int getServiceCount();
    public boolean isLiveData();
    public String[] getCountries() ;
    public String getRunMode();
}
