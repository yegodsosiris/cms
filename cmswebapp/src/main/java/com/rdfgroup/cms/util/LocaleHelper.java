package com.rdfgroup.cms.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class LocaleHelper {
	
	private static List<Locale> list = new ArrayList<Locale>();

	private static Comparator<Locale> comp = new Comparator<Locale>() {
		public int compare(Locale t1, Locale t2) {
			return t1.getDisplayLanguage().compareTo(t2.getDisplayLanguage());
		}

	};
	
	static {
		List<String> usedLanguages = new ArrayList<String>();
		usedLanguages.add("Chinese");
		usedLanguages.add("Dutch");
		usedLanguages.add("English");
		usedLanguages.add("French");
		usedLanguages.add("German");
		usedLanguages.add("Italian");
		usedLanguages.add("Spanish");
		Locale[] locales = Locale.getAvailableLocales();
		Map<String, Locale> map = new LinkedHashMap<String, Locale>();
		List<Locale> all = Arrays.asList(locales);
		Collections.sort(all, comp);
		for (Locale locale : all) {
			if (usedLanguages.contains(locale.getDisplayLanguage())) {
				map.put(locale.getDisplayLanguage(), locale);
			}
		}
		list.addAll(map.values());
	}

	public static List<Locale> getLocals() {
		return list;
	}
	
	public static Locale getLocale(String language) {
		for (Locale locale : list) {
			if (locale.getLanguage().equals(language)) {
				return locale;
			}
		}
		return null;
	}
	
	

}
