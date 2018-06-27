# 定时器说明使用文档

----
##第一步：部署和配置

   ###(1)定时器的使用必须依赖lambo-schedule-client
   ###(2)配置web.xml
  在web.xml文件中添加：
  ```java
  	<servlet-name>LoadOnStartupServlet</servlet-name>
  		<servlet-class>org.loushang.commons.schedule.web.LoadCrontabServlet</servlet-class>
  		<init-param>
  			<param-name>PROPERTIES_FILE</param-name>
  			<param-value>schedule.properties</param-value>
  		</init-param>
  		<load-on-startup>1</load-on-startup>
  	</servlet>
  ```
 ###(2)配置数据源
数据库的数据源支持两种配置方式：

  ```java
    #直接方式
    #lambo.schedule.jdbc.datasource.driverClassName=com.mysql.jdbc.Driver
    #lambo.schedule.jdbc.datasource.url=jdbc:mysql://127.0.0.1:3306/test
    #lambo.schedule.jdbc.datasource.username=root
    #lambo.schedule.jdbc.datasource.password=root
    #lambo.schedule.jdbc.datasource.maxActive=20
    
    #spring datasource方式（推荐使用）
    lambo.schedule.jdbc.springDatasource.beanId=dataSource
  ```
 ##第二步：意事项
 ###1、	集群环境下只能启动一个定时器，lambo-egg定时器默认是启动，不启动定时器方法：在jvm中设置：-Dschedule.start=false
 ###2、任务名不可重复
 ###3、小时的范围为0-23，周的范围为0-6,分钟的范围0-59
 ###4、定时任务的方法参数必须为String[ ]
 ##第三部 定时任务设置示例
 #### 日程安排中的五个域分别代表了分、时、日、月、周，这五个域的内容可以是以下值：
 #### *      ： 代表所有月、日、时、分
 #### */n    ： 代表每n月、日、时、分
 #### n1-n2  ： 代表n1(月...)至n2(月...)
 #### n1,n2  ： 代表n1、n2(月...)
 ####  周     ： 是周一、二、三...，而不是第一、二、三...周 ，并且0代表周日
 ###请看下面例子：
 表示方式 | 意义
 ----|------|
"0 12 * * *" | 	每天的12点(中午)执行一次任务
"15 10 * * *" |	每天的10:15am 
"* 14 * * *"  |	每天的14:00开始到14:59期间，每分种执行一次任务
"*/5 14 * * *" |	每天的14:00开始到14:59期间，每5分种执行一次任务
"*/5 14,18 * * *" | 	每天的14:00到14:59期间和18:00到18:59期间，每5分种执行一次任务 
"0-5 14 * * *" 	| 每天的14:00开始到14:05期间，每分种执行一次任务 
"10,44 14 * 3 4" |	在3月份的每个星期四的14:10和14:44执行一次任务
"15 10 * * 1-5" |	在每个星期一到星期五的10:15执行一次任务
"15 10 15 * *" |	在每个月15日的10:15执行一次任务
"0 20 * * 6"  |	在每周六的20:00执行一次任务
"30 21 */2 * *" |	每个月每两天的21:30执行一次任务
"15 10 13 * 5" |	在每个月的13日并且是周五的那一天的10:15执行任务