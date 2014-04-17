package com.rdfgroup.cms.cmsclient;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class CmsClientServiceFactory implements FactoryBean<CmsClientService> {
    
    @Autowired 
    private AutowireCapableBeanFactory factory;
    
    @Value("${cms.client.class}")
    private String cmsClientClassName;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public CmsClientService getObject() throws Exception {
        Class classz = Class.forName(cmsClientClassName);
        return (CmsClientService) factory.createBean(classz);
    }

    @Override
    public Class<?> getObjectType() {
        return CmsClientService.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
