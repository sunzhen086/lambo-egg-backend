package com.lambo.schedule.jdbc;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public abstract class DataSourceFactory {

	public static final String CONTAINER_PREFIX = "java:comp/env/";
	
	private static Logger logger = Logger.getLogger(DataSourceFactory.class.getName());
	
	private ServletContext servletContext ;

	protected DataSource dataSource = null;
	

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private static DataSourceFactory instance;


	public static DataSourceFactory getInstance(){
		if(instance==null){
				instance=newInstance();
		}
		return instance;
	}
	
	public static DataSourceFactory newInstance(){
		if(instance==null){
			instance=new DefaultDataSourceFactory();
			logger.debug("Use DefaultDataSource ...");
		}
		return instance;
	}
	
	public static DataSourceFactory newInstance(String beanId){
		if(instance==null){
			if(beanId!=null&&!"".equals(beanId)){
				logger.debug("Use SpringDataSource ...");
				instance=new SpringDataSourceFactory(beanId);
				
			}else{
				logger.debug("Use DefaultDataSource ...");
				instance=new DefaultDataSourceFactory();
				
			}
		}
		return instance;
	}

	public DataSource getDataSource() {
		if (dataSource == null)
			try {
				logger.debug("createDataSource----begin");
				dataSource=this.createDataSource();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("Get Datasource Error:",e);
				throw new RuntimeException("Get Datasource Error:"+e);
			}
		
		return dataSource;
	}

	protected abstract DataSource createDataSource() throws Exception ;

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}



}
