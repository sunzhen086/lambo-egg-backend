package com.lambo.auth.util;

import com.lambo.auth.dao.model.UpmsStUser;

import java.util.HashMap;
import java.util.Map;

public class UpmsStUserUtil {
    public static Map upmsStUser2Map(UpmsStUser upmsStUser){
        Map result = new HashMap();
        result.put("userId",upmsStUser.getUserId());
        result.put("xsmUserId",upmsStUser.getXsmUserId());
        result.put("password",upmsStUser.getPassword());
        result.put("nickName",upmsStUser.getNickName());
        result.put("refId",upmsStUser.getRefId());
        result.put("userType",upmsStUser.getUserType());
        result.put("comId",upmsStUser.getComId());
        result.put("loginInfo",upmsStUser.getLoginInfo());
        return result;
    }
    public static UpmsStUser map2UpmsStUser(Map userMap){
        UpmsStUser upmsStUser = new UpmsStUser();
        upmsStUser.setUserId((Integer)userMap.get("userId"));
        upmsStUser.setXsmUserId((String)userMap.get("xsmUserId"));
        upmsStUser.setPassword((String)userMap.get("password"));
        upmsStUser.setNickName((String)userMap.get("nickName"));
        upmsStUser.setRefId((String)userMap.get("refId"));
        upmsStUser.setUserType((String)userMap.get("userType"));
        upmsStUser.setComId((String)userMap.get("comId"));
        upmsStUser.setLoginInfo((String)userMap.get("loginInfo"));
        return upmsStUser;
    }
}
