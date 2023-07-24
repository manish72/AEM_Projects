package com.aem.demo.core.models.Impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.ExporterOption;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.RequestAttribute;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.demo.core.models.Author;
import com.day.cq.search.QueryBuilder;
import com.day.cq.wcm.api.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@Model(
    adaptables = SlingHttpServletRequest.class,
    adapters = Author.class,
    resourceType = AuthorImpl.RESOURCE_TYPE,
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(name = "jackson", extensions = "json", selector = "demo",
options = {
		@ExporterOption(name = "SerializationFeature.WRAP_ROOT_VALUE", value="true"),/* this option is used when we need to use JsonRootName annotation */
		@ExporterOption(name = "MapperFeature.SORT_PROPERTIES_ALPHABETICALLY", value="true")/* this option is used when we need to sort the keys of JSON items */
})
@JsonRootName("AuthorDetails")
public class AuthorImpl implements Author{
    private static final Logger LOG = LoggerFactory.getLogger(AuthorImpl.class);
    static final String RESOURCE_TYPE = "aemdemo/components/author";

    @SlingObject
    ResourceResolver resourceresolver; /* to retreive the commonly used resource, slingObject annotation is used */

    @Self
    SlingHttpServletRequest slingHttpsServletRequest;
    
    @OSGiService /* to invoke existing/custom OSGi services, this annotation is used */
    QueryBuilder queryBuilder;
    
    @RequestAttribute(name="rAttribute")
    private String reqAttribute;
    
    @ResourcePath(path = "/content/aemdemo/language-masters/basic-page")
    @Via("resource")
    Resource resource;

    @ScriptVariable
    Page currentPage; /* to retreive current page properties ScriptVariable annotation is used of Page class */

    @Inject
    @Via("resource")
    @Named("jcr:lastModifiedBy")
    String modifiedBy;
    
    @ValueMapValue /* to map firstname dialog value */
    @Default(values = "AEM")
    String fname;

    @ValueMapValue /* to map lastname dialog value */
    @Default(values = "Demo")
    String lname;
    
    @ValueMapValue
	private List<String> books;
    
    @Inject
    @Via("resource") /* to retreive dialog values of is Professor checkbox value, also this need to be used when SlingHttpServletRequest class is used  */
    boolean isProf;

    @Override
    @JsonProperty(value = "AuthorFirstName") /* to rename the key of firstName to our own custom name, this annotation is used */ 
    public String getFirstName() {
        return fname;
    }

    @Override
    public String getLastName() {
        return lname;
    }

    @Override
    public boolean getIsProfessor() {
        return isProf;
    }

    @Override
    public String getCurrentPageTitle() {
        return currentPage.getTitle();
    }

	@Override
	@JsonIgnore /* to ignore the current key-value pair inside JSON, this annotation is used */ 
	public String getRequestAttribute() {
		return reqAttribute;
	}

	@Override
	public String getBasicPageName() {
		return resource.getName();
	}
	
	/*@PostConstruct  Once all injections are completed, this method will execute 
	protected void init() {
		LOG.info("\n Inside init method {} : {}",currentPage.getTitle(),resource.getPath());
	}*/

	@Override
	public String getLastModifiedBy() {
		return modifiedBy;
	}
	
	/* for Exporter example code */
	@JsonProperty(value = "author-name")
	public String authorName() {
		return "AEM Demo";
	}

	@Override
	public List<String> getAuthorBooks() {
		// TODO Auto-generated method stub
		if(books != null) {
			return new ArrayList<String>(books);
		}
		else {
			return Collections.emptyList();
		}
	}
    
}
