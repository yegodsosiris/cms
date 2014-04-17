package com.rdfgroup.cms.services.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;



@Component
@Scope("session")
public class Website implements Serializable{

	public final static String KEY = "website";
	public final static String DEFAULT_WEBSITE = "gb";
	public final static String GB_WEBSITE = "gb";
	public final static String US_WEBSITE = "us";
	public final static String DE_WEBSITE = "de";
	public final static String FR_WEBSITE = "fr";
	public final static String HK_WEBSITE = "hk";
	public final static String ES_WEBSITE = "es";
	public final static String ALL_WEBSITES = "all";
	public final static String NULL_WEBSITE = "";

	public final static String ENGLISH = "en";
	public final static String SPANISH = "es";
	public final static String GERMAN = "de";
	
	private static Map<String, Website> websites = new HashMap<String, Website>();
	
	static {
		websites.put(DEFAULT_WEBSITE, new Website(DEFAULT_WEBSITE, ENGLISH));
		websites.put(GB_WEBSITE, new Website(GB_WEBSITE, ENGLISH));
		websites.put(DE_WEBSITE, new Website(DE_WEBSITE, GERMAN));
		websites.put(US_WEBSITE, new Website(US_WEBSITE, ENGLISH));
		websites.put(FR_WEBSITE, new Website(FR_WEBSITE, ENGLISH));
		websites.put(HK_WEBSITE, new Website(HK_WEBSITE, ENGLISH));
		websites.put(ES_WEBSITE, new Website(ES_WEBSITE, SPANISH));
		websites.put(ALL_WEBSITES, new Website(ALL_WEBSITES, ENGLISH));
		websites.put(NULL_WEBSITE, new Website(NULL_WEBSITE, ENGLISH));
	}
	
	public static Website getWebsite(String website) {
		return websites.get(website);
	}
	
	private static final long serialVersionUID = -1718143215377806917L;
	private String name;
	private String language;
	
	public String getLanguage() {
		return language;
	}
	
	private Website() {
		
	}
	
	private Website(String name, String language) {
		this.name=name;
		this.language=language;
	}
	
	public String getName() {
		return name;
	}

}
