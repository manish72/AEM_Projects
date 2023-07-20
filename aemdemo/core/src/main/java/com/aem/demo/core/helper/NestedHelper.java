package com.aem.demo.core.helper;

import java.util.Date;

import org.apache.sling.api.resource.Resource;

public class NestedHelper {
	private int bookEdition;
    private Date editionDate;
    
    public NestedHelper(Resource resource){
        if(resource.getValueMap().get("bookedition", Integer.class)!=null) {
            this.bookEdition = resource.getValueMap().get("bookedition", Integer.class);
        }
        if(resource.getValueMap().get("editiondate",Date.class)!=null){
            this.editionDate=resource.getValueMap().get("editiondate",Date.class);
        }

    }

    public int getBookEdition() {
        return bookEdition;
    }

    public Date getEditionDate() {
        return editionDate;
    }
}
