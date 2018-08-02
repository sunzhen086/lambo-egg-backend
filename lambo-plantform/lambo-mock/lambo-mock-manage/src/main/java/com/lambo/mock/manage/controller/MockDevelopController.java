package com.lambo.mock.manage.controller;

import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseController;
import com.lambo.common.base.BaseResult;
import com.lambo.common.base.BaseResultConstant;
import com.lambo.mock.manage.model.MockDevelop;
import com.lambo.mock.manage.model.MockDevelopExample;
import com.lambo.mock.manage.service.api.MockDevelopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Api(value = "mock开发管理", description = "mock开发管理")
@RequestMapping("/manage/mock/develop")
public class MockDevelopController extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(MockDevelopController.class);

    @Autowired
    MockDevelopService mockDevelopService;

    @ApiOperation(value = "新增mock开发信息")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    @LogAround("新增mock开发信息")
    public Object insert(@RequestParam(required = true, value = "mockId") String mockId,
                         @RequestParam(required = false, value = "status") String status,
                         @RequestParam(required = false, value = "designer") String designer,
                         @RequestParam(required = false, value = "developer") String developer,
                         @RequestParam(required = false, value = "note") String note) {

        MockDevelop mockDevelop = new MockDevelop();

        //MOCK_DEVELOP
        mockDevelop.setMockId(mockId);
        mockDevelop.setStatus(status);
        mockDevelop.setDesigner(designer);
        mockDevelop.setDeveloper(developer);
        mockDevelop.setNote(note);

        int count = mockDevelopService.insert(mockDevelop);

        if(count == 0){
            return new BaseResult(BaseResultConstant.FAILED,"insert失败");
        }else{
            return new BaseResult(BaseResultConstant.SUCCESS,mockDevelop);
        }
    }

    @ApiOperation(value = "更新mock开发信息")
    @RequestMapping(value = "/update/{mockId}",method = RequestMethod.POST)
    @ResponseBody
    @LogAround("更新mock开发信息")
    public Object update(@PathVariable("mockId") String mockId,
                         @RequestParam(required = false, value = "status") String status,
                         @RequestParam(required = false, value = "designer") String designer,
                         @RequestParam(required = false, value = "developer") String developer,
                         @RequestParam(required = false, value = "note") String note) {

        MockDevelop mockDevelop = new MockDevelop();

        //MOCK_DEVELOP
        mockDevelop.setMockId(mockId);
        mockDevelop.setStatus(status);
        mockDevelop.setDesigner(designer);
        mockDevelop.setDeveloper(developer);
        mockDevelop.setNote(note);

        int count = mockDevelopService.updateByPrimaryKeyWithBLOBs(mockDevelop);

        if(count == 0){
            return new BaseResult(BaseResultConstant.FAILED,"update失败");
        }else{
            return new BaseResult(BaseResultConstant.SUCCESS,mockDevelop);
        }
    }

    @ApiOperation(value = "查询mock开发信息")
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("查询mock开发信息")
    public Object query(
            @RequestParam(required = false, value = "mockId") String mockId) {

        MockDevelopExample mockDevelopExample =  new MockDevelopExample();
        if(null!=mockId && !("").endsWith(mockId)) {
            mockDevelopExample.createCriteria().andMockIdEqualTo(mockId);
        }

        MockDevelop mockDevelop = mockDevelopService.selectFirstByExampleWithBLOBs(mockDevelopExample);

        return new BaseResult(BaseResultConstant.SUCCESS,mockDevelop);
    }

}
