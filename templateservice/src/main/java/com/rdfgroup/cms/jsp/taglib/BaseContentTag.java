package com.rdfgroup.cms.jsp.taglib;

import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.rdfgroup.cms.cmsclient.CmsClientService;
import com.rdfgroup.cms.services.client.service.PublicTemplateService;
import com.rdfgroup.cms.services.domain.Website;
import com.rdfgroup.cms.services.domain.template.Template;

@Configurable
public abstract class BaseContentTag extends TagSupport {
    
    private static final long serialVersionUID = 4104103206115759502L;
    
    public static final String LOG = "LOG";
    public static final String MODEL = "vmodel";

    @Autowired
    private VelocityEngine velocityEngine;
    
    @Autowired
    private PublicTemplateService templateService;

    @Autowired
    protected CmsClientService cmsClient;
    
    protected Map<String, Object> vmodel;
    
    private String templateId;
    
    protected int processModel(String modelKey, Object cmsContentModel) throws JspException {
        JspWriter out = pageContext.getOut();
        Template template = templateService.getTemplateByKey(templateId, (Website) vmodel.get(Website.KEY));
        
        VelocityContext context = new VelocityContext();
        context.put(MODEL, vmodel);
        context.put(modelKey, cmsContentModel);
        
        velocityEngine.evaluate(context, out, LOG, template.getContent());     
        return super.doStartTag();
        
    }
    
    public Map<String, Object> getVmodel() {
        return vmodel;
    }

    public void setVmodel(Map<String, Object> vmodel) {
        this.vmodel = vmodel;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

}
