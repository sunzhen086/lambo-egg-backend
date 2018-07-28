
package com.lambo.rule.rpc.api;

import com.lambo.common.utils.spring.SpringContextUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典工具类
 * by:zxc
 * Date: 2018年7月20日
 */
public class RuleUtil {

private static	RuleService ruleService;
	private static Log log = LogFactory.getLog(RuleUtil.class);
private static RuleService getRuleService(){
	if(ruleService==null){
		Object bean = SpringContextUtil.getBean("ruleService");
		if(bean==null){
			log.error("ruleService bean is null!");
		}
		ruleService = (RuleService) bean;
	}
	return ruleService;
}

	public static  List<Map<String,String>>  getRuleList(String ruleId){
		List<Map<String,String>> result = getRuleService().getRuleDataList(ruleId);
		return result;
	}

	public static  String  getRuleJson(String ruleId){
		String result = getRuleService().getRuleJson(ruleId);
		return result;
	}
	public static  String  getRuleValue(String ruleId,String organId){
		String result = getRuleService().getRuleValue(ruleId,organId);
		return result;
	}
}