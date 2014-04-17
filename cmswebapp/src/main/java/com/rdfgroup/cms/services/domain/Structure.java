package com.rdfgroup.cms.services.domain;

import java.util.HashMap;
import java.util.Map;

public class Structure {
	private Map<String, Object> fields = new HashMap<String, Object>();
	
	public Map<String, Object> getFields() {
		return fields;
	}
	
	public void setFields(Map<String, Object> fields) {
		this.fields = fields;
	}
	
}
