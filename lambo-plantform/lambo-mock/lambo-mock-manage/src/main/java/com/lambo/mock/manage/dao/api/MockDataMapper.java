package com.lambo.mock.manage.dao.api;

import java.util.List;
import java.util.Map;

/**
 * @ClassName MockDataMapper
 * @Descirption 统计数据
 * @Author cuiyh
 * @Date 2018/8/1
 **/
public interface MockDataMapper {

    /**
     * 统计文件夹下所有可用服务的状态
     * @param mockUrl
     * @return
     */
    List<Map> selectDevStatusByMockUrl(String mockUrl);

    /**
     * 获取数据服务的开发状态
     * @param mockId
     * @return
     */
    Map selectDevStatusByMockId(String mockId);

}
