package com.lambo.code;

import com.lambo.code.utils.MybatisGeneratorUtil;

/**
 * 代码生成类
 * Created by lambo on 2017/1/10.
 */
public class Generator {

	// 根据命名规范，只修改此常量值即可
	private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/test";
	private static String JDBC_USERNAME = "root";
	private static String JDBC_PASSWORD = "root";
	private static String DATABASE = "test";
	private static String TABLE = "test_user";
	private static String LAST_INSERT_ID_TABLES = "user_id";//主键
	private static Boolean IS_AUTO_INC = false;//是否自增

	private static String PACKAGE_NAME = "com.lambo.code";
	private static String MODULE = "LamboCode";

	/**
	 * 自动代码生成
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		MybatisGeneratorUtil.generator(JDBC_DRIVER, JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD, DATABASE, TABLE, LAST_INSERT_ID_TABLES,IS_AUTO_INC,PACKAGE_NAME, MODULE);
	}

}
