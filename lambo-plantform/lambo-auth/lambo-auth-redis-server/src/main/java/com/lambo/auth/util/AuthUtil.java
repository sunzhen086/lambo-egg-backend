package com.lambo.auth.util;

import com.lambo.auth.dao.model.UpmsStUser;
import com.lambo.common.utils.io.PropertiesFileUtil;

public class AuthUtil {

    public static PropertiesFileUtil authConfig = PropertiesFileUtil.getInstance("auth");

    /**
     * 获取登录页
     * @return
     */
    public static String getLoginPage(){
        return authConfig.get("XSM_APACHE") + authConfig.get("XSM_LOGIN_PAGE");
    }
    /**
     * 获取指定用户类型的首页
     * @param userType
     * @return
     */
    public static String getDefaultHomepage(String userType){
        if(userType == null || "".equals(userType)){
            return authConfig.get("CUST_HOMEPAGE");
        }else if(userType.equals("2210")){
            return authConfig.get("CUST_HOMEPAGE");
        }else{
            return authConfig.get("MANAGE_HOMEPAGE");
        }
    }
}
