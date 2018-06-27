# 多应用场景下的认证客户端

----

本客户端实现了在多应用场景下的应用单点登录，基于shiro和redis实现了应用会话共享，保证了应用无状态


具体登录步骤描述如下：
1. 认证中心在登录成功往cookie里放入一个code，同时在reids里放入code与登录用户userId的对应
2. 认证客户端在响应请求时，先从本地缓存到读取session，读不到则到redis中去读
3. 如果redis里也读不到，则从cookie里取出code，然后到redis里根据code拿到userId
4. 如果能拿到userId，则基于此userId免密登录，创建会话，同时将会话信息写入redis
5. 如果拿不到userId，则提示会话失效，交由前端处理后续


具体注销步骤如下：
1. 从redis中删除认证中心的会话信息
2. 从redis中删除同一code相关联的客户端会话
3. 删除cookie中的code
注：只认证中心提供注销服务
