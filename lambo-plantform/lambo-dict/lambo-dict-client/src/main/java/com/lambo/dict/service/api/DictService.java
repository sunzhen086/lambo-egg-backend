package com.lambo.dict.service.api;




import java.util.List;

/**
 * @author zxc
 * @ClassName DictService
 * @Descirption TODO
 * @Date 2018/6/29 14:17
 **/
public interface DictService {

    /**
     * 获取数据字典
     * @param
     * @return
     */
  public List getDictData(String key);

}
