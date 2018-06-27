# 此模块为代码机，可以便捷生成单表增删改查代码

## 示例参考：

第一步： 依赖lambo-code-client组件
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
"D:\Program Files\Java\jdk1.7.0_51\bin\java" "-javaagent:D:\Program Files\JetBrains\IntelliJ IDEA 2017.2.5\lib\idea_rt.jar=49198:D:\Program Files\JetBrains\IntelliJ IDEA 2017.2.5\bin" -Dfile.encoding=UTF-8 -classpath "D:\Program Files\Java\jdk1.7.0_51\jre\lib\charsets.jar;D:\Program Files\Java\jdk1.7.0_51\jre\lib\deploy.jar;D:\Program Files\Java\jdk1.7.0_51\jre\lib\ext\access-bridge-64.jar;D:\Program Files\Java\jdk1.7.0_51\jre\lib\ext\dnsns.jar;D:\Program Files\Java\jdk1.7.0_51\jre\lib\ext\jaccess.jar;D:\Program Files\Java\jdk1.7.0_51\jre\lib\ext\localedata.jar;D:\Program Files\Java\jdk1.7.0_51\jre\lib\ext\sunec.jar;D:\Program Files\Java\jdk1.7.0_51\jre\lib\ext\sunjce_provider.jar;D:\Program Files\Java\jdk1.7.0_51\jre\lib\ext\sunmscapi.jar;D:\Program Files\Java\jdk1.7.0_51\jre\lib\ext\zipfs.jar;D:\Program Files\Java\jdk1.7.0_51\jre\lib\javaws.jar;D:\Program Files\Java\jdk1.7.0_51\jre\lib\jce.jar;D:\Program Files\Java\jdk1.7.0_51\jre\lib\jfr.jar;D:\Program Files\Java\jdk1.7.0_51\jre\lib\jfxrt.jar;D:\Program Files\Java\jdk1.7.0_51\jre\lib\jsse.jar;D:\Program Files\Java\jdk1.7.0_51\jre\lib\management-agent.jar;D:\Program Files\Java\jdk1.7.0_51\jre\lib\plugin.jar;D:\Program Files\Java\jdk1.7.0_51\jre\lib\resources.jar;D:\Program Files\Java\jdk1.7.0_51\jre\lib\rt.jar;D:\workspace\lambo-egg-backend\lambo-plantform\lambo-common\target\classes;C:\Users\wangjierj\.m2\repository\org\springframework\spring-context-support\4.3.7.RELEASE\spring-context-support-4.3.7.RELEASE.jar;C:\Users\wangjierj\.m2\repository\org\springframework\spring-beans\4.3.7.RELEASE\spring-beans-4.3.7.RELEASE.jar;C:\Users\wangjierj\.m2\repository\org\springframework\spring-context\4.3.7.RELEASE\spring-context-4.3.7.RELEASE.jar;C:\Users\wangjierj\.m2\repository\org\springframework\spring-webmvc\4.3.7.RELEASE\spring-webmvc-4.3.7.RELEASE.jar;C:\Users\wangjierj\.m2\repository\org\springframework\spring-aop\4.3.7.RELEASE\spring-aop-4.3.7.RELEASE.jar;C:\Users\wangjierj\.m2\repository\org\springframework\spring-expression\4.3.7.RELEASE\spring-expression-4.3.7.RELEASE.jar;C:\Users\wangjierj\.m2\repository\org\springframework\spring-web\4.3.7.RELEASE\spring-web-4.3.7.RELEASE.jar;C:\Users\wangjierj\.m2\repository\org\springframework\spring-jdbc\4.3.7.RELEASE\spring-jdbc-4.3.7.RELEASE.jar;C:\Users\wangjierj\.m2\repository\org\springframework\spring-tx\4.3.7.RELEASE\spring-tx-4.3.7.RELEASE.jar;C:\Users\wangjierj\.m2\repository\org\springframework\spring-jms\4.3.7.RELEASE\spring-jms-4.3.7.RELEASE.jar;C:\Users\wangjierj\.m2\repository\org\springframework\spring-messaging\4.3.7.RELEASE\spring-messaging-4.3.7.RELEASE.jar;C:\Users\wangjierj\.m2\repository\org\springframework\spring-core\4.3.7.RELEASE\spring-core-4.3.7.RELEASE.jar;C:\Users\wangjierj\.m2\repository\commons-logging\commons-logging\1.2\commons-logging-1.2.jar;C:\Users\wangjierj\.m2\repository\org\springframework\security\spring-security-web\4.1.3.RELEASE\spring-security-web-4.1.3.RELEASE.jar;C:\Users\wangjierj\.m2\repository\aopalliance\aopalliance\1.0\aopalliance-1.0.jar;C:\Users\wangjierj\.m2\repository\org\springframework\security\spring-security-core\4.1.3.RELEASE\spring-security-core-4.1.3.RELEASE.jar;C:\Users\wangjierj\.m2\repository\org\springframework\security\spring-security-config\4.1.3.RELEASE\spring-security-config-4.1.3.RELEASE.jar;C:\Users\wangjierj\.m2\repository\org\springframework\session\spring-session-data-redis\1.3.0.RELEASE\spring-session-data-redis-1.3.0.RELEASE.jar;C:\Users\wangjierj\.m2\repository\org\apache\commons\commons-pool2\2.4.2\commons-pool2-2.4.2.jar;C:\Users\wangjierj\.m2\repository\org\springframework\session\spring-session\1.3.0.RELEASE\spring-session-1.3.0.RELEASE.jar;C:\Users\wangjierj\.m2\repository\org\aspectj\aspectjrt\1.8.8\aspectjrt-1.8.8.jar;C:\Users\wangjierj\.m2\repository\cglib\cglib\3.1\cglib-3.1.jar;C:\Users\wangjierj\.m2\repository\org\ow2\asm\asm\4.2\asm-4.2.jar;C:\Users\wangjierj\.m2\repository\org\apache\shiro\shiro-core\1.3.2\shiro-core-1.3.2.jar;C:\Users\wangjierj\.m2\repository\org\apache\shiro\shiro-web\1.3.2\shiro-web-1.3.2.jar;C:\Users\wangjierj\.m2\repository\org\apache\shiro\shiro-quartz\1.3.2\shiro-quartz-1.3.2.jar;C:\Users\wangjierj\.m2\repository\org\opensymphony\quartz\quartz\1.6.1\quartz-1.6.1.jar;C:\Users\wangjierj\.m2\repository\org\apache\shiro\shiro-spring\1.3.2\shiro-spring-1.3.2.jar;C:\Users\wangjierj\.m2\repository\org\apache\shiro\shiro-ehcache\1.3.2\shiro-ehcache-1.3.2.jar;C:\Users\wangjierj\.m2\repository\org\mybatis\mybatis\3.4.2\mybatis-3.4.2.jar;C:\Users\wangjierj\.m2\repository\org\mybatis\mybatis-spring\1.3.1\mybatis-spring-1.3.1.jar;C:\Users\wangjierj\.m2\repository\com\github\pagehelper\pagehelper\5.0.1\pagehelper-5.0.1.jar;C:\Users\wangjierj\.m2\repository\com\github\jsqlparser\jsqlparser\0.9.5\jsqlparser-0.9.5.jar;C:\Users\wangjierj\.m2\repository\org\mybatis\generator\mybatis-generator-core\1.3.5\mybatis-generator-core-1.3.5.jar;C:\Users\wangjierj\.m2\repository\mysql\mysql-connector-java\5.1.34\mysql-connector-java-5.1.34.jar;C:\Users\wangjierj\.m2\repository\com\alibaba\druid\1.1.9\druid-1.1.9.jar;C:\Users\wangjierj\.m2\repository\redis\clients\jedis\2.9.0\jedis-2.9.0.jar;C:\Users\wangjierj\.m2\repository\org\springframework\data\spring-data-redis\1.7.5.RELEASE\spring-data-redis-1.7.5.RELEASE.jar;C:\Users\wangjierj\.m2\repository\org\springframework\data\spring-data-keyvalue\1.1.5.RELEASE\spring-data-keyvalue-1.1.5.RELEASE.jar;C:\Users\wangjierj\.m2\repository\org\springframework\data\spring-data-commons\1.12.5.RELEASE\spring-data-commons-1.12.5.RELEASE.jar;C:\Users\wangjierj\.m2\repository\org\springframework\spring-oxm\4.2.8.RELEASE\spring-oxm-4.2.8.RELEASE.jar;C:\Users\wangjierj\.m2\repository\org\slf4j\jcl-over-slf4j\1.7.21\jcl-over-slf4j-1.7.21.jar;C:\Users\wangjierj\.m2\repository\org\slf4j\slf4j-api\1.7.12\slf4j-api-1.7.12.jar;C:\Users\wangjierj\.m2\repository\org\slf4j\slf4j-log4j12\1.7.12\slf4j-log4j12-1.7.12.jar;C:\Users\wangjierj\.m2\repository\log4j\log4j\1.2.17\log4j-1.2.17.jar;C:\Users\wangjierj\.m2\repository\commons-fileupload\commons-fileupload\1.3.1\commons-fileupload-1.3.1.jar;C:\Users\wangjierj\.m2\repository\commons-io\commons-io\2.2\commons-io-2.2.jar;C:\Users\wangjierj\.m2\repository\io\springfox\springfox-swagger2\2.4.0\springfox-swagger2-2.4.0.jar;C:\Users\wangjierj\.m2\repository\io\swagger\swagger-annotations\1.5.6\swagger-annotations-1.5.6.jar;C:\Users\wangjierj\.m2\repository\io\swagger\swagger-models\1.5.6\swagger-models-1.5.6.jar;C:\Users\wangjierj\.m2\repository\io\springfox\springfox-spi\2.4.0\springfox-spi-2.4.0.jar;C:\Users\wangjierj\.m2\repository\io\springfox\springfox-core\2.4.0\springfox-core-2.4.0.jar;C:\Users\wangjierj\.m2\repository\io\springfox\springfox-schema\2.4.0\springfox-schema-2.4.0.jar;C:\Users\wangjierj\.m2\repository\io\springfox\springfox-swagger-common\2.4.0\springfox-swagger-common-2.4.0.jar;C:\Users\wangjierj\.m2\repository\io\springfox\springfox-spring-web\2.4.0\springfox-spring-web-2.4.0.jar;C:\Users\wangjierj\.m2\repository\com\google\guava\guava\18.0\guava-18.0.jar;C:\Users\wangjierj\.m2\repository\com\fasterxml\classmate\1.3.1\classmate-1.3.1.jar;C:\Users\wangjierj\.m2\repository\org\springframework\plugin\spring-plugin-core\1.2.0.RELEASE\spring-plugin-core-1.2.0.RELEASE.jar;C:\Users\wangjierj\.m2\repository\org\springframework\plugin\spring-plugin-metadata\1.2.0.RELEASE\spring-plugin-metadata-1.2.0.RELEASE.jar;C:\Users\wangjierj\.m2\repository\io\springfox\springfox-swagger-ui\2.4.0\springfox-swagger-ui-2.4.0.jar;C:\Users\wangjierj\.m2\repository\commons-beanutils\commons-beanutils\1.9.3\commons-beanutils-1.9.3.jar;C:\Users\wangjierj\.m2\repository\commons-collections\commons-collections\3.2.2\commons-collections-3.2.2.jar;C:\Users\wangjierj\.m2\repository\commons-lang\commons-lang\2.6\commons-lang-2.6.jar;C:\Users\wangjierj\.m2\repository\org\apache\commons\commons-lang3\3.4\commons-lang3-3.4.jar;C:\Users\wangjierj\.m2\repository\commons-codec\commons-codec\1.10\commons-codec-1.10.jar;C:\Users\wangjierj\.m2\repository\org\apache\commons\commons-compress\1.12\commons-compress-1.12.jar;C:\Users\wangjierj\.m2\repository\javax\persistence\persistence-api\1.0.2\persistence-api-1.0.2.jar;C:\Users\wangjierj\.m2\repository\javax\validation\validation-api\1.1.0.Final\validation-api-1.1.0.Final.jar;C:\Users\wangjierj\.m2\repository\org\hibernate\hibernate-validator\5.2.2.Final\hibernate-validator-5.2.2.Final.jar;C:\Users\wangjierj\.m2\repository\org\jboss\logging\jboss-logging\3.2.1.Final\jboss-logging-3.2.1.Final.jar;C:\Users\wangjierj\.m2\repository\com\baidu\unbiz\fluent-validator\1.0.6\fluent-validator-1.0.6.jar;C:\Users\wangjierj\.m2\repository\net\sf\json-lib\json-lib\2.4\json-lib-2.4-jdk15.jar;C:\Users\wangjierj\.m2\repository\net\sf\ezmorph\ezmorph\1.0.6\ezmorph-1.0.6.jar;C:\Users\wangjierj\.m2\repository\com\alibaba\fastjson\1.2.28\fastjson-1.2.28.jar;C:\Users\wangjierj\.m2\repository\com\fasterxml\jackson\core\jackson-core\2.9.0\jackson-core-2.9.0.jar;C:\Users\wangjierj\.m2\repository\com\fasterxml\jackson\core\jackson-databind\2.9.0\jackson-databind-2.9.0.jar;C:\Users\wangjierj\.m2\repository\com\fasterxml\jackson\core\jackson-annotations\2.9.0\jackson-annotations-2.9.0.jar;C:\Users\wangjierj\.m2\repository\org\apache\commons\commons-email\1.4\commons-email-1.4.jar;C:\Users\wangjierj\.m2\repository\com\sun\mail\javax.mail\1.5.2\javax.mail-1.5.2.jar;C:\Users\wangjierj\.m2\repository\javax\activation\activation\1.1.1\activation-1.1.1.jar;C:\Users\wangjierj\.m2\repository\org\apache\httpcomponents\httpclient\4.5.2\httpclient-4.5.2.jar;C:\Users\wangjierj\.m2\repository\org\apache\httpcomponents\httpcore\4.4.4\httpcore-4.4.4.jar;C:\Users\wangjierj\.m2\repository\org\aspectj\aspectjweaver\1.8.8\aspectjweaver-1.8.8.jar;C:\Users\wangjierj\.m2\repository\javax\servlet\servlet-api\2.5\servlet-api-2.5.jar;C:\Users\wangjierj\.m2\repository\javax\servlet\jstl\1.2\jstl-1.2.jar;C:\Users\wangjierj\.m2\repository\org\apache\poi\poi-ooxml\3.17\poi-ooxml-3.17.jar;C:\Users\wangjierj\.m2\repository\org\apache\poi\poi\3.17\poi-3.17.jar;C:\Users\wangjierj\.m2\repository\org\apache\commons\commons-collections4\4.1\commons-collections4-4.1.jar;C:\Users\wangjierj\.m2\repository\org\apache\poi\poi-ooxml-schemas\3.17\poi-ooxml-schemas-3.17.jar;C:\Users\wangjierj\.m2\repository\org\apache\xmlbeans\xmlbeans\2.6.0\xmlbeans-2.6.0.jar;C:\Users\wangjierj\.m2\repository\stax\stax-api\1.0.1\stax-api-1.0.1.jar;C:\Users\wangjierj\.m2\repository\com\github\virtuald\curvesapi\1.04\curvesapi-1.04.jar;C:\Users\wangjierj\.m2\repository\junit\junit\4.12\junit-4.12.jar;C:\Users\wangjierj\.m2\repository\org\hamcrest\hamcrest-core\1.3\hamcrest-core-1.3.jar;C:\Users\wangjierj\.m2\repository\com\h2database\h2\1.4.196\h2-1.4.196.jar;C:\Users\wangjierj\.m2\repository\org\springframework\spring-test\4.3.7.RELEASE\spring-test-4.3.7.RELEASE.jar;C:\Users\wangjierj\.m2\repository\com\bladejava\blade-patchca\1.0.5\blade-patchca-1.0.5.jar;C:\Users\wangjierj\.m2\repository\com\google\zxing\core\3.3.0\core-3.3.0.jar;C:\Users\wangjierj\.m2\repository\com\google\zxing\javase\3.3.0\javase-3.3.0.jar;C:\Users\wangjierj\.m2\repository\com\beust\jcommander\1.48\jcommander-1.48.jar;C:\Users\wangjierj\.m2\repository\com\github\jai-imageio\jai-imageio-core\1.3.1\jai-imageio-core-1.3.1.jar;C:\Users\wangjierj\.m2\repository\eu\bitwalker\UserAgentUtils\1.20\UserAgentUtils-1.20.jar;C:\Users\wangjierj\.m2\repository\dom4j\dom4j\1.6.1\dom4j-1.6.1.jar;C:\Users\wangjierj\.m2\repository\xml-apis\xml-apis\1.0.b2\xml-apis-1.0.b2.jar;C:\Users\wangjierj\.m2\repository\org\apache\ant\ant\1.9.7\ant-1.9.7.jar;C:\Users\wangjierj\.m2\repository\org\apache\ant\ant-launcher\1.9.7\ant-launcher-1.9.7.jar;D:\workspace\lambo-egg-backend\lambo-plantform\lambo-upms\lambo-upms-common\target\classes;D:\workspace\lambo-egg-backend\lambo-plantform\lambo-auth\lambo-sso-v6-client\target\classes;C:\Users\wangjierj\.m2\repository\commons-httpclient\commons-httpclient\3.1\commons-httpclient-3.1.jar;C:\Users\wangjierj\.m2\repository\commons-logging\commons-logging\1.0.4\commons-logging-1.0.4.jar;C:\Users\wangjierj\.m2\repository\javax\servlet\javax.servlet-api\3.0.1\javax.servlet-api-3.0.1.jar;D:\workspace\lambo-egg-backend\lambo-plantform\lambo-oss\lambo-oss-client\target\test-classes;D:\workspace\lambo-egg-backend\lambo-plantform\lambo-oss\lambo-oss-client\target\classes;D:\workspace\lambo-egg-backend\lambo-plantform\lambo-oss\lambo-oss-server\target\classes;D:\workspace\lambo-egg-backend\lambo-plantform\lambo-mq\lambo-mq-client\target\classes;C:\Users\wangjierj\.m2\repository\org\apache\rocketmq\rocketmq-client\4.2.0\rocketmq-client-4.2.0.jar;C:\Users\wangjierj\.m2\repository\org\apache\rocketmq\rocketmq-common\4.2.0\rocketmq-common-4.2.0.jar;C:\Users\wangjierj\.m2\repository\org\apache\rocketmq\rocketmq-remoting\4.2.0\rocketmq-remoting-4.2.0.jar;C:\Users\wangjierj\.m2\repository\io\netty\netty-all\4.0.42.Final\netty-all-4.0.42.Final.jar;D:\workspace\lambo-egg-backend\lambo-plantform\lambo-upms\lambo-upms-client\target\classes;D:\workspace\lambo-egg-backend\lambo-plantform\lambo-cache\lambo-cache-redis\target\classes;C:\Users\wangjierj\.m2\repository\net\sf\ehcache\ehcache\2.10.0\ehcache-2.10.0.jar;D:\workspace\lambo-egg-backend\lambo-plantform\lambo-code\lambo-code-client\target\classes;C:\Users\wangjierj\.m2\repository\org\apache\velocity\velocity\1.7\velocity-1.7.jar;C:\Users\wangjierj\.m2\repository\commons-collections\commons-collections\3.2.1\commons-collections-3.2.1.jar;D:\workspace\lambo-egg-backend\lambo-plantform\lambo-cache\lambo-cache-sample\target\classes;D:\workspace\lambo-egg-backend\lambo-plantform\lambo-auth\lambo-auth-redis-client\target\classes;D:\workspace\lambo-egg-backend\lambo-plantform\lambo-auth\lambo-auth-redis-server\target\classes;D:\workspace\lambo-egg-backend\lambo-plantform\lambo-auth\lambo-auth-sample-client\target\classes;D:\workspace\lambo-egg-backend\lambo-plantform\lambo-upms\lambo-upms-server\target\test-classes;D:\workspace\lambo-egg-backend\lambo-plantform\lambo-upms\lambo-upms-server\target\classes;D:\workspace\lambo-egg-backend\lambo-plantform\lambo-rest\lambo-rest-client\target\test-classes;D:\workspace\lambo-egg-backend\lambo-plantform\lambo-rest\lambo-rest-client\target\classes;C:\Users\wangjierj\.m2\repository\ognl\ognl\3.0.8\ognl-3.0.8.jar;C:\Users\wangjierj\.m2\repository\javassist\javassist\3.11.0.GA\javassist-3.11.0.GA.jar;D:\workspace\lambo-egg-backend\lambo-plantform\lambo-rest\lambo-rest-manage\target\test-classes;D:\workspace\lambo-egg-backend\lambo-plantform\lambo-rest\lambo-rest-manage\target\classes;C:\Users\wangjierj\.m2\repository\org\postgresql\postgresql\9.4.1208\postgresql-9.4.1208.jar;D:\workspace\lambo-egg-backend\lambo-plantform\lambo-rest\lambo-rest-manage\lib\db2jcc4.jar;D:\workspace\lambo-egg-backend\lambo-plantform\lambo-rest\lambo-rest-manage\lib\db2jcc_license_cu.jar;D:\workspace\lambo-egg-backend\lambo-plantform\lambo-rest\lambo-rest-manage\lib\ojdbc6.jar;D:\workspace\lambo-egg-backend\lambo-test\target\classes;D:\workspace\lambo-egg-backend\lambo-plantform\lambo-mq\lambo-mq-server\target\classes;D:\workspace\lambo-egg-backend\lambo-plantform\lambo-demo\target\classes;D:\workspace\lambo-egg-backend\bss\target\classes" Generator
========== 开始生成generatorConfig.xml文件 ==========
2018-06-27 09:02:55,311 [main] INFO  [com.lambo.common.utils.other.JdbcUtil] - 数据库连接成功：jdbc:mysql://127.0.0.1:3306/test
========== 结束生成generatorConfig.xml文件 ==========
========== 开始运行MybatisGenerator ==========
========== 结束运行MybatisGenerator ==========
========== 开始生成Constant ==========
D:/workspace/lambo-egg-backend/lambo-plantform/lambo-common/src/main/java/com/lambo/cm/constant/LamboCmResult.java
D:/workspace/lambo-egg-backend/lambo-plantform/lambo-common/src/main/java/com/lambo/cm/constant/LamboCmResultConstant.java
========== 结束生成Constant ==========
========== 开始生成Service,Controller,Vue ==========
D:/workspace/lambo-egg-backend/lambo-plantform/lambo-common/src/main/java/com/lambo/cm/service/api/TestUserService.java
D:/workspace/lambo-egg-backend/lambo-plantform/lambo-common/src/main/java/com/lambo/cm/service/impl/TestUserServiceImpl.java
D:/workspace/lambo-egg-backend/lambo-plantform/lambo-common/src/main/java/com/lambo/cm/controller/TestUserController.java
D:/workspace/lambo-egg-backend/lambo-plantform/lambo-common/src/main/java/com/lambo/cm/vue/TestUserQuery.vue
D:/workspace/lambo-egg-backend/lambo-plantform/lambo-common/src/main/java/com/lambo/cm/vue/TestUserEdit.vue
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