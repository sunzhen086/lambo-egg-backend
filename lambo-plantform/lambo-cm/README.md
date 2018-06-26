# 此模块为代码机，可以便捷生成单表增删改查代码

## 示例参考：

第一步： 依赖lambo-cm-client组件
```java
<dependency>
    <groupId>com.lambo</groupId>
    <artifactId>lambo-cm-client</artifactId>
    <version>1.0.0</version>
    <type>jar</type>
</dependency>
```
第二步：在指定包的路径下，新建类Generator.java，如下：
```java
private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";//数据库驱动
private static String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/test";//数据连接
private static String JDBC_USERNAME = "root";//数据库用户
private static String JDBC_PASSWORD = "root";//数据库密码
private static String DATABASE = "test";//数据库名称
private static String TABLE = "test_user";//表名
private static String LAST_INSERT_ID_TABLES = "user_id";//主键
private static Boolean IS_AUTO_INC = false;//是否自增

private static String PACKAGE_NAME = "com.lambo.cm";//类生成包路径
private static String MODULE = "LamboCm";//组件名称

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
                PACKAGE_NAME, 
                MODULE);
}
```

第三步：运行Generator.java，其中vue文件需要自行拷贝到前台目录。