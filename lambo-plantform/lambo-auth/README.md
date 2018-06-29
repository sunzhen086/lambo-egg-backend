# 认证模块

----

认证模块负责登录和系统集成相关功能，有如下几个子项目，他们之间互斥：

* [lambo-auth-sample-client](/lambo-auth-sample-client/README.md) 单应用场景下使用用户名和密码认证
* [lambo-auth-redis-server](/lambo-auth-redis-server/README.md) 多应用场景下的认证中心，使用redis存储会话
* [lambo-auth-redis-client](/lambo-auth-redis-client/README.md) 多应用场景下的认证客户端，跟lambo-auth-redis-server认证中心搭配使用
* [lambo-sso-v6-client](/lambo-sso-v6-client/README.md) 与V6集成的客户端项目

