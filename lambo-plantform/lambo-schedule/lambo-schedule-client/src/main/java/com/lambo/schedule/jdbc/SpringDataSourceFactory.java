package com.lambo.schedule.jdbc;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.springframework.web.context.WebApplicationContext;

public class SpringDataSourceFactory extends DataSourceFactory{
	private String beanId;
	public SpringDataSourceFactory(String beanId){
		this.beanId=beanId;
	}

	@Override
	protected DataSource createDataSource() throws Exception {
		
		// TODO Auto-generated method stub
		ServletContext servletContext = this.getServletContext();
		if(servletContext==null){
			throw new RuntimeException(SpringDataSourceFactory.class.getName()+": servletContext is null!");
		}
		WebApplicationContext context = (WebApplicationContext)servletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);

		return (DataSource) context.getBean(beanId);
	}

}
