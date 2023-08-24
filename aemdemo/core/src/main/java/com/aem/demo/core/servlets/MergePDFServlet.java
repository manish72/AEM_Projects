package com.aem.demo.core.servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.Part;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aemfd.docmanager.Document;
import com.adobe.fd.assembler.client.AssemblerOptionSpec;
import com.adobe.fd.assembler.client.AssemblerResult;
import com.adobe.fd.assembler.service.AssemblerService;
import com.adobe.fd.forms.api.FormsService;

@Component(
		service={Servlet.class}
)
@SlingServletPaths(
		value = {"/bin/mergepdf"}
)
public class MergePDFServlet extends SlingAllMethodsServlet{
	private static final Logger LOG = LoggerFactory.getLogger(MergePDFServlet.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Reference
	FormsService formsService;
	
	@Reference
	AssemblerService assemblerService;
	
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		/* Converting DDX to input stream */
		Part ddxFile = request.getPart("sampleDDX.ddx");
		LOG.info("DDX received");
		InputStream ddxIS = ddxFile.getInputStream();
		Document ddxDocument = new Document(ddxIS);
		
		/* Converting sample1 pdf to input stream */
		Part pdf1 = request.getPart("sample1.pdf");
		LOG.info("PDF1 received");
		InputStream pdf1IS = pdf1.getInputStream();
		Document pdf1Document = new Document(pdf1IS);
		
		/* Converting sample2 pdf to input stream */
		Part pdf2 = request.getPart("sample2.pdf");
		LOG.info("PDF2 received");
		InputStream pdf2IS = pdf2.getInputStream();
		Document pdf2Document = new Document(pdf2IS);
		
		/* Adding Documents into Map */
		Map<String,Object> mapofDocuments = new HashMap<String,Object>();
		mapofDocuments.put("sample1.pdf", pdf1Document);
		mapofDocuments.put("sample2.pdf", pdf2Document);
		
		AssemblerOptionSpec assemblerOptionSpec = new AssemblerOptionSpec();
		
		try {
			LOG.info("Inside try block");
			AssemblerResult assemblerResult = assemblerService.invoke(ddxDocument, mapofDocuments, assemblerOptionSpec);
			LOG.info("Completed Assembler service invokation");
			Document finalPDF = assemblerResult.getDocuments().get("out.pdf");
			LOG.info("Document Retreived");
			finalPDF.copyToFile(new File("C:/Users/manis/OneDrive/Desktop/Assembler/AssemblerFinalOut.pdf"));
			LOG.info("File saved successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOG.error("Error occured {} ",e.getMessage());
		}
		
	}

}
