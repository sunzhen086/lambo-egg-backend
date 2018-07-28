
package com.lambo.dict.rpc.api;

import com.lambo.common.utils.spring.SpringContextUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典工具类
 * by:zxc
 * Date: 2018年7月20日
 */
public class DictUtil {

private static	DictService dictService;
	private static Log log = LogFactory.getLog(DictUtil.class);
private static DictService getDictService(){
	if(dictService==null){
		Object bean = SpringContextUtil.getBean("dictService");
		if(bean==null){
			log.error("dictService bean is null!");
		}
		dictService = (DictService) bean;
	}
	return dictService;
}

	public static  List<Map<String,String>>  getDictList(String dictId){
		List<Map<String,String>> result = getDictService().getDictDataList(dictId);
		return result;
	}

	public static LinkedHashMap<String, String> getDictMap(String dictId){
		LinkedHashMap<String, String> result = getDictService().getDictMap(dictId);
		return result;
	}

	public static  String  getDictJson(String dictId){
		String result = getDictService().getDictMapJson(dictId);
		return result;
	}

	public static  String  getDictValue(String dictId,String key){
		String result = getDictService().getDictValue(dictId,key);
		return result;
	}
}