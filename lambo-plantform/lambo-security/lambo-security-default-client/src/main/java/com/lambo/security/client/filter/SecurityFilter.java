package com.lambo.security.client.filter;

import com.lambo.common.utils.io.PropertiesFileUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 安全拦截器
 */
@WebFilter(filterName = "securityFilter", urlPatterns = "/*")
public class SecurityFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(SecurityHttpServletRequestWrapper.class);
    boolean exist = true;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestUrl = httpServletRequest.getRequestURI();
        String excludeUrl = "";
        if (exist) {
            try {
                excludeUrl = PropertiesFileUtil.getInstance("security-default-client").get("excludeUrl");
            } catch (Exception e) {
                exist = false;
            }
        }
        if (StringUtils.isNotBlank(excludeUrl)) {
            if(StringUtils.equals(excludeUrl,"ALL")){
                chain.doFilter(httpServletRequest, response);
            }
            String[] urls = excludeUrl.split(";");
            for (String url : urls) {
                url = url.replace("/*","");
                if(StringUtils.isNotBlank(url) && requestUrl.contains(url)){
                    chain.doFilter(httpServletRequest, response);
                }else{
                    SecurityHttpServletRequestWrapper securityRequest = new SecurityHttpServletRequestWrapper(httpServletRequest);
                    chain.doFilter(securityRequest, response);
                }
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}