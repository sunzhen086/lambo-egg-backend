# 安全模块

安全模块对输入的特殊字符进行了拦截处理，以防止xss、sql注入攻击等，特殊字符包括但不限于以下：
```javascript

html相关字符 < >  " ;  & \

sql相关字符  master truncate insert select delete update declare alter drop

```
### 配置文件

可以通过security-default-client.properties配置文件来排除一些URL,以满足需要用到这些字符的场景

```properties
excludeUrl=/aaa/bbb/ccc;/ooo/xxx/*;
```
排除所有URL可使用excludeUrl=ALL