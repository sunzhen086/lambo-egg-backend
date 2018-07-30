package com.lambo.auth.util;

import com.lambo.auth.dao.model.UpmsPermission;
import com.lambo.auth.dao.model.UpmsRole;
import com.lambo.auth.dao.model.UpmsStUser;
import com.lambo.auth.rpc.api.AuthClientApiService;
import com.lambo.common.utils.io.PropertiesFileUtil;
import com.lambo.common.utils.spring.SpringContextUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * @title:用户会话
 * @description:取当前的用户会话信息，基于楼上平台的用户信息进行封装
 * @author wangjierj
 * @date:2018-7-26
 */
public class UserInfoUtil {

    private static AuthClientApiService authClientApiService = (AuthClientApiService)SpringContextUtil.getBean("authClientApiService");
    /**
     * 获取当前用户的userInfo
     *
     * @return userInfo
     */
    public static UpmsStUser getUserInfo() {
        Subject subject = SecurityUtils.getSubject();
        String xsmUserId = (String) subject.getPrincipal();
        UpmsStUser upmsStUser = authClientApiService.selectUpmsUserByXsmUserId(xsmUserId);
        return upmsStUser;
    }
    /**
     * 取用户所属公司，默认我新商盟的公司id
     *
     * @return 公司编码
     */
    public static String getComId() {
        Subject subject = SecurityUtils.getSubject();
        String xsmUserId = (String) subject.getPrincipal();
        UpmsStUser upmsStUser = authClientApiService.selectUpmsUserByXsmUserId(xsmUserId);
        return upmsStUser.getComId();
    }
    /**
     * 取用户所属工业公司
     *
     * @return 公司编码
     */
    public static String getIndustryComId() {
        Subject subject = SecurityUtils.getSubject();
        String xsmUserId = (String) subject.getPrincipal();
        UpmsStUser upmsStUser = authClientApiService.selectUpmsUserByXsmUserId(xsmUserId);
        return upmsStUser.getComId();
    }
    /**
     * 获取用户对应v6的组织代码
     *
     * @return 用户对应v6的组织代码
     */
    public static String getUserRefId() {
        Subject subject = SecurityUtils.getSubject();
        String xsmUserId = (String) subject.getPrincipal();
        UpmsStUser upmsStUser = authClientApiService.selectUpmsUserByXsmUserId(xsmUserId);
        return upmsStUser.getRefId();
    }
    /**
     * 获取当前登录用户的新商盟的用户类型
     *
     * @return 返回值用户的类型
     */
    public static String getUserType() {
        Subject subject = SecurityUtils.getSubject();
        String xsmUserId = (String) subject.getPrincipal();
        UpmsStUser upmsStUser = authClientApiService.selectUpmsUserByXsmUserId(xsmUserId);
        return upmsStUser.getUserType();
    }
    /**
     * 获取用户编码
     *
     * @return 用户编码
     */
    public static String getUserId() {
        Subject subject = SecurityUtils.getSubject();
        return (String) subject.getPrincipal();
    }

    /**
     * 获取用户名称
     *
     * @return 用户名称
     */
    public static String getUserName() {
        Subject subject = SecurityUtils.getSubject();
        String xsmUserId = (String) subject.getPrincipal();
        UpmsStUser upmsStUser = authClientApiService.selectUpmsUserByXsmUserId(xsmUserId);
        return upmsStUser.getNickName();
    }

    /**
     * 获取当前登录用户能访问的组织数据权限
     * @return 返回值为组织ID的集合
     */
    public static List<String> getUserOrganDataPermit(String organType) {
        Subject subject = SecurityUtils.getSubject();
        String xsmUserId = (String) subject.getPrincipal();
        UpmsStUser upmsStUser = authClientApiService.selectUpmsUserByXsmUserId(xsmUserId);
        List<String> permits = new ArrayList<String>();
        permits.add(upmsStUser.getComId());
        return permits;
    }

    /**
     * 获取默认的组织数据权限
     *
     * @param struType
     * @param organType
     * @return 返回值为默认权限组织的ID
     */
    public static String getDefaultUserDataPermitId(String struType,String organType) {
        Subject subject = SecurityUtils.getSubject();
        String xsmUserId = (String) subject.getPrincipal();
        UpmsStUser upmsStUser = authClientApiService.selectUpmsUserByXsmUserId(xsmUserId);
        return upmsStUser.getComId();
    }

    /**
     * 判断当前用户是否具有某操作的权限
     *
     * @param operCode
     *            操作编码
     * @return
     */
    public static boolean hasPermissionByOperCode(String operCode) {
        Subject subject = SecurityUtils.getSubject();
        String xsmUserId = (String) subject.getPrincipal();
        List<UpmsPermission> permissions = authClientApiService.selectUpmsPermissionByXsmUserId(xsmUserId);
        if(permissions != null && permissions.size()>0){
            for(int i=0;i<permissions.size();i++){
                UpmsPermission permit = permissions.get(i);
                if(permit.getPermissionValue().equals(operCode)){
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 判断用户是否系统管理员
     * @param xsmUserId
     * @return
     */
    public static boolean isAdmin(String xsmUserId) {
        if(xsmUserId != null && !"".equals(xsmUserId)){
            List<UpmsRole> roles = authClientApiService.selectUpmsRoleByXsmUserId(xsmUserId);
            if(roles != null && roles.size()>0){
                for(int i=0;i<roles.size();i++){
                    UpmsRole role = roles.get(i);
                    if(role.getRoleId().equals("1")){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 判断当前用户是否系统管理员
     *
     * @return
     */
    public static boolean isAdmin() {
        return isAdmin(getUserId());
    }

    /**
     * 根据应用编码获取统一资源标识
     *
     * @param systemId
     * @return 上下文根
     */
    public static String getRootBySystemId(Integer systemId) {
        if(systemId == null){
            String sysId = PropertiesFileUtil.getInstance("config").get("upms.system.id");
            systemId = Integer.parseInt(sysId);
        }
        AuthClientApiService authClientApiService = (AuthClientApiService)SpringContextUtil.getBean("authClientApiService");
        String basePath = authClientApiService.getSysRootBySysId(systemId);
        return basePath;
    }

}
