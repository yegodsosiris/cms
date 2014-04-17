package com.rdfgroup.cms.jsp.taglib;

import javax.servlet.jsp.JspException;

public class IncludeTemplateForModelTag extends BaseContentTag {

    private static final long serialVersionUID = 8067191996065177229L;
    
    private Object customModel;
    
    private String modelKey;

    @Override 
    public int doStartTag() throws JspException {
        return super.processModel(modelKey, customModel);
    }

    public String getModelKey() {
        return modelKey;
    }

    public void setModelKey(String modelKey) {
        this.modelKey = modelKey;
    }

    public Object getCustomModel() {
        return customModel;
    }

    public void setCustomModel(Object customModel) {
        this.customModel = customModel;
    }

}
