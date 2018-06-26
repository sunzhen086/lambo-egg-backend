package com.lambo.demo;


import com.lambo.common.utils.other.MybatisGeneratorUtil;

/**
 * 代码生成类
 * Created by lambo on 2018/3/26.
 */
public class Generator {

	/**
	 * 根据命名规范，只修改此常量值即可
	 */
	private static String MODULE = "sample";
	private static String TABLE = "upms_log";
	private static String PACKAGE_NAME = "com.lambo.demo";
	private static String DOMAIN_OBJECT_NAME = "LogDemo";

	private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static String JDBC_URL = "jdbc:mysql://10.10.10.136:3306/lambo";
	private static String JDBC_USERNAME = "root";
	private static String JDBC_PASSWORD = "root";
	private static String DATABASE = "lambo";
	private static String LAST_INSERT_ID_TABLES = "log_id";

	/**
	 * 自动代码生成
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		MybatisGeneratorUtil.generator(JDBC_DRIVER, JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD, DATABASE, TABLE, LAST_INSERT_ID_TABLES,true,PACKAGE_NAME, MODULE);
	}

}
