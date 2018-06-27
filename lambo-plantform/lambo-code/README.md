# 此模块为代码机，可以便捷生成单表增删改查代码

## 示例参考：

第一步： 依赖lambo-code-client组件,在组件的pom.xml文件添加如下配置：
```java
<dependency>
    <groupId>com.lambo</groupId>
    <artifactId>lambo-code-client</artifactId>
    <version>1.0.0</version>
    <type>jar</type>
</dependency>
```
第二步：在指定包的路径下，新建类Generator.java，如下：
```java
import com.lambo.cm.utils.MybatisGeneratorUtil;

/**
 * 代码生成类
 * Created by lambo on 2017/1/10.
 */
public class Generator {
    private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";//数据库驱动
    private static String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/test";//数据连接
    private static String JDBC_USERNAME = "root";//数据库用户
    private static String JDBC_PASSWORD = "root";//数据库密码
    private static String DATABASE = "test";//数据库名称
    private static String TABLE = "test_user";//表名
    private static String LAST_INSERT_ID_TABLES = "user_id";//主键
    private static Boolean IS_AUTO_INC = false;//是否自增
    
    private static String PACKAGE_NAME = "com.lambo.cm";//类的包路径
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
            MODULE
        );
    }
}
```

第三步：运行Generator.java，生成的vue文件需要自行拷贝到前台目录。运行日志参考如下：

```java
========== 开始生成generatorConfig.xml文件 ==========
2018-06-27 09:02:55,311 [main] INFO  [com.lambo.common.utils.other.JdbcUtil] - 数据库连接成功：jdbc:mysql://127.0.0.1:3306/test
========== 结束生成generatorConfig.xml文件 ==========
========== 开始运行MybatisGenerator ==========
========== 结束运行MybatisGenerator ==========
========== 开始生成Constant ==========
D:/workspace/lambo-egg-backend/lambo-plantform/lambo-code/src/main/java/com/lambo/cm/constant/LamboCmResult.java
D:/workspace/lambo-egg-backend/lambo-plantform/lambo-code/src/main/java/com/lambo/cm/constant/LamboCmResultConstant.java
========== 结束生成Constant ==========
========== 开始生成Service,Controller,Vue ==========
D:/workspace/lambo-egg-backend/lambo-plantform/lambo-code/src/main/java/com/lambo/cm/service/api/TestUserService.java
D:/workspace/lambo-egg-backend/lambo-plantform/lambo-code/src/main/java/com/lambo/cm/service/impl/TestUserServiceImpl.java
D:/workspace/lambo-egg-backend/lambo-plantform/lambo-code/src/main/java/com/lambo/cm/controller/TestUserController.java
D:/workspace/lambo-egg-backend/lambo-plantform/lambo-code/src/main/java/com/lambo/cm/vue/TestUserQuery.vue
D:/workspace/lambo-egg-backend/lambo-plantform/lambo-code/src/main/java/com/lambo/cm/vue/TestUserEdit.vue
拷贝vue到自己前台目录，配置vue的router,其中注意路由的name属性，参考如下：
import testUserQuery from '@/components/testUser/TestUserQuery';
import testUserEdit from '@/components/testUser/TestUserEdit';

{ 
	path: 'manage/testUser/query',
	meta:{
		title: 'testUserQuery',
	},
	name:'testUserQuery',
	component: testUserQuery
},
{
	path: 'manage/testUser/edit',
	meta:{
		title: 'testUserEdit',
	},
	name:'testUserEdit',
	component: testUserEdit
}
========== 结束生成Service,Controller,Vue ==========
```