package com.rdfgroup.cms.velocity.directive;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.parser.node.Node;

import com.rdfgroup.cms.services.domain.Website;

public class IncludeContentListUserDirective extends BaseUserDirective {

    public String getName() {
        return "includeContentList";
    }

    public int getType() {
        return LINE;
    }
    
    /**
     * Processes a templateKey (id for a template in the Mongo DB) and a contentKey (id for a piece of
     * content from the CMS). The content key is used to fetch the relevant content which is then passed
     * to processDirective() to be processed as the underlying object for Velocity to render.
     */
	public boolean render(InternalContextAdapter context, Writer writer, Node node) 
            throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
		
		String usage = "Directive usage: #includeContentList('template', 'contentType', 'modelKey', $vmodel)";
		if (node.jjtGetNumChildren() !=4) {
			return processError(context, node, writer, usage);
		}
    	
        String templateKey = getString(context, node, 0);
        String modelKey = getString(context, node, 2);
        Map<String, Object> vModel = getVModel(context, node, 3);
        Website website = (Website) vModel.get(Website.KEY);
        List<Map<String, Object>> model = cmsClient.getContentList(getString(context, node, 1), website.getLanguage());
		
		return processDirective(
        		templateKey, 
        		model, 
        		modelKey,
        		vModel, 
        		writer
        );

    }

}
