package com.lambo.auth.service.impl;

import com.lambo.auth.dao.api.AuthClientApiMapper;
import com.lambo.auth.dao.api.UpmsStUserMapper;
import com.lambo.auth.dao.model.*;
import com.lambo.auth.rpc.api.AuthClientApiService;
import com.lambo.common.utils.io.PropertiesFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * UpmsApiService实现
 * Created by lambo on 2016/01/19.
 */
public class AuthClientApiServiceImpl implements AuthClientApiService {

    private static Logger logger = LoggerFactory.getLogger(AuthClientApiServiceImpl.class);

    private static String SYSTEM_ID = PropertiesFileUtil.getInstance("config").get("upms.system.id");

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

}