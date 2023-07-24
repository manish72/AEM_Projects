package com.aem.demo.core.models.Impl;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.aem.demo.core.models.Author;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class AuthorImplTest {
	
	private final AemContext aemContext = new AemContext();
	Author author;

	@BeforeEach
	void setUp(){
		aemContext.addModelsForClasses(AuthorImpl.class);
		aemContext.load().json("/com/aem/demo/core/models/Impl/Author.json", "/component");
		aemContext.load().json("/com/aem/demo/core/models/Impl/Page.json", "/page");
		aemContext.load().json("/com/aem/demo/core/models/Impl/Resource.json", "/resource");
	}

	@Test
	void getFirstName() {
		aemContext.currentResource("/component/author");
		author = aemContext.request().adaptTo(Author.class);
		final String expected = "MANISH GURU";
		String actual = author.getFirstName();
		assertEquals(expected, actual);
	}

	@Test
	void getLastName() {
		aemContext.currentResource("/component/author");
		author = aemContext.request().adaptTo(Author.class);
		final String expected = "Vankadhara";
		String actual = author.getLastName();
		assertEquals(expected, actual);
	}

	@Test
	void getIsProfessor() {
		aemContext.currentResource("/component/author");
		author = aemContext.request().adaptTo(Author.class);
		assertEquals(true, author.getIsProfessor());
	}

	@Test
	void getCurrentPageTitle() {
		aemContext.currentPage("/page/currentPage");
		author = aemContext.request().adaptTo(Author.class);
		assertEquals("Author Bio", author.getCurrentPageTitle());
	}
	
	@Test
	void getAuthorBooks() {
		aemContext.currentResource("/component/author");
		author = aemContext.request().adaptTo(Author.class);
		assertEquals(3, author.getAuthorBooks().size());
		assertEquals("A man in the boat", author.getAuthorBooks().get(0));
	}
	
	@Test
	void getBooksWithNull() {
		aemContext.currentResource("/component/author-empty-books");
		author = aemContext.request().adaptTo(Author.class);
		assertEquals(0, author.getAuthorBooks().size());
	}
	
	@Test
	void getRequestAttribute() {
		aemContext.request().setAttribute("rAttribute", "TestAttribute");
		author = aemContext.request().adaptTo(Author.class);
		assertEquals("TestAttribute", author.getRequestAttribute());
	}
	
	@Test
	void getBasicPageName() {
		Resource resource = aemContext.currentResource("/resource/resourcePage");
		AuthorImpl authorImpl = aemContext.registerService(new AuthorImpl());
		authorImpl.resource = resource;
		assertEquals("resourcePage", authorImpl.getBasicPageName());
	}
	
	@Test
	void authorName() {
		assertEquals("AEM Demo", aemContext.registerService(new AuthorImpl()).authorName());
	}
}
