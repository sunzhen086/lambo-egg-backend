/*
 * Created on 2004-4-28
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.lambo.schedule;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.log4j.Logger;


/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PropertyJDBC {
	static Logger Log = Logger.getLogger(PropertyJDBC.class.getName());
    final static String JDBC_DATASOURCE_DRIVERCLASSNAME="master.jdbc.driver";
    final static String JDBC_DATASOURCE_URL="master.jdbc.url";
    final static String JDBC_DATASOURCE_USERNAME="master.jdbc.username";
    final static String JDBC_DATASOURCE_PASSWORD="master.jdbc.password";
	private static Properties prop = new Properties();

	public static void initProperty( Properties props ){
		prop = props;
	}
	

	/**
	 *	This method gets the value of the given property
	 *	@param property
	 *  @return value
	 */
	public static String getProperty(String property) {
		return prop.getProperty(property);
	}

	/**
	 *	This method sets the given property
	 *	@param property
	 *  @param value
	 */
	public void setProperty(String property, String value) {
		prop.setProperty(property, value);
	}

	/**
	 *	This method Stores in the properties File the given property and all the
	 *  "live" properties
	 *	@param property
	 *  @param value
	 */
	public static void storeProperty(String property, String value, String strFileName) {
		prop.setProperty(property, value);
		try {
			File filez = new File(strFileName);
			filez.delete();
			OutputStream out = new FileOutputStream(filez);
			prop.store(out, "Jcrontab Automatic Properties");
		} catch (Exception e) {
			Log.error(e.toString(), e);
		}
	}
	
	/**
	 * @return
	 */
	public static String getDBUrl() {
		// TODO Auto-generated method stub
		return prop.getProperty(JDBC_DATASOURCE_URL);
	}
	/**
	 * @return
	 */
	public static String getDBUser() {
		// TODO Auto-generated method stub
		return prop.getProperty(JDBC_DATASOURCE_USERNAME);
	}
	/**
	 * @return
	 */
	public static String getDBPass() {
		// TODO Auto-generated method stub
		return prop.getProperty(JDBC_DATASOURCE_PASSWORD);
	}
	/**
	 * @return
	 */
	public static String getDBDriver() {
		// TODO Auto-generated method stub
		return prop.getProperty(JDBC_DATASOURCE_DRIVERCLASSNAME);
	}
}
