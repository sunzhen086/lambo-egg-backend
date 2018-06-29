# V6集成客户端

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


相关接口

```java
package com.lambo.auth.client.controller;

import com.lambo.auth.client.service.api.AuthClientApiService;
import com.lambo.common.base.BaseController;
import com.lambo.common.base.BaseResult;
import com.lambo.common.base.BaseResultConstant;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName AuthClientApiController
 * @Descirption 会话相关服务
 * @author sunzhen
 * @Date 2018/6/20 8:23
 **/
@Controller
@RequestMapping("/auth")
public class AuthClientApiController extends BaseController {

    @Autowired
    AuthClientApiService authClientApiService;

    @ApiOperation(value = "获取当前登录用户ID" ,notes = "获取当前登录用户ID")
    @RequestMapping(value = "/user/getId", method = RequestMethod.GET)
    @ResponseBody
    public Object getUserId(){
        return new BaseResult(BaseResultConstant.SUCCESS,authClientApiService.getUserId());
    }

    @ApiOperation(value = "获取当前登录用户NAME" ,notes = "获取当前登录用户NAME")
    @RequestMapping(value = "/user/getName", method = RequestMethod.GET)
    @ResponseBody
    public Object getUserName(){
        return new BaseResult(BaseResultConstant.SUCCESS,authClientApiService.getUserName());
    }

    @ApiOperation(value = "获取当前登录用户所属公司" ,notes = "获取当前登录用户所属公司")
    @RequestMapping(value = "/user/getComId", method = RequestMethod.GET)
    @ResponseBody
    public Object getUserComId(){
        return new BaseResult(BaseResultConstant.SUCCESS,authClientApiService.getComId());
    }

    @ApiOperation(value = "获取当前登录用户数据权限" ,notes = "获取当前登录用户数据权限")
    @RequestMapping(value = "/user/getDataPermissions", method = RequestMethod.GET)
    @ResponseBody
    public Object getUserDataPermissions(){
        return new BaseResult(BaseResultConstant.SUCCESS,authClientApiService.getDataPermissions());
    }

}

```