package com.aem.demo.core.models.Impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Calendar;

import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aem.demo.core.models.HTLSightly;

@Model(
		adaptables = SlingHttpServletRequest.class,
		adapters = HTLSightly.class,
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HTLSightlyImpl implements HTLSightly{
	private static final Logger LOG = LoggerFactory.getLogger(AuthorBooksImpl.class);
	
	@Inject
	Resource resource;
	
	@ValueMapValue
	private List<String> books;
	
	@Inject
	@Via("resource")
	Calendar publishdate;

	@Override
	public String[] getBooksArray() {
		// TODO Auto-generated method stub
		String[] booksArray = {"JAVA","AEM","OS","NETWORK"};
		return booksArray;
	}

	@Override
	public List<String> getBooks() {
		// TODO Auto-generated method stub
		if(books != null) {
			return new ArrayList<String>(books);
		}
		else {
			return Collections.emptyList();
		}
	}

	@Override
	public Map<String, String> getBooksMap() {
		// TODO Auto-generated method stub
		Map<String, String> mapBooks = new HashMap<>();
		mapBooks.put("attribute1", "Value1");
		mapBooks.put("attribute2", "Value2");
		mapBooks.put("attribute3", "Value3");
		mapBooks.put("attribute4", "Value4");
		mapBooks.put("attribute5", "Value5");
		return mapBooks;
	}

	@Override
	public List<Map<String, String>> getBookDetailsWithMap() {
		// TODO Auto-generated method stub
		List<Map<String, String>> bookDetailsMap = new ArrayList<>();
		try {
			Resource bookDetail = resource.getChild("bookdetailswithmap");
			if(bookDetail != null) {
				for (Resource book : bookDetail.getChildren()) {
					Map<String,String> bookMap = new HashMap<>();
					bookMap.put("bookname",book.getValueMap().get("bookname",String.class));
					bookMap.put("booksubject",book.getValueMap().get("booksubject",String.class));
					bookMap.put("publishyear",book.getValueMap().get("publishyear",String.class));
					bookDetailsMap.add(bookMap);
				}
			}
		}
		catch(Exception e) {
			LOG.info("\n ERROR while getting Book Details {} ",e.getMessage());
		}
		LOG.info("\n SIZE {} ",bookDetailsMap.size());
		return bookDetailsMap;
	}
	
	public Calendar getPublishDate() {
		return publishdate;
	}
}
