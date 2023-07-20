package com.aem.demo.core.models.Impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.aem.demo.core.helper.MultifieldHelper;
import com.aem.demo.core.helper.NestedHelper;
import com.aem.demo.core.models.AuthorBooks;

@Model(
		adaptables = SlingHttpServletRequest.class,
		adapters = AuthorBooks.class,
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AuthorBooksImpl implements AuthorBooks{
	private static final Logger LOG = LoggerFactory.getLogger(AuthorBooksImpl.class);
	
	@Inject
	Resource componentResource;
	
	@ValueMapValue
	@Default(values="AEM")
	private String authorname;
	
	@ValueMapValue
	private List<String> books;

	@Override
	public String getAuthorName() {
		// TODO Auto-generated method stub
		return authorname;
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

	@Override
	public List<Map<String, String>> getBookDetailsWithMap() {
		// TODO Auto-generated method stub
		List<Map<String, String>> bookDetailsMap = new ArrayList<>();
		try {
			Resource bookDetail = componentResource.getChild("bookdetailswithmap");
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

	@Override
	public List<MultifieldHelper> getBookDetailsWithBean() {
		// TODO Auto-generated method stub
		List<MultifieldHelper> bookDetailsBean = new ArrayList<>();
		try {
			Resource bookDetailBean = componentResource.getChild("bookdetailswithbean");
			if(bookDetailsBean!=null) {
				for(Resource bookBean : bookDetailBean.getChildren()) {
					LOG.info("\n PATH Bean {} ",bookBean.getPath());
					LOG.info("\n BEAN PRO {}",bookBean.getValueMap().get("bookname",String.class));
					
					bookDetailsBean.add(new MultifieldHelper(bookBean));
				}
			}
		}
		catch(Exception e) {
			LOG.info("\n ERROR while getting Book Details with Bean {}",e.getMessage());
		}
		return bookDetailsBean;
	}

	@Override
    public List<MultifieldHelper> getBookDetailsWithNestedMultifield() {
        List<MultifieldHelper> bookDetailsNested=new ArrayList<>();
        try {
            Resource bookDetailNested=componentResource.getChild("bookdetailswithnestedmultifield");
            if(bookDetailNested!=null){
                for (Resource bookNested : bookDetailNested.getChildren()) {
                    MultifieldHelper multifieldHelper=new MultifieldHelper(bookNested);
                    if(bookNested.hasChildren()){
                        List<NestedHelper> bookNestedList=new ArrayList<>();
                        Resource nestedResource=bookNested.getChild("bookeditions");
                        for(Resource nasted : nestedResource.getChildren()){
                            bookNestedList.add(new NestedHelper(nasted));
                        }
                        multifieldHelper.setBookEditions(bookNestedList);
                    }
                    bookDetailsNested.add(multifieldHelper);
                }
            }
        }catch (Exception e){
            LOG.info("\n ERROR while getting Book Details With Nested Multifield {} ",e.getMessage());
        }
        LOG.info("\n SIZE Multifield {} ",bookDetailsNested.size());
        return bookDetailsNested;
    }
}
