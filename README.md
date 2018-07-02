# lambo-egg
轻量级的javaweb后端开发框架

### 技术选型

技术 | 名称 | 官网
----|------|----
Spring Framework | 容器  | [http://projects.spring.io/spring-framework/](http://projects.spring.io/spring-framework/)
SpringMVC | MVC框架  | [http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#mvc](http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#mvc)
Apache Shiro | 安全框架  | [http://shiro.apache.org/](http://shiro.apache.org/)
MyBatis | ORM框架  | [http://www.mybatis.org/mybatis-3/zh/index.html](http://www.mybatis.org/mybatis-3/zh/index.html)
PageHelper | MyBatis物理分页插件  | [http://git.oschina.net/free/Mybatis_PageHelper](http://git.oschina.net/free/Mybatis_PageHelper)
Druid | 数据库连接池  | [https://github.com/alibaba/druid](https://github.com/alibaba/druid)
FluentValidator | 校验框架  | [https://github.com/neoremind/fluent-validator](https://github.com/neoremind/fluent-validator)
Ehcache | 进程内缓存框架  | [http://www.ehcache.org/](http://www.ehcache.org/)
Log4J | 日志组件  | [http://logging.apache.org/log4j/1.2/](http://logging.apache.org/log4j/1.2/)
Swagger2 | 接口测试框架  | [http://swagger.io/](http://swagger.io/)
Maven | 项目构建管理  | [http://maven.apache.org/](http://maven.apache.org/)

### 模块介绍

> [核心模块](lambo-plantform/lambo-common)

> [认证模块](lambo-plantform/lambo-auth)

> [代码生成模块](lambo-plantform/lambo-code)

> [缓存模块](lambo-plantform/lambo-cache)

> [MQ模块](lambo-plantform/lambo-mq)

> [对象存储模块](lambo-plantform/lambo-oss)

> [服务模块](lambo-plantform/lambo-rest)

> [RPC模块](lambo-plantform/lambo-rpc)

> [定时器模块](lambo-plantform/lambo-schedule)

> [用户权限组织模块](lambo-plantform/lambo-upms)

> [示例模块](lambo-plantform/lambo-demo)

### 开发指南
- 1、本机安装Jdk7+、Mysql并**启动相关服务**，使用默认配置默认端口即可
- 2、克隆源代码到本地并打开，**推荐使用IntelliJ IDEA**，本地编译并安装到本地maven仓库

### 编译流程
- 打开IDEA,import project选择lambo根目录下的pom.xml(注意一定要选择到pom.xml)
- 打开Maven-Project窗口(view-Tool Windows-Maven Projects),双击运行lambo-Lifecycle-install选项

### 启动
- 新建lambo数据库，导入project-datamodel文件夹下的lambo.sql
- 修改lambo-demo/lmabo-demo-sample模块的jdbc.properties数据库连接等配置信息，其中master.jdbc.password、slave.jdbc.password密码值使用了AES加密，请使用lambo-commmon项目下的com.lambo.common.util.codec.AESUtil工具类修改这些值
- 在Maven Project窗口里,打开lambo-demo下的Plugins,在jetty - jetty:run上右键debug lambo-demo
- 访问 [http://127.0.0.1:9630/demo-sample-server/swagger-ui.html]("后台Swagger接口文档地址")
- 在sso-controller中使用/sso/login登录系统(admin 123456),然后就可以测试各个接口了

### 文档

更多文档请查看[本项目Wiki](https://github.com/btmagm/lambo-egg-backend/wiki)
