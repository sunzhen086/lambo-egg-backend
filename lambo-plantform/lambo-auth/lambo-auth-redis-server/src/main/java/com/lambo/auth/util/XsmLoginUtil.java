package com.lambo.auth.util;

import com.alibaba.druid.support.json.JSONUtils;
import com.lambo.auth.dao.model.UpmsStUser;
import com.lambo.common.utils.io.PropertiesFileUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.loushang.bsp.bean.User;
import org.loushang.bsp.sso.client.util.TokenConsumerUtil;
import org.loushang.bsp.sso.server.util.GetToken;
import org.loushang.util.KeyHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class XsmLoginUtil {
    private static final Logger log = LoggerFactory.getLogger(XsmLoginUtil.class);
    private static PropertiesFileUtil authConfig = PropertiesFileUtil.getInstance("auth");
    private static String xsmApache = authConfig.get("XSM_APACHE");
    private static String xsmCodeUrl = authConfig.get("XSM_CODE_URL");
    private static String xsmLoginUrl = authConfig.get("XSM_LOGIN_URL");
    private static String xsmIsOpenProxy = authConfig.get("IS_NEED_PROXY");
    private static String xsmProxyHost = authConfig.get("PROXY_HOST");
    private static String xsmProxyPort = authConfig.get("PROXY_PORT");

    /**
     * 功能:验证新商盟登录
     *
     * @param userId
     *            登录账号（公共账号） 例:INTER_11220101
     * @param userPsw
     *            登录密码（公共账号）例:1
     * @return map
     */
    public static Map getXsmLoginInfo(String userId,String userPsw) {
        Map retMap = null;
        try {
            // 代理取验证码
            GetMethod get = new GetMethod(xsmApache + xsmCodeUrl);
            HttpClient client = new HttpClient();
            if(xsmIsOpenProxy!=null && "1".equals(xsmIsOpenProxy) && xsmProxyHost !=null && !"".equals(xsmProxyHost) ){
                if(xsmProxyPort !=null && !"".equals(xsmProxyPort)){
                    client.getHostConfiguration().setProxy(xsmProxyHost, Integer.parseInt(xsmProxyPort));
                }else{
                    client.getHostConfiguration().setProxy(xsmProxyPort, 80);
                }
                client.getParams().setAuthenticationPreemptive(true);
            }
            client.executeMethod(get);
            if (get.getStatusCode() != HttpStatus.SC_OK) {
                if(log.isDebugEnabled()){
                    log.debug("getXsmLoginInfo --- 验证码失败 status="+get.getStatusCode());
                }
            } else {
                if(log.isDebugEnabled()){
                    log.debug("getXsmLoginInfo --- 验证码通过");
                }
                BufferedReader bufferReader = new BufferedReader(new InputStreamReader(get.getResponseBodyAsStream(),"UTF-8"));
                StringBuffer stringBuffer = new StringBuffer();
                String tmpStr = "";
                while((tmpStr=bufferReader.readLine())!=null){
                    stringBuffer.append(tmpStr);
                }
                String ret = stringBuffer.toString();
                if(null!=ret&&!ret.equals("")){
                    Document doc = null;
                    doc = DocumentHelper.parseText(ret);
                    if(null!=doc){
                        Element root = null;
                        root = doc.getRootElement();
                        if (root != null) {
                            if(null!=root && root.attributes()!=null){
                                for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
                                    Element recordElement = (Element) iterator.next();
                                    if("challenge".equals(recordElement.getName())){
                                        String challenge = recordElement.getText();
                                        if (challenge != null && challenge.length() > 0) {
                                            // 判断登陆
                                            String username = userId;
                                            String password = md5Hashed(userPsw);
                                            password = md5Hashed(password + challenge);
                                            get = new GetMethod(xsmApache + xsmLoginUrl + "?j_username="
                                                    + username + "&j_password=" + password);
                                            client.executeMethod(get);
                                            if (get.getStatusCode() != HttpStatus.SC_OK) {
                                                if(log.isDebugEnabled()){
                                                    log.debug("getXsmLoginInfo --- 用户登陆失败 status="+get.getStatusCode());
                                                }
                                            } else {
                                                if(log.isDebugEnabled()){
                                                    log.debug("getXsmLoginInfo --- 用户登陆成功");
                                                }
                                                String loginInfo = get.getResponseBodyAsString();
                                                retMap = userStr2Map(loginInfo);
                                            }
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } catch (HttpException e) {
            if(log.isErrorEnabled()){
                log.error("getXsmLoginInfo --- HttpException=",e);
            }
        } catch (IOException e) {
            if(log.isErrorEnabled()){
                log.error("getXsmLoginInfo --- IOException=",e);
            }
        } catch (DocumentException e) {
            if(log.isErrorEnabled()){
                log.error("getXsmLoginInfo --- DocumentException=",e);
            }
        } catch (Exception e) {
            if(log.isErrorEnabled()){
                log.error("getXsmLoginInfo --- Exception=",e);
            }
        }
        return retMap;
    }
    /**
     * 功能:对字符串进行MD5加密
     *
     * @param plainText
     *            待加密的字符串
     * @return String
     */
    private static String md5Hashed(String plainText) {
        byte[] temp = (plainText + "{1#2$3%4(5)6@7!poeeww$3%4(5)djjkkldss}")
                .getBytes();
        // byte[] temp = (plainText).getBytes();
        MessageDigest md;
        // 返回结果
        StringBuffer buffer = new StringBuffer();
        try {
            // 进行MD5散列
            md = MessageDigest.getInstance("md5");
            md.update(temp);
            temp = md.digest();
            // 将散列的结果转换为Hex字符串
            int i = 0;
            for (int offset = 0; offset < temp.length; offset++) {
                i = temp[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buffer.append("0");
                buffer.append(Integer.toHexString(i));
            }
        } catch (GeneralSecurityException e) {
            if(log.isErrorEnabled()){
                log.error("md5Hashed --- GeneralSecurityException=",e);
            }
        } catch (Exception e) {
            if(log.isErrorEnabled()){
                log.error("md5Hashed --- Exception=",e);
            }
        }
        // 返回
        return buffer.toString();
    }

    /**
     * 将string类型的xml数据转换为map
     * @param str
     * @return
     */
    public static Map userStr2Map(String str){
        Map result = new HashMap();
        try {
            Document doc = null;
            doc = DocumentHelper.parseText(str);
            if(null!=doc) {
                Element root = null;
                root = doc.getRootElement();
                if (root != null && root.attributes() != null) {
                    result.put("code",root.attributeValue("code"));
                    result.put("msg",root.attributeValue("msg"));
                    for (Iterator iterator = root.elementIterator(); iterator.hasNext(); ) {
                        Element recordElement = (Element) iterator.next();
                        result.put(recordElement.getName(),recordElement.getText());
                    }
                }
            }
        }catch (DocumentException e) {
            if(log.isErrorEnabled()){
                log.error("userStr2Map --- DocumentException=",e);
            }
        } catch (Exception e) {
            if(log.isErrorEnabled()){
                log.error("userStr2Map --- Exception=",e);
            }
        }
        return result;
    }

    /**
     * 新商盟token验证
     * @param token
     * @return
     */
    public static User verifyToken(String token){
        User user = null;
        try {
            user = TokenConsumerUtil.verifyToken(token, "gbk", KeyHolder.getPublicKey());
        }catch (Exception e) {
            if(log.isErrorEnabled()){
                log.error("verifyToken --- Exception=",e);
            }
        }
        return user;
    }

    /**
     * 产生新商盟的token
     * @param user
     * @return
     */
    public static String createToken(User user){
       return GetToken.getUserToken(user);
    }

    /**
     * 将新商盟的user转换为通用的map
     * @param user
     * @return
     */
    public static Map user2Map(User user){
        Map userMap = new HashMap();
        userMap.put("userId",user.getUserId());
        userMap.put("nickName",user.getNickName());
        userMap.put("userType",user.getUserType());
        userMap.put("comId",user.getComId());
        userMap.put("saledptId",user.getSaleDptId());
        userMap.put("refId",user.getRefId());
        userMap.put("comName",user.getComName());
        userMap.put("domainUrl",user.getDomainUrl());
        userMap.put("comType",user.getComType());
        userMap.put("comShort",user.getComShort());
        userMap.put("parentComId",user.getParentComId());
        return userMap;
    }

    /**
     * Map转换为新商盟的User类
     * @param userMap
     * @return
     */
    public static User map2User(Map userMap){
        User user = new User();
        user.setUserId((String) userMap.get("userId"));
        user.setNickName((String) userMap.get("nickName"));
        user.setUserType((String) userMap.get("userType"));
        user.setComId((String) userMap.get("comId"));
        user.setSaleDptId((String) userMap.get("saledptId"));
        user.setRefId((String) userMap.get("refId"));
        user.setComName((String) userMap.get("comName"));
        user.setDomainUrl((String) userMap.get("domainUrl"));
        user.setComType((String) userMap.get("comType"));
        user.setComShort((String) userMap.get("comShort"));
        user.setParentComId((String) userMap.get("parentComId"));
        return user;
    }

    /**
     * 对比用户信息是否发生变化
     * @return
     */
    public static boolean isUserInfoChanged(UpmsStUser oldUser, User newUser){
        if(!oldUser.getXsmUserId().equals(newUser.getUserId())){
            return true;
        }
        if(!oldUser.getNickName().equals(newUser.getNickName())){
            return true;
        }
        if(!oldUser.getRefId().equals(newUser.getRefId())){
            return true;
        }
        if(!oldUser.getUserType().equals(newUser.getUserType())){
            return true;
        }
        if(!oldUser.getComId().equals(newUser.getComId())){
            return true;
        }
        if(null == oldUser.getLoginInfo() || "".equals(oldUser.getLoginInfo())){
            return true;
        }
        Map loginInfo = (Map)JSONUtils.parse(oldUser.getLoginInfo());
        if(!loginInfo.get("comName").equals(newUser.getComName())){
            return true;
        }
        if(!loginInfo.get("comShort").equals(newUser.getComShort())){
            return true;
        }
        if(!loginInfo.get("parentComId").equals(newUser.getParentComId())){
            return true;
        }
        if(!loginInfo.get("saledptId").equals(newUser.getSaleDptId())){
            return true;
        }
        if(!loginInfo.get("comType").equals(newUser.getComType())){
            return true;
        }
        return false;
    }

    public static void main(String[] args){
        String str = "<?xml version=\"1.0\" encoding=\"gbk\"?>\n" +
                "<xsm code=\"0000\" msg=\"验证成功\" trans_time=\"20180713151440\">\n" +
                "<userId>DLYC888</userId>\n" +
                "<nickName>1</nickName>\n" +
                "<userType>2210</userType>\n" +
                "<passwordStatus>0</passwordStatus>\n" +
                "<comId>11210201</comId>\n" +
                "<saledptId>0111</saledptId>\n" +
                "<refId>101618</refId>\n" +
                "<comName>大连市烟草公司</comName>\n" +
                "<domainUrl>http://home.xinshangmeng.com/home/index/dl.htm?v=1531466080011</domainUrl>\n" +
                "<comType>02</comType>\n" +
                "<comShort>大连市</comShort>\n" +
                "<parentComId>11000001</parentComId>\n" +
                "<expirationTime>1531467880015</expirationTime>\n" +
                "<planText>DLYC88812210112102010111101618大连市烟草公司02大连市110000011531467880015</planText>\n" +
                "<signatureValue>c67f6bb3d9d7dc9b01f0eef734447efa87ffd7679adae7bae355b21fdfde841f6b9535e3b28f7b6e1b9e01fcd8313271eca2881d7228c743196bd02dfb1d6648927e70a1f9e647b731b641090e70b5bdd09fc062be5a4a6d0d5e3328c4f63b6346003709fca5f78e8d564f45673b27adfae3c41b4c83ee905cc86b08cf9199a0</signatureValue>\n" +
                "</xsm>";
        Map ret = userStr2Map(str);
        System.out.println("ret= "+ JSONUtils.toJSONString(ret));
        User user = map2User(ret);
        System.out.println("user = "+user);
        /*String token = createToken(user);
        System.out.println("token = "+token);
        User user1 = verifyToken(token);
        System.out.println("user1 = "+user1);*/
        Map userMap = user2Map(user);
        System.out.println("userMap= "+ JSONUtils.toJSONString(userMap));
        String loginInfo = "{\"comShort\":\"铜川\",\"comType\":\"02\",\"nickName\":\"东村商店\",\"comName\":\"铜川分公司\",\"userId\":\"610222100306\",\"parentComId\":\"11610001\",\"domainUrl\":\"http://10.110.1.176/home/index.htm?v=1532742633294\",\"refId\":\"610222100306\",\"comId\":\"11610201\",\"saledptId\":\"11610222\",\"userType\":\"1000\"}";
        Map loginInfos = (Map)JSONUtils.parse(loginInfo);
        System.out.println("loginInfos= "+ loginInfos);
    }
}
