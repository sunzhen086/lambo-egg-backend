package com.lambo.auth.client.shiro.filter;

import com.lambo.auth.client.constant.AuthClientResult;
import com.lambo.common.utils.web.ResponseUtil;
import com.lambo.auth.client.constant.AuthClientResultConstant;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 重写authc过滤器
 * Created by lambo on 2017/3/11.
 */
public class UpmsAuthenticationFilter extends AuthenticationFilter {

    private final static Logger logger = LoggerFactory.getLogger(UpmsAuthenticationFilter.class);


    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);
        return subject.isAuthenticated();
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        ResponseUtil.sendJson(response,new AuthClientResult(AuthClientResultConstant.ACCESS_DENIED,"未登录或会话已失效"));
        return false;
    }

}
