package com.rdfgroup.cms.velocity.directive;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.parser.node.Node;


public class IncludeTemplateUserDirective extends BaseUserDirective {

    private final String usage = "Directive usage: #includeTemplate('templateKey', $vmodel)";

	public String getName() {
        return "includeTemplate";
    }

    public int getType() {
        return LINE;
    }

    /**
     * Processes a templateKey (id for a template in the Mongo DB) and an object. The object is 
     * passed untouched to processDirective() to be rendered by Velocity.
     */
	public boolean render(InternalContextAdapter context, Writer writer, Node node) 
            throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {

		if (node.jjtGetNumChildren() !=2) {
			return processError(context, node, writer, usage);
		}
		String templateKey = getString(context, node, 0);
		Map<String, Object> vModel = getVModel(context, node, 1);

		return processDirective(
				templateKey,
				null,
				null,
				vModel, 
				writer
		);
        
    }

}
