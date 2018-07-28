package com.lambo.auth.util;

import java.util.Set;

/**
 * @title:用户会话
 * @description:取当前的用户会话信息，基于楼上平台的用户信息进行封装
 * @author wangjierj
 * @date:2018-7-26
 */
public class UserInfo {
    /**
     * 取用户所属公司，默认我新商盟的公司id
     *
     * @return 公司编码
     */
    public static String getComId() {
        return null;
    }
    /**
     * 取用户所属工业公司
     *
     * @return 公司编码
     */
    public static String getIndustryComId() {
        return null;
    }
    /**
     * 获取用户对应v6的组织代码
     *
     * @return 用户对应v6的组织代码
     */
    public static String getUserRefId() {
        return null;
    }
    /**
     * 获取当前登录用户的新商盟的用户类型
     *
     * @return 返回值用户的类型
     */
    public static String getUserType() {
        return null;
    }
    /**
     * 获取用户编码
     *
     * @return 用户编码
     */
    public static String getUserId() {
        return null;
    }

    /**
     * 获取用户名称
     *
     * @return 用户名称
     */
    public static String getUserName() {
        return null;
    }

    /**
     * 获取当前登录用户能访问的组织数据权限
     * @return 返回值为组织ID的集合
     */
    public static Set<String> getUserOrganDataPermit(String organType) {
        return null;
    }

    /**
     * 获取默认的组织数据权限
     *
     * @param struType
     * @param organType
     * @return 返回值为默认权限组织的ID
     */
    public static String getDefaultUserDataPermitId(String struType,String organType) {
        return null;
    }

    /**
     * 判断当前用户是否具有某操作的权限
     *
     * @param operCode
     *            操作编码
     * @return
     */
    public static boolean hasPermissionByOperCode(String operCode) {
        return false;
    }
    /**
     * 判断用户是否系统管理员
     * @param userId
     * @return
     */
    public static boolean isAdmin(String userId) {
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
     * @param appCode
     * @return uri
     */
    public static String getUriByAppCode(String appCode) {
        return null;
    }
}
