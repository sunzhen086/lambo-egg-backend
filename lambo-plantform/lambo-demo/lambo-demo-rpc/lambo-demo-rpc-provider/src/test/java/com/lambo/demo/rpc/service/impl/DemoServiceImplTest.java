package com.lambo.demo.rpc.service.impl;

import com.lambo.common.base.BaseResult;
import com.lambo.common.base.BaseResultConstant;
import com.lambo.demo.rpc.api.DemoService;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * DemoServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 */
/**
 * @ClassName BaseJunit4Test
 * @Descirption 测试rpc服务
 * @Author sunzhen
 * @Date 2018/4/25 15:25
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*:applicationContext.xml",
        "classpath*:applicationContext-jdbc.xml",
        "classpath*:applicationContext-listener.xml",
        "classpath*:springMVC-servlet.xml",
        "classpath*:applicationContext-dubbo-consumer.xml"
})
@WebAppConfiguration
public class DemoServiceImplTest {

    @Autowired
    DemoService permissionService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getPermissions(Long id)
     */
    @Test
    public void testGetPermissions() throws Exception {
        List<String> result = permissionService.getPermissions((long)111);
        System.out.println("****************************************");
        System.out.println(result);
        System.out.println("****************************************");
    }


} 
