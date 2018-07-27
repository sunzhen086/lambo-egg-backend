package com.lambo.auth.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.lambo.auth.dao.model.UpmsStUser;
import com.lambo.auth.dao.model.UpmsStUserExample;
import com.lambo.auth.shiro.session.AuthClientSession;
import com.lambo.auth.shiro.session.AuthClientSessionDao;
import com.lambo.auth.service.api.UpmsStUserService;
import com.lambo.auth.util.XsmLoginUtil;
import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseController;
import com.lambo.common.base.BaseResult;
import com.lambo.common.base.BaseResultConstant;
import com.lambo.common.utils.idgen.IdGenerate;
import com.lambo.common.utils.io.RedisUtil;
import com.lambo.common.utils.web.CookieUtils;
import com.lambo.common.utils.web.http.ServletUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.loushang.bsp.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 单点登录管理
 * Created by lambo on 2016/12/10.
 */
@Controller
@RequestMapping("/sso")
@Api(value = "单点登录管理", description = "单点登录管理")
public class AuthCenterController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalAuthController.class);
    /**
     * 会话code
     */
    private final static String LAMBO_SSO_CODE = "lambo-sso-code";
    /**
     * 全局会话key列表
     */
    private final static String LAMBO_SSO_SESSION_IDS = "lambo-sso-session-ids";
    /**
     * code key
     */
    private final static String LAMBO_SSO_CODE_USERNAME = "lambo-sso-code-username";

    /**
     * cookie key
     */
    private final static String LAMBO_SSO_COOKIE_KEY = "lambo-sso-key";


    @Autowired
    AuthClientSessionDao upmsSessionDao;
    @Autowired
    UpmsStUserService upmsStUserService;


    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password) {
        if (StringUtils.isBlank(username)) {
            return new BaseResult(BaseResultConstant.FAILED, "帐号不能为空！");
        }
        if (StringUtils.isBlank(password)) {
            return new BaseResult(BaseResultConstant.FAILED, "密码不能为空！");
        }
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String sessionId = session.getId().toString();
        // 判断是否已登录，如果已登录，则返回成功提示
        String hasCode = RedisUtil.get(LAMBO_SSO_CODE + "_" + sessionId);
        // code校验值
        if (StringUtils.isBlank(hasCode)) {
            // 先注销
            SecurityUtils.getSubject().logout();
            // 使用st认证
            Map result = XsmLoginUtil.getXsmLoginInfo(username,password);
            if(result != null && result.containsKey("code")){
                String code = (String) result.get("code");
                if(null == code){
                    code = "1000";
                }
                if(code.equals("0000")){//登录成功
                    String xsmUserId = (String) result.get("userId");
                    if(null != xsmUserId && !"".equals(xsmUserId)){
                        User user = XsmLoginUtil.map2User(result);
                        UpmsStUserExample upmsStUserExample = new UpmsStUserExample();
                        upmsStUserExample.or().andXsmUserIdEqualTo(user.getUserId());
                        UpmsStUser upmsStUser = upmsStUserService.selectFirstByExample(upmsStUserExample);
                        int count = 0;
                        if(null == upmsStUser){
                            upmsStUser = new UpmsStUser();
                            upmsStUser.setXsmUserId(user.getUserId());
                            upmsStUser.setNickName(user.getNickName());
                            upmsStUser.setRefId(user.getRefId());
                            upmsStUser.setUserType(user.getUserType());
                            upmsStUser.setComId(user.getComId());
                            upmsStUser.setLoginInfo(JSONUtils.toJSONString(XsmLoginUtil.user2Map(user)));
                            count = upmsStUserService.insertSelective(upmsStUser);
                        }else{
                            if(XsmLoginUtil.isUserInfoChanged(upmsStUser,user)){
                                upmsStUser.setXsmUserId(user.getUserId());
                                upmsStUser.setNickName(user.getNickName());
                                upmsStUser.setRefId(user.getRefId());
                                upmsStUser.setUserType(user.getUserType());
                                upmsStUser.setComId(user.getComId());
                                upmsStUser.setLoginInfo(JSONUtils.toJSONString(XsmLoginUtil.user2Map(user)));
                                count = upmsStUserService.updateByPrimaryKeySelective(upmsStUser);
                            }else{
                                count = 1;
                            }
                        }
                        if(count <= 0){
                            return new BaseResult(BaseResultConstant.FAILED, "登录失败");
                        }
                    }else{
                        return new BaseResult(BaseResultConstant.FAILED, "登录失败！");
                    }
                }else{
                    return new BaseResult(BaseResultConstant.FAILED, result.get("msg"));
                }
            }else{
                return new BaseResult(BaseResultConstant.FAILED, "登录失败，稍后重试");
            }

            // 更新session状态
            upmsSessionDao.updateStatus(sessionId, AuthClientSession.OnlineStatus.on_line);
            // 全局会话sessionId列表，供会话管理
            //RedisUtil.lpush(LAMBO_SSO_SESSION_IDS, sessionId.toString());

            // 默认验证帐号密码正确，创建code
            String code = IdGenerate.uuid();
            //过期时间
            int timeout = (int) subject.getSession().getTimeout() / 1000;
            // 会话的code
            RedisUtil.set(LAMBO_SSO_CODE + "_" + sessionId, code, timeout);
            // code校验值
            RedisUtil.set(LAMBO_SSO_CODE_USERNAME + "_" + code, username, timeout);
            // 保存code对应的会话sessionId，方便退出操作
            RedisUtil.sadd(LAMBO_SSO_SESSION_IDS + "_" + code, sessionId, timeout);

            //往Cookie里设置单点登录code
            CookieUtils.setCookie(ServletUtils.getResponse(), LAMBO_SSO_COOKIE_KEY, code, "/",-1);
        }

        return new BaseResult(BaseResultConstant.SUCCESS, "登录成功");
    }

    @ApiOperation(value = "新商盟登录中转页面")
    @RequestMapping(value = "/token/login", method = RequestMethod.GET)
    @LogAround("进入新商盟登录中转页面")
    public String forChange(@RequestParam(value = "token") String token, HttpServletRequest req, HttpServletResponse rep) {
        return "";
    }

    @ApiOperation(value = "token认证")
    @RequestMapping(value = "/token/dologin", method = RequestMethod.POST)
    @ResponseBody
    public Object verify(@RequestParam(value = "token") String token) {
        if(token == null || "".equals(token)){
            return new BaseResult(BaseResultConstant.FAILED, "token不能为空！");
        }
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String sessionId = session.getId().toString();
        // 判断是否已登录，如果已登录，则返回成功提示
        String hasCode = RedisUtil.get(LAMBO_SSO_CODE + "_" + sessionId);
        // code校验值
        if (StringUtils.isBlank(hasCode)) {
            // 先注销
            SecurityUtils.getSubject().logout();
            User user = XsmLoginUtil.verifyToken(token);
            if(user != null && !"".equals(user.getUserId())){
                UpmsStUserExample upmsStUserExample = new UpmsStUserExample();
                upmsStUserExample.or().andXsmUserIdEqualTo(user.getUserId());
                UpmsStUser upmsStUser = upmsStUserService.selectFirstByExample(upmsStUserExample);
                int count = 0;
                if(null == upmsStUser){
                    upmsStUser = new UpmsStUser();
                    upmsStUser.setXsmUserId(user.getUserId());
                    upmsStUser.setNickName(user.getNickName());
                    upmsStUser.setRefId(user.getRefId());
                    upmsStUser.setUserType(user.getUserType());
                    upmsStUser.setComId(user.getComId());
                    upmsStUser.setLoginInfo(JSONUtils.toJSONString(XsmLoginUtil.user2Map(user)));
                    count = upmsStUserService.insertSelective(upmsStUser);
                }else{
                    if(XsmLoginUtil.isUserInfoChanged(upmsStUser,user)){
                        upmsStUser.setXsmUserId(user.getUserId());
                        upmsStUser.setNickName(user.getNickName());
                        upmsStUser.setRefId(user.getRefId());
                        upmsStUser.setUserType(user.getUserType());
                        upmsStUser.setComId(user.getComId());
                        upmsStUser.setLoginInfo(JSONUtils.toJSONString(XsmLoginUtil.user2Map(user)));
                        count = upmsStUserService.updateByPrimaryKeySelective(upmsStUser);
                    }else{
                        count = 1;
                    }
                }
                if(count <= 0){
                    return new BaseResult(BaseResultConstant.FAILED, "登录失败");
                }
            }else{
                return new BaseResult(BaseResultConstant.FAILED, "登录失败!");
            }

            // 更新session状态
            upmsSessionDao.updateStatus(sessionId, AuthClientSession.OnlineStatus.on_line);
            // 全局会话sessionId列表，供会话管理
            //RedisUtil.lpush(LAMBO_SSO_SESSION_IDS, sessionId.toString());

            // 默认验证帐号密码正确，创建code
            String code = IdGenerate.uuid();
            //过期时间
            int timeout = (int) subject.getSession().getTimeout() / 1000;
            // 会话的code
            RedisUtil.set(LAMBO_SSO_CODE + "_" + sessionId, code, timeout);
            // code校验值
            RedisUtil.set(LAMBO_SSO_CODE_USERNAME + "_" + code, user.getUserId(), timeout);
            // 保存code对应的会话sessionId，方便退出操作
            RedisUtil.sadd(LAMBO_SSO_SESSION_IDS + "_" + code, sessionId, timeout);

            //往Cookie里设置单点登录code
            CookieUtils.setCookie(ServletUtils.getResponse(), LAMBO_SSO_COOKIE_KEY, code, "/",-1);
        }
        return new BaseResult(BaseResultConstant.SUCCESS, "登录成功");
    }

    @ApiOperation(value = "退出登录")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public Object logout() {
        // shiro退出登录
        SecurityUtils.getSubject().logout();
        return new BaseResult(BaseResultConstant.SUCCESS, "注销成功");
    }

}