package com.properties;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesContext {
	String path;
	Properties prop;
	public PropertiesContext() throws Exception{
		path = "/system.properties";
		prop=new Properties();
		InputStream is = PropertiesContext.class.getResourceAsStream(path);
		prop.load(is);
	}
	
	public Properties getProp(){
		return prop;
	}	
}
