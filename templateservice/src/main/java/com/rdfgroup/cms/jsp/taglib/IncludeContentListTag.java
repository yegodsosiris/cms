package com.rdfgroup.cms.jsp.taglib;

import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;

import com.rdfgroup.cms.services.domain.Website;

public class IncludeContentListTag extends BaseContentTag {

    private static final long serialVersionUID = 8067191996065177229L;
    
    private String contentType;
    
    private String modelKey;

    @Override 
    public int doStartTag() throws JspException {
        Website website = (Website) vmodel.get(Website.KEY);
        List<Map<String,Object>> model = cmsClient.getContentList(contentType, website.getLanguage());
        return super.processModel(modelKey, model);
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getModelKey() {
        return modelKey;
    }

    public void setModelKey(String modelKey) {
        this.modelKey = modelKey;
    }

}
