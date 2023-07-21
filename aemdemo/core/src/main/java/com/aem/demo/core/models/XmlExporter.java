package com.aem.demo.core.models;

import java.util.Calendar;
import java.util.List;

public interface XmlExporter {
    public String getTitle();
    public String getDescription();
    public List<String> getBooks();
    public Calendar getDate();
}
