# V6集成客户端

------------

本客户端实现了与V6的集成，使用方式如下：
* 在应用resources目录下新建lambo-sso-v6-client.properties配置文件
```properties
v6.apache.ip=127.0.0.1
v6.userinfo.url=/v6/restful/GetUserInfo/getUserInfoJsonExt
v6.loginpage.url=/v6

```
* v6.apache.ip 配置v6的ip地址
* v6.userinfo.url 配置v6的获取用户信息url，一般不需要改
* v6.loginpage.url 配置v6的登录页
* 应用要与v6同域部署