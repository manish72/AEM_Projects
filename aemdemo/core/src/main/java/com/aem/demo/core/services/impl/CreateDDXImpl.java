package com.aem.demo.core.services.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;

import com.adobe.aemfd.docmanager.Document;
import com.aem.demo.core.services.CreateDDX;

@Component(
		service = CreateDDX.class,
		name = "CreateDDX"
)
public class CreateDDXImpl implements CreateDDX{
	private static final Logger LOG = LoggerFactory.getLogger(CreateDDXImpl.class);
	
	DocumentBuilderFactory docFactory;
	DocumentBuilder documentBuilder;

	@Override
	public Document mergetwoPDFs() {
		// TODO Auto-generated method stub
		LOG.info("Inside MergretwoPDFs method");
		
		/* Creating Document Factory instance */
		docFactory = DocumentBuilderFactory.newInstance();
		try {
			/* Creating document builder factory instance */
			documentBuilder= docFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			/* Creating DDX instance */
			LOG.info("Creating DDX document");
			org.w3c.dom.Document ddx = documentBuilder.newDocument();
			
			/* Creating DDX */
			Element rootElement = ddx.createElementNS("http://ns.adobe.com/DDX/1.0/", "DDX");
			ddx.appendChild(rootElement);
			
			/* Creating result tag */
			Element pdfResult = ddx.createElement("PDF");
			pdfResult.setAttribute("result", "out.pdf");
			rootElement.appendChild(pdfResult);
			
			/* Creating first PDF child tag */ 
			Element pdf1 = ddx.createElement("PDF");
			pdf1.setAttribute("source", "sample1.pdf");
			pdfResult.appendChild(pdf1);
			
			/* Creating second PDF child tag */ 
			Element pdf2 = ddx.createElement("PDF");
			pdf2.setAttribute("source", "sample2.pdf");
			pdfResult.appendChild(pdf2);
			LOG.info("DDX Created");
			return orgw3cDocumentToAEMFDDocument(ddx);
		}
		catch(DOMException e) {
			LOG.error("Error occured while creating DDX document {} ", e.getMessage());
		}
		return null;
	}
	
	public Document orgw3cDocumentToAEMFDDocument( org.w3c.dom.Document xmlDocument)
    {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DOMSource source = new DOMSource(xmlDocument);
        LOG.info("In orgW3CDocumentToAEMFDDocument method");
        StreamResult outputTarget = new StreamResult(outputStream);
        try{
          TransformerFactory.newInstance().newTransformer().transform(source, outputTarget);
          InputStream is1 = new ByteArrayInputStream(outputStream.toByteArray());
          Document xmlAEMFDDocument = new Document(is1);
          return xmlAEMFDDocument;
       }
       catch (Exception e)
	    {
	       LOG.error("Error in generating ddx " + e.getMessage());
	    }
        return null;
     }
}