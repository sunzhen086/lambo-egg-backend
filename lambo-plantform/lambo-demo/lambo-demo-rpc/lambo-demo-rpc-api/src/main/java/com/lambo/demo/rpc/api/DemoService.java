package com.lambo.demo.rpc.api;

import java.util.List;

/**
 * @ClassName DemoService
 * @Descirption TODO
 * @author sunzhen
 * @Date 2018/6/27 14:58
 **/
public interface DemoService {
    List<String> getPermissions(Long id);
}
