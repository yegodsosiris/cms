package com.rdfgroup.cms.jsp.taglib;

import java.util.Map;

import javax.servlet.jsp.JspException;

import com.rdfgroup.cms.services.domain.Website;

public class IncludeContentTag extends BaseContentTag {

    private static final long serialVersionUID = 8067191996065177229L;
    
    private String contentId;
    
    private String modelKey;

    @Override 
    public int doStartTag() throws JspException {
        Website website = (Website) vmodel.get(Website.KEY);
        Map<String, Object> contentById = cmsClient.getContentByUid(contentId, website.getLanguage());
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
