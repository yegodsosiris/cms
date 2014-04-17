package com.rdfgroup.cms.config;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

public class PropertiesLoader {
	private static final String VELOCITY_ROOT = "velocity.root";
	private static final String TEMPLATING_PROPERTIES = "templating.properties";
	private static final String CMS_HOST = "cms.host";
	private static final String MONGO_PASSWORD = "mongo.password";
	private static final String MONGO_USERNAME = "mongo.username";
	private static final String MONGO_DATABASE = "mongo.db";
	private static final String MONGO_HOST = "mongo.host";
	private static final String MONGO_PORT = "mongo.port";

	private static Properties prop;

	private static Properties get() {

		try {
			if (prop == null) {
				prop = new Properties();
				prop.load(PropertiesLoader.class.getClassLoader().getResourceAsStream(TEMPLATING_PROPERTIES));
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return prop;
	}

	public static String getVelocityRoot() {
		return getStringProperty(VELOCITY_ROOT);
	}

	public static String getCmsHost() {
		return getStringProperty(CMS_HOST);
	}

	public static String getMongoPassword() {
		return getStringProperty(MONGO_PASSWORD);
	}

	public static String getMongoUsername() {
		return getStringProperty(MONGO_USERNAME);
	}

	public static String getMongoDb() {
		return getStringProperty(MONGO_DATABASE);
	}

	public static String getMongoHost() {
		return getStringProperty(MONGO_HOST);
	}

	public static int getMongoPort() {
		String stringProperty = getStringProperty(MONGO_PORT);
		return Integer.valueOf(stringProperty);
	}

	private static String getStringProperty(String key) {
		String r = (String) get().get(key);
		if (StringUtils.isBlank(r)) {
			error(key);
		}
		return r;
	}


	private static void error(String key) {
		throw new IllegalStateException(String.format("% property not set within %s", key, TEMPLATING_PROPERTIES));
	}
}