package com.rdfgroup.cms.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ui.velocity.VelocityEngineFactory;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;
import org.springframework.web.servlet.view.velocity.VelocityConfig;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityToolboxView;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

@Configuration
@EnableWebMvc
@PropertySource("classpath:templating.properties")
public class ApplicationConfiguration {

    @Value("${velocity.root}")
    private String velocityRoot;

	@Bean
	public VelocityEngine getVelocityEngine() throws VelocityException,
			IOException {

		VelocityEngineFactory factory = new VelocityEngineFactory();
		factory.setVelocityProperties(getVelocityProperties());
		return factory.createVelocityEngine();
	}
	
	@Bean
    static public PropertySourcesPlaceholderConfigurer myPropertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer p = new PropertySourcesPlaceholderConfigurer();
        p.setLocation(new ClassPathResource("templating.properties"));
        return p;
    }	

	private Properties getVelocityProperties() {

		List<String> userDirectives = new ArrayList<String>();
		userDirectives.add("com.rdfgroup.cms.velocity.directive.IncludeTemplateForModelUserDirective");
		userDirectives.add("com.rdfgroup.cms.velocity.directive.IncludeTemplateUserDirective");
		userDirectives.add("com.rdfgroup.cms.velocity.directive.IncludeContentListUserDirective");
		userDirectives.add("com.rdfgroup.cms.velocity.directive.IncludeContentUserDirective");
		userDirectives.add("com.rdfgroup.cms.velocity.directive.IncludeStaticContentUserDirective");
		userDirectives.add("com.rdfgroup.cms.velocity.directive.AclUserDirective");
		Properties props = new Properties();
		props.put("userdirective", StringUtils.join(userDirectives, ","));
		return props;
	}

	/**
	 * Not used directly anywhere.... just needs to be defined for velocity to work.
	 * 
	 * @return
	 * @throws VelocityException
	 * @throws IOException
	 */
	@Bean
	public VelocityConfig velocityConfig() throws VelocityException, IOException {
		VelocityConfigurer cfg = new VelocityConfigurer();
		cfg.setResourceLoaderPath(velocityRoot);
		cfg.setVelocityProperties(getVelocityProperties());
		return cfg;
	}

	
    @Bean(name = "viewResolver")
    public ContentNegotiatingViewResolver viewResolver() {
        ContentNegotiatingViewResolver contentNegotiatingViewResolver = new ContentNegotiatingViewResolver();
        contentNegotiatingViewResolver.setIgnoreAcceptHeader(false);
		contentNegotiatingViewResolver.setViewResolvers(getViewResolvers());
        contentNegotiatingViewResolver.setDefaultViews(getDefaultViews());
        return contentNegotiatingViewResolver;
    }
    
    private List<ViewResolver> getViewResolvers() {
    	List<ViewResolver> viewResolvers = new ArrayList<ViewResolver>();
    	VelocityViewResolver resolver = new VelocityViewResolver();
   	 	resolver.setViewClass(VelocityToolboxView.class);
   	 	resolver.setSuffix(".vm");
   	 	viewResolvers.add(resolver);
   	 	return viewResolvers;
    }
    
    private List<View> getDefaultViews() {
    	List<View> defaultViews = new ArrayList<View>();
    	MappingJacksonJsonView mappingJacksonJsonView = new MappingJacksonJsonView();
        mappingJacksonJsonView.setContentType("application/json");
        defaultViews.add(mappingJacksonJsonView);
        return defaultViews;
    }


}