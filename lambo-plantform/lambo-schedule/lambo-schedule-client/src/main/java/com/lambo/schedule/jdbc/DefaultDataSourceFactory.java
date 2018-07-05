package com.lambo.schedule.jdbc;

import javax.sql.DataSource;

import com.lambo.common.utils.codec.AESUtil;
import org.apache.commons.dbcp.BasicDataSource;
import com.lambo.schedule.PropertyJDBC;

public class DefaultDataSourceFactory extends DataSourceFactory{
	private JndiTemplate jndiTemplate = new JndiTemplate();

	@Override
	protected DataSource createDataSource() throws Exception {
		// TODO Auto-generated method stub
		// 使用jndi连接数据源。
//		if (PropertyMgr.getDBJndi() != null) {
//			String jndiName = PropertyMgr.getDBJndi();
//			String inContainer = PropertyMgr.getDBInContainer();
//			if ("true".equals(inContainer)
//					&& !jndiName.startsWith(CONTAINER_PREFIX)
//					&& jndiName.indexOf(':') == -1) {
//				jndiName = CONTAINER_PREFIX + jndiName;
//			}
//			DataSource ds = (DataSource) jndiTemplate.lookup(jndiName);
//			return ds;
//		}


		BasicDataSource bds = new BasicDataSource();
		String driverClassName=PropertyJDBC.getDBDriver();
		bds.setDriverClassName(driverClassName);
		bds.setUrl(PropertyJDBC.getDBUrl());
		bds.setUsername(PropertyJDBC.getDBUser());
		//bds.setPassword(PropertyJDBC.getDBPass());

		bds.setPassword(AESUtil.AESDecode(PropertyJDBC.getDBPass()));

		bds.setMaxActive(50);

		bds.setMaxIdle(10);

		bds.setMaxWait(-1);
		String sql =null;
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