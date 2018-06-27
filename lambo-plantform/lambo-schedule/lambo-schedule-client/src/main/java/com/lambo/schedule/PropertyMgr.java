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
public class PropertyMgr {
	static Logger Log = Logger.getLogger(PropertyMgr.class.getName());
	final static String TASK_RUNNER = "lambo.schedule.taskRunner";
	final static String SCHEDULE_VIEW = "lambo.schedule.taskView";
	final static String DATA_SOURCE = "lambo.schedule.data.datasource";
	final static String DATA_FILE = "lambo.schedule.data.file";
	final static String REFRESH = "lambo.schedule.task.refreshFrequency";
	final static String MAIL_TO = "lambo.schedule.sendMail.to";
	final static String MAIL_FROM = "lambo.schedule.sendMail.from";
	final static String MAIL_HOST = "lambo.schedule.sendMail.smtp.host";
	final static String MAIL_USER = "lambo.schedule.sendMail.smtp.username";
	final static String MAIL_PASS = "lambo.schedule.sendMail.smtp.password";
	final static String HOLIDAY_SOURCE = "lambo.schedule.data.holidaysource";
	final static String HOLIDAY_FILE = "lambo.schedule.data.holidaysfilesource";
	final static String DATE_FORMAT = "lambo.schedule.data.dateFormat";
    final static String DATA_FILE_CHARSET = "lambo.schedule.data.file.charset";
    final static String JDBC_DATASOURCE_JNDI="lambo.schedule.jdbc.datasource.jndi";
    final static String JDBC_DATASOURCE_DRIVERCLASSNAME="lambo.schedule.jdbc.datasource.driverClassName";
    final static String JDBC_DATASOURCE_URL="lambo.schedule.jdbc.datasource.url";
    final static String JDBC_DATASOURCE_USERNAME="lambo.schedule.jdbc.datasource.username";
    final static String JDBC_DATASOURCE_PASSWORD="lambo.schedule.jdbc.datasource.password";
    final static String JDBC_DATASOURCE_INCONTAINER="lambo.schedule.jdbc.datasource.incontainer";
    final static String JDBC_DATASOURCE_MAXACTIVE="lambo.schedule.jdbc.datasource.maxActive";
    final static String JDBC_DATASOURCE_PROPERTY="lambo.schedule.jdbc.datasource.";
    final static String HSF_SERVICE_TIMEOUT="lambo.schedule.hsf.service.timeout";
    

	private static Properties prop = new Properties();

	public static String getTaskRunner() {
		return getProperty(TASK_RUNNER);
	}
	public static String getTaskView() {
		return getProperty(SCHEDULE_VIEW);
	}
	public static String getDataSource() {
		return getProperty(DATA_SOURCE);
	}
	public static String getDataFile() {
		return getProperty(DATA_FILE);
	}
	public static String getRefresh() {
		return getProperty(REFRESH);
	}
	public static String getMailTo() {
		return getProperty(MAIL_TO);
	}
	public static String getMailFrom() {
		return getProperty(MAIL_FROM);
	}
	public static String getMailHost() {
		return getProperty(MAIL_HOST);
	}
	public static String getMailUser() {
		return getProperty(MAIL_USER);
	}
	public static String getMailPass() {
		return getProperty(MAIL_PASS);
	}
	public static String getHolidaySource() {
		return getProperty(HOLIDAY_SOURCE);
	}
	public static String getHolidayFile() {
		return getProperty(HOLIDAY_FILE);
	}
	public static String getDateFormat() {
		return getProperty(DATE_FORMAT);
	}
    public static String getDataFileCharset() {
        return getProperty(DATA_FILE_CHARSET);
    }
    public static String getHsfServiceTimeout() {
        return getProperty(HSF_SERVICE_TIMEOUT);
    }
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
	/**
	 * @return
	 */
	public static String getDBJndi() {
		// TODO Auto-generated method stub
		return prop.getProperty(JDBC_DATASOURCE_JNDI);
	}
	/**
	 * @return
	 */
	public static String getDBInContainer() {
		// TODO Auto-generated method stub
		return prop.getProperty(JDBC_DATASOURCE_INCONTAINER);
	}
	/**
	 * @return
	 */
	public static String getDBMaxActive() {
		// TODO Auto-generated method stub
		return prop.getProperty(JDBC_DATASOURCE_MAXACTIVE);
	}
	public static String getDBProperty(String dbKey) {
		return prop.getProperty(JDBC_DATASOURCE_PROPERTY+dbKey);
	}
}
