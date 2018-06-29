package com.lambo.demo;

import com.lambo.code.utils.MybatisGeneratorUtil;

/**
 * 代码生成类
 * Created by lambo on 2017/1/10.
 */
public class Generator {
    private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";//数据库驱动
    private static String JDBC_URL = "jdbc:mysql://10.10.10.136:3306/test";//数据连接
    private static String JDBC_USERNAME = "root";//数据库用户
    private static String JDBC_PASSWORD = "root";//数据库密码
    private static String DATABASE = "test";//数据库名称
    private static String TABLE = "rest_setting";//表名
    private static String LAST_INSERT_ID_TABLES = "rest_id";//主键
    private static Boolean IS_AUTO_INC = false;//是否自增

    private static String PACKAGE_NAME = "com.lambo.demo";//类的包路径

    /**
     * 自动代码生成
     * @param args
     */
    public static void main(String[] args) throws Exception {
        MybatisGeneratorUtil.generator(
                JDBC_DRIVER,
                JDBC_URL,
                JDBC_USERNAME,
                JDBC_PASSWORD,
                DATABASE,
                TABLE,
                LAST_INSERT_ID_TABLES,
                IS_AUTO_INC,
                PACKAGE_NAME
        );
    }
}