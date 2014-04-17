package com.rdfgroup.cms.services.domain;

import java.io.Serializable;
import java.util.Locale;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("session")
public class Website implements Serializable{

	public final static String KEY = "website";
	
	private static final long serialVersionUID = -1718143215377806917L;
	private String name;
	private String language;
	
	public String getLanguage() {
		return language;
	}
	
	public Website(Locale locale) {
		this.name=locale.getDisplayLanguage();
		this.language=locale.getLanguage();
	}
	
	public String getName() {
		return name;
	}

}
