package com.lambo.security.client.filter;


import com.lambo.common.utils.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SQL过滤
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-01 16:16
 */
public class SQLFilter {

    private static Logger logger = LoggerFactory.getLogger(HTMLFilter.class);

    /**
     * SQL注入过滤
     *
     * @param str 待验证的字符串
     */
    public static String sqlInject(String str) {
        if (logger.isDebugEnabled()) {
            logger.debug("************************************************");
            logger.debug("INPUT: " + str);
        }
        if (StringUtils.isBlank(str)) {
            return null;
        }
        //去掉'|"|;|\字符
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        //转换成小写
        str = str.toLowerCase();

        //非法字符
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alter", "drop"};

        //判断是否包含非法字符
        for (String keyword : keywords) {
            if (str.contains(keyword + " ")) {
                str = "非法的字符'" + keyword + "'";
            }
        }

        if (logger.isDebugEnabled()) {
            logger.debug("OUTPUT: " + str);
            logger.debug("************************************************");
        }

        return str;
    }
}
