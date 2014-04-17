package com.rdfgroup.cms.jsp.taglib;

import javax.servlet.jsp.JspException;

public class IncludeTemplateTag extends BaseContentTag {

    private static final long serialVersionUID = 8067191996065177229L;
    public static final String LOG = "LOG";
    public static final String MODEL = "vmodel";

    @Override 
    public int doStartTag() throws JspException {
        return super.processModel(null, null);
    }

}
