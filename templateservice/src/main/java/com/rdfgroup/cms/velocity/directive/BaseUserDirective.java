package com.rdfgroup.cms.velocity.directive;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.rdfgroup.cms.cmsclient.CmsClientService;
import com.rdfgroup.cms.services.client.service.PublicTemplateService;
import com.rdfgroup.cms.services.domain.Website;
import com.rdfgroup.cms.services.domain.template.Template;
import com.rdfgroup.cms.velocity.mvc.VelocityWriter;

@Configurable
public abstract class BaseUserDirective extends Directive {
	
	protected final String VELOCITY_ERROR_TEMPLATE_KEY = "velocity_error";

    @Autowired
    private VelocityEngine velocityEngine;
    
    @Autowired
    private PublicTemplateService templateService;
    
    @Autowired
    protected CmsClientService cmsClient;

	protected String getString(InternalContextAdapter context, Node node, int index) {
		return String.valueOf(node.jjtGetChild(index).value(context));
	}
	

    
    protected boolean validateParams(Node node, int size) {
    	return node.jjtGetNumChildren() == size;
    }
	
    
    protected boolean processError( InternalContextAdapter context, Node node, Writer writer, String message) throws IOException {
		Map<String, Object> vModel = new HashMap<String, Object>();
		vModel.put(Website.KEY, Website.getWebsite(Website.DEFAULT_WEBSITE));
	    String directiveResultKey = "error";
		Object directiveResultModel = String.format("Failed to render %s for %s. %s", getString(context, node, 0),getString(context, node, 1), message);
		return processDirective(VELOCITY_ERROR_TEMPLATE_KEY, directiveResultModel, directiveResultKey, vModel, writer);
		
	}


	@SuppressWarnings("unchecked")
	protected Map<String, Object> getVModel(InternalContextAdapter context, Node node, int index) {
		Object value = node.jjtGetChild(index).value(context);
		Map<String, Object> vmodel = (Map<String, Object>) value;
		return vmodel;
	}


	private Website getWebsite(Map<String, Object> vModel) {
		return (Website) vModel.get(Website.KEY);
	}
	
	/**
	 * Grabs the template from Mongo and then constructs the necessary classes for velocity
	 * to then render (evaluate) the template against the supplied model. Velocity will look
	 * for the variable 'vmodel' declared within the template and map it against the incoming model.
	 * 
	 * The result is then appended to the writer in preparation for display or for further nested
	 * velocity processing.
	 */
	protected boolean processDirective(String templateKey, Object directiveResultModel, String directiveResultKey, Map<String, Object> vModel, Writer writer) throws IOException {
	    Template template = null;
	    try {
	        template = templateService.getTemplateByKey(templateKey, getWebsite(vModel));
	    } catch(ResourceNotFoundException e) {
            template = templateService.getTemplateByKey(VELOCITY_ERROR_TEMPLATE_KEY, Website.getWebsite(Website.DEFAULT_WEBSITE));
            directiveResultModel = "Unable to load template (" + templateKey + ") from the database";
            directiveResultKey = "error";
	    }
		
	    VelocityContext velocityContext = new VelocityContext();
	    velocityContext.put(directiveResultKey, directiveResultModel);
		velocityContext.put(VelocityWriter.MODEL, vModel);
		
		velocityEngine.evaluate(velocityContext, writer, VelocityWriter.LOG, template.getContent());
		
		return true;
	}

}
