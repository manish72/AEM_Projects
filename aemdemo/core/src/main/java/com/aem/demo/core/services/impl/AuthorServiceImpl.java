package com.aem.demo.core.services.impl;

import java.io.InputStream;

import javax.jcr.Binary;
import javax.jcr.Node;
import javax.jcr.Session;
import javax.jcr.ValueFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.demo.core.services.AuthorService;
import com.aem.demo.core.services.AuthorServiceConfig;
import com.aem.demo.core.utils.ResolverUtil;
import com.aem.demo.core.utils.ServiceUtil;
import com.aem.demo.core.constants.Constants;

@Component(
        service = AuthorService.class,
        name = "AuthorService",
        immediate = true
)
public class AuthorServiceImpl implements AuthorService{
	private static final Logger LOG = LoggerFactory.getLogger(AuthorServiceImpl.class);

	@Reference
	AuthorServiceConfig authorServiceConfig;
	
	@Reference
	ResourceResolverFactory resourceResolverFactory;
	
	
	@Override
	public String createAuthorNode(String country, SlingHttpServletRequest request) {
		// TODO Auto-generated method stub
		String nodeCreated = StringUtils.EMPTY;
		try {
			AuthorServiceConfig config = authorServiceConfig.getCountryConfig(country);
			String nodeLocation = config.getNodePath() + "/" + config.getNodeName();
			ResourceResolver resourceResolver = ResolverUtil.newResolver(resourceResolverFactory);
			Session session = resourceResolver.adaptTo(Session.class);
			if(session.nodeExists(nodeLocation)) {
				nodeCreated = addAuthor(session,request,nodeLocation);
			}
			else {
				addParentNode(session,config);
				nodeCreated = addAuthor(session,request,nodeLocation);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			LOG.error("\n Error while creating node - {} ",e.getMessage());
		}
		return nodeCreated;
	}


	private String addAuthor(Session session,SlingHttpServletRequest request,String nodeLocation){
      try {
          Node parentNode = session.getNode(nodeLocation);
          if (!parentNode.hasNode(getNodeName(request))) {
              Node authorNode = parentNode.addNode(getNodeName(request), Constants.AUTHORNODE_TYPE);
              authorNode.setProperty("fname", ServiceUtil.getRequestParamter(request, "fname"));
              authorNode.setProperty("lname", ServiceUtil.getRequestParamter(request, "lname"));
              authorNode.setProperty("email", ServiceUtil.getRequestParamter(request, "email"));
              authorNode.setProperty("phone", ServiceUtil.getRequestParamter(request, "phone"));
              authorNode.setProperty("books", request.getParameter("books").split(","));
              addThumbnail(authorNode, request);
              session.save();
              return authorNode.getName() + " added.";
          } else {
              return "This author already exists.";
          }
      }catch (Exception e){
          LOG.error("\n Error while creating Author node ");
      }
      return null;
    }
	
    private String addParentNode(Session session,AuthorServiceConfig config){
        try {
            if(session.nodeExists(config.getNodePath())){
                Node gParentNode=session.getNode(config.getNodePath());
                Node parentNode=gParentNode.addNode(config.getNodeName(),Constants.AUTHORNODE_TYPE);
                session.save();
                return parentNode.getName();
            }
        }catch (Exception e){
            LOG.error("\n Error while creating Parent node ");
        }
        return null;
    }
    
    private String getNodeName(SlingHttpServletRequest request){
        String fName=request.getParameter("fname");
        String lName=request.getParameter("lname");
        String email=request.getParameter("email");
        String[] books=request.getParameter("books").split(",");
        String authorNodeName=fName+"-"+lName+"-"+email;
        return authorNodeName;
    }
    
    private boolean addThumbnail(Node node,SlingHttpServletRequest request){
        try {
            ResourceResolver resourceResolver = ResolverUtil.newResolver(resourceResolverFactory);
            RequestParameter rp = request.getRequestParameter("file");
            InputStream is = rp.getInputStream();
            Session session=resourceResolver.adaptTo(Session.class);
            ValueFactory valueFactory=session.getValueFactory();
            Binary imageBinary=valueFactory.createBinary(is);
            Node photo=node.addNode("photo","sling:Folder");
            Node file=photo.addNode("image","nt:file");
            Node content = file.addNode("jcr:content", "nt:resource");
            content.setProperty("jcr:mimeType", rp.getContentType());
            content.setProperty("jcr:data", imageBinary);
            return true;

        }catch (Exception e){
            LOG.info("\n ERROR while add Thumbnail - {} ",e.getMessage());
        }
        return false;
    }

}
