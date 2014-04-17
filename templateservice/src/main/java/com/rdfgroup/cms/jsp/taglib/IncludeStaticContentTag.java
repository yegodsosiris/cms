package com.rdfgroup.cms.jsp.taglib;

import java.util.Map;

import javax.servlet.jsp.JspException;

public class IncludeStaticContentTag extends BaseContentTag {

    private static final long serialVersionUID = 8067191996065177229L;
    
    private String contentId;
    
    private String modelKey;

    @Override 
    public int doStartTag() throws JspException {
        Map<String, Object> contentById = cmsClient.getContentById(contentId);
        return super.processModel(modelKey, contentById);
    }

    public String getModelKey() {
        return modelKey;
    }

    public void setModelKey(String modelKey) {
        this.modelKey = modelKey;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

}
