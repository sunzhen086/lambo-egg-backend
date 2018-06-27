package com.lambo.schedule.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;


public class ResourceBundle {
	private static Logger logger = Logger.getLogger(ResourceBundle.class.getName());
	private static Map resourceBundles=new HashMap();
	private java.util.ResourceBundle resourceBundle = null;
	
	private ResourceBundle(String baseName){
		this.resourceBundle = java.util.ResourceBundle.getBundle(baseName);
	}
	public static ResourceBundle getBundle(String baseName){
		if(resourceBundles.get(baseName)==null){
			resourceBundles.put(baseName,new ResourceBundle(baseName));
		}
		return (ResourceBundle) resourceBundles.get(baseName);
	}
	
	public String getString(String key ){
		String value=resourceBundle.getString(key);
		try {
			return new String(value.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
		return null;
	}
	

}
