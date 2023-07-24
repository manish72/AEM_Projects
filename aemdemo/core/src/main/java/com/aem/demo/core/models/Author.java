package com.aem.demo.core.models;

import java.util.List;

public interface Author{
    String getFirstName();
    String getLastName();
    boolean getIsProfessor();
    String getCurrentPageTitle();
    String getRequestAttribute();
    String getBasicPageName();
    String getLastModifiedBy();
    List<String> getAuthorBooks();
}
