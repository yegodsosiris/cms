package com.rdfgroup.cms.velocity.directive.helper;

import java.util.HashMap;
import java.util.Map;

public class DotCMSLanguageHelper {

	private static Map<String, String> codeToId = new HashMap<String, String>();
	private static Map<String, String> idToCode = new HashMap<String, String>();

	static {
		codeToId.put("en", "1");
		codeToId.put("es", "2");
		idToCode.put("1", "en");
		idToCode.put("2", "es");
	}

	public static String getLanguageIdFromCode(String code) {
		return codeToId.get(code);
	}

	public static String getCodeFromLanguageId(String id) {
		return idToCode.get(id);
	}
	
}
