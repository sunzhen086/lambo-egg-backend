package com.lambo.auth.shiro.realm;

import com.lambo.auth.dao.model.UpmsPermission;
import com.lambo.auth.dao.model.UpmsRole;
import com.lambo.auth.dao.model.UpmsStUser;
import com.lambo.auth.rpc.api.AuthClientApiService;
import com.lambo.common.utils.codec.Md5Utils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户认证和授权
 * Created by lambo on 2017/1/20.
 */
public class AuthClientRealm extends AuthorizingRealm {

    private static Logger logger = LoggerFactory.getLogger(AuthClientRealm.class);

    @Autowired
    private AuthClientApiService authClientApiServiceProvider;

    /**
     * 授权：验证权限时调用
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        UpmsStUser upmsStUser = authClientApiServiceProvider.selectUpmsUserByXsmUserId(username);

        // 当前用户所有角色
        List<UpmsRole> upmsRoles = authClientApiServiceProvider.selectUpmsRoleByUpmsUserId(upmsStUser.getUserId());
        Set<String> roles = new HashSet<>();
        for (UpmsRole upmsRole : upmsRoles) {
            if (StringUtils.isNotBlank(upmsRole.getName())) {
                roles.add(upmsRole.getName());
            }
        }

        // 当前用户所有权限
        List<UpmsPermission> upmsPermissions = authClientApiServiceProvider.selectUpmsPermissionByUpmsUserId(upmsStUser.getUserId());
        Set<String> permissions = new HashSet<>();
        for (UpmsPermission upmsPermission : upmsPermissions) {
            if (StringUtils.isNotBlank(upmsPermission.getPermissionValue())) {
                permissions.add(upmsPermission.getPermissionValue());
            }
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证：登录时调用
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        /**
         * 免密单点登录
         */
        if(StringUtils.isBlank(password)){
            return new SimpleAuthenticationInfo(username, password, getName());
        }

        // 查询用户信息
        UpmsStUser upmsStUser = authClientApiServiceProvider.selectUpmsUserByXsmUserId(username);

        if (null == upmsStUser) {
            throw new UnknownAccountException();
        }
        if (!upmsStUser.getPassword().equalsIgnoreCase(Md5Utils.md5(password))) {
            throw new IncorrectCredentialsException();
        }

        return new SimpleAuthenticationInfo(username, password, getName());
    }

}
