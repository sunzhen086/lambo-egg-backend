package com.lambo.auth.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.lambo.auth.dao.api.AuthClientApiMapper;
import com.lambo.auth.dao.api.UpmsStUserMapper;
import com.lambo.auth.dao.model.*;
import com.lambo.auth.rpc.api.AuthClientApiService;
import com.lambo.common.utils.io.PropertiesFileUtil;
import com.lambo.common.utils.io.RedisUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;


/**
 * UpmsApiService实现
 * Created by lambo on 2016/01/19.
 */
public class AuthClientApiServiceImpl implements AuthClientApiService {

    private static Logger logger = LoggerFactory.getLogger(AuthClientApiServiceImpl.class);

    private static String SYSTEM_ID = PropertiesFileUtil.getInstance("config").get("upms.system.id");
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
    AuthClientApiMapper authClientApiMapper;

    @Autowired
    UpmsStUserMapper upmsStUserMapper;

    /**
     * 根据用户id获取所拥有的权限
     * @param upmsUserId
     * @return
     */
    @Override
    public List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId) {
        // 用户不存在或锁定状态
        UpmsStUser upmsStUser = upmsStUserMapper.selectByPrimaryKey(upmsUserId);
        if (null == upmsStUser) {
            logger.info("selectUpmsPermissionByUpmsUserId : upmsUserId={}", upmsUserId);
            return null;
        }
        List<UpmsPermission> upmsPermissions = authClientApiMapper.selectUpmsPermissionByUpmsUserId(upmsUserId,Integer.parseInt(SYSTEM_ID));
        return upmsPermissions;
    }
    /**
     * 根据用户新商盟id获取所拥有的权限
     * @param xsmUserId
     * @return
     */
    @Override
    public List<UpmsPermission> selectUpmsPermissionByXsmUserId(String xsmUserId) {
        // 用户不存在或锁定状态
        UpmsStUserExample upmsStUserExample = new UpmsStUserExample();
        upmsStUserExample.createCriteria().andXsmUserIdEqualTo(xsmUserId);
        List<UpmsStUser> upmsStUser = upmsStUserMapper.selectByExampleWithBLOBs(upmsStUserExample);
        if (null == upmsStUser || upmsStUser.size() <= 0) {
            logger.info("selectUpmsPermissionByXsmUserId : xsmUserId={}", xsmUserId);
            return null;
        }
        List<UpmsPermission> upmsPermissions = authClientApiMapper.selectUpmsPermissionByUpmsUserId(upmsStUser.get(0).getUserId(),Integer.parseInt(SYSTEM_ID));
        return upmsPermissions;
    }

    /**
     * 根据用户id获取所属的角色
     * @param upmsUserId
     * @return
     */
    @Override
    public List<UpmsRole> selectUpmsRoleByUpmsUserId(Integer upmsUserId) {
        // 用户不存在
        UpmsStUser upmsStUser = upmsStUserMapper.selectByPrimaryKey(upmsUserId);
        if (null == upmsStUser) {
            logger.info("selectUpmsRoleByUpmsUserId : upmsUserId={}", upmsUserId);
            return null;
        }
        List<UpmsRole> upmsRoles = authClientApiMapper.selectUpmsRoleByUpmsUserId(upmsUserId);
        return upmsRoles;
    }
    /**
     * 根据用户id获取所属的角色
     * @param xsmUserId
     * @return
     */
    @Override
    public List<UpmsRole> selectUpmsRoleByXsmUserId(String xsmUserId) {
        // 用户不存在
        // 用户不存在或锁定状态
        UpmsStUserExample upmsStUserExample = new UpmsStUserExample();
        upmsStUserExample.createCriteria().andXsmUserIdEqualTo(xsmUserId);
        List<UpmsStUser> upmsStUser = upmsStUserMapper.selectByExampleWithBLOBs(upmsStUserExample);
        if (null == upmsStUser || upmsStUser.size() <= 0) {
            logger.info("selectUpmsRoleByXsmUserId : xsmUserId={}", xsmUserId);
            return null;
        }
        List<UpmsRole> upmsRoles = authClientApiMapper.selectUpmsRoleByUpmsUserId(upmsStUser.get(0).getUserId());
        return upmsRoles;
    }

    /**
     * 根据username获取UpmsUser
     * @param xsmUserId
     * @return
     */
    @Override
    public UpmsStUser selectUpmsUserByXsmUserId(String xsmUserId) {
        UpmsStUserExample upmsStUserExample = new UpmsStUserExample();
        upmsStUserExample.createCriteria().andXsmUserIdEqualTo(xsmUserId);
        List<UpmsStUser> upmsStUsers = upmsStUserMapper.selectByExampleWithBLOBs(upmsStUserExample);
        if (null != upmsStUsers && upmsStUsers.size() > 0) {
            return upmsStUsers.get(0);
        }
        return null;
    }

    /**
     * 根据userId获取UpmsStUser
     * @param upmsUserId
     * @return
     */
    @Override
    public UpmsStUser selectUpmsUserByUpmsUserId(Integer upmsUserId) {
        return upmsStUserMapper.selectByPrimaryKey(upmsUserId);
    }

    /**
     * 根据系统id获取上下文根
     * @param sysId
     * @return
     */
    public String getSysRootBySysId(Integer sysId){
        Map sysInfo = authClientApiMapper.selectSystemInfo(sysId);
        if(sysInfo != null && sysInfo.containsKey("basepath")){
            return (String) sysInfo.get("basepath");
        }
        return null;
    }
}