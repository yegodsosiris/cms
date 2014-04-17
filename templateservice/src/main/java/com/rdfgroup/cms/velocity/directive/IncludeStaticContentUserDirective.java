package com.rdfgroup.cms.velocity.directive;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.parser.node.Node;

public class IncludeStaticContentUserDirective extends BaseUserDirective {

    private final String usage = "Directive usage: #includeContent('template', 'contentId', 'modelKey', $vmodel)";

	public String getName() {
        return "includeStaticContent";
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

		if (node.jjtGetNumChildren() !=4) {
			return processError(context, node, writer, usage);
		}

		String templateKey = getString(context, node, 0);
		String contentId = getString(context, node, 1);
		String modelKey = getString(context, node, 2);
		Map<String, Object> contentById = cmsClient.getContentById(contentId);
        Map<String, Object> vModel = getVModel(context, node, 3);
        
		return processDirective(
        		templateKey, 
        		contentById, 
        		modelKey,
        		vModel, 
        		writer
        );

    }

}
