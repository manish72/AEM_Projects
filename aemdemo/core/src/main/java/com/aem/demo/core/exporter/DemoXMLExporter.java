package com.aem.demo.core.exporter;

import java.io.StringWriter;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.sling.models.export.spi.ModelExporter;
import org.apache.sling.models.factory.ExportException;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = ModelExporter.class)
public class DemoXMLExporter implements ModelExporter{
	private static final Logger LOG = LoggerFactory.getLogger(DemoXMLExporter.class);

	@Override
	public <T> T export(Object model, Class<T> aClass, Map<String, String> options) throws ExportException {
		// TODO Auto-generated method stub
		StringWriter stringWriter = new StringWriter();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(model.getClass());
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(model, stringWriter);
		}
		catch(JAXBException e) {
			LOG.info("\n Marshall Error : {} ",e.getMessage());
		}
		
		return (T) stringWriter.toString();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "demoxml";
	}

	@Override
	public boolean isSupported(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}

}
