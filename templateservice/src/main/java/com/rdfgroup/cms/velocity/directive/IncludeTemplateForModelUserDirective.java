package com.rdfgroup.cms.velocity.directive;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.parser.node.Node;


public class IncludeTemplateForModelUserDirective extends BaseUserDirective {

    private final String usage = "Directive usage: #includeTemplate('templateKey', $customModel, $modelKey, $vmodel)";

	public String getName() {
        return "includeTemplateForModel";
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

		if (node.jjtGetNumChildren() !=4) {
			return processError(context, node, writer, usage);
		}
		String templateKey = getString(context, node, 0);
		Object customModel = node.jjtGetChild(1).value(context);
		String modelKey = getString(context, node, 2);
		Map<String, Object> vModel = getVModel(context, node, 3);

		return processDirective(
				templateKey,
				customModel,
				modelKey,
				vModel, 
				writer
		);
	}

}
