package com.aem.demo.core.models.Impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Calendar;
import com.aem.demo.core.helper.AuthorBean;

import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.demo.core.models.HTLSightly;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

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
	
	@ValueMapValue
	private int numbers;
	
	@Inject
	@Via("resource")
	Calendar publishdate;
	
	@ScriptVariable
	PageManager pageManager;

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
	
	public int getNumbers() {
		return numbers;
	}
	
	public AuthorBean getAuthorInfo() {
		AuthorBean authorBean=new AuthorBean();
		authorBean.setFirstName("AEM");
		authorBean.setLastName("GEEKS");
		authorBean.setAuthor(true);
		List<String> books=new ArrayList<String>();
		books.add("JAVA"); books.add("SFCC"); books.add("AEM");
		authorBean.setBooks(books);
		return authorBean;
	}

	@Override
	public List<String> getBooksList() {
		// TODO Auto-generated method stub
		List<String> booksList = new ArrayList<>();
		booksList.add("JAVA");booksList.add("AEM");booksList.add("OS");booksList.add("NETWORK");
		return booksList;
	}

	@Override
	public List<Page> getPageInfo() {
		// TODO Auto-generated method stub
		List<Page> childPage=new ArrayList<>();
		Page home = pageManager.getPage("/content/aemdemo/language-masters/en");
		Iterator<Page> childs = home.listChildren();
		while(childs.hasNext()) {
			Page page = childs.next();
			childPage.add(page);
		}
		return childPage;
	}

	@Override
	public Iterator<Page> getPageIterator() {
		// TODO Auto-generated method stub
		Page home = pageManager.getPage("/content/aemdemo/language-masters/en");
		Iterator<Page> childs = home.listChildren();
		return childs;
	}
}
