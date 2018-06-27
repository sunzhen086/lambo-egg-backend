package com.lambo.schedule.jdbc;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import com.lambo.schedule.PropertyMgr;

public class DefaultDataSourceFactory extends DataSourceFactory{
	private JndiTemplate jndiTemplate = new JndiTemplate();

	@Override
	protected DataSource createDataSource() throws Exception {
		// TODO Auto-generated method stub
		// 使用jndi连接数据源。
		if (PropertyMgr.getDBJndi() != null) {
			String jndiName = PropertyMgr.getDBJndi();
			String inContainer = PropertyMgr.getDBInContainer();
			if ("true".equals(inContainer)
					&& !jndiName.startsWith(CONTAINER_PREFIX)
					&& jndiName.indexOf(':') == -1) {
				jndiName = CONTAINER_PREFIX + jndiName;
			}
			DataSource ds = (DataSource) jndiTemplate.lookup(jndiName);
			return ds;
		} 
		
		// 使用commons-dbcp进行简单的连接池数据源。
		BasicDataSource bds = new BasicDataSource();
		String driverClassName=PropertyMgr.getDBDriver();
		bds.setDriverClassName(driverClassName);
		bds.setUrl(PropertyMgr.getDBUrl());
		bds.setUsername(PropertyMgr.getDBUser());
		bds.setPassword(PropertyMgr.getDBPass());
		String maxActive = PropertyMgr.getDBMaxActive();
		int max = 0;
		if (maxActive != null && !maxActive.trim().equals("")) {
			max = Integer.parseInt(maxActive.trim());
			bds.setMaxActive(max);
		} else {
			max = 5;
			bds.setMaxActive(5);
		}
		String maxIdle = PropertyMgr.getDBProperty("maxIdle");
		if (maxIdle != null && !maxIdle.trim().equals("")) {
			int min = Integer.parseInt(maxIdle.trim());
			bds.setMaxIdle(min);
		} else {
			bds.setMaxIdle(max / 2);
		}
		bds.setMaxWait(-1);
		String sql = PropertyMgr.getDBProperty("validateSql");
		if (sql == null) {
			if (driverClassName.indexOf("db2") >= 0) {
				sql = " SELECT COUNT(*) FROM SYSIBM.SYSTABLES";
			} else if (driverClassName.indexOf("oracle") >= 0) {
				sql = "select 1 from dual";
			} else if (driverClassName.indexOf("sqlserver") >= 0
					|| driverClassName.indexOf("sybase") >= 0) {
				sql = "SELECT 1";
			}
		}
		if (sql != null)
			bds.setValidationQuery(sql);

		return bds;
	}
	
}