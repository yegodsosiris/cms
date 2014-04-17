package com.rdfgroup.cms.velocity.directive;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.parser.node.Node;

import com.rdfgroup.cms.util.JacksonHelper;
import com.rdfgroup.cms.velocity.directive.helper.VUser;

public class AclUserDirective extends BaseUserDirective {

	private final String usage = "Directive usage: #acl(<Role>, $vmodel)";

	public String getName() {
		return "acl";
	}

	public int getType() {
		return BLOCK;
	}

	public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException, ResourceNotFoundException, ParseErrorException,
			MethodInvocationException {

		if (node.jjtGetNumChildren() != 3) {
			return processError(context, node, writer, usage);
		}

		String acl = getString(context, node, 0);
		Map<String, Object> vModel = getVModel(context, node, 1);

		VUser vuser = JacksonHelper.getObjectFromJson(vModel.get("currentuser").toString(), VUser.class);
		List<String> roles = vuser.getRoles();
		for (String role : roles) {
			if (role.equals(acl)) {

				// reading block content and rendering it
				StringWriter blockContent = new StringWriter();
				node.jjtGetChild(2).render(context, blockContent);

				String content = blockContent.toString();
				writer.write(content);
			}
		}

		return true;

	}

}
