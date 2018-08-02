package com.lambo.mock.manage.controller;

import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseController;
import com.lambo.common.base.BaseResult;
import com.lambo.common.base.BaseResultConstant;
import com.lambo.common.utils.idgen.IdGenerate;
import com.lambo.mock.manage.model.MockStru;
import com.lambo.mock.manage.model.MockStruExample;
import com.lambo.mock.manage.service.api.MockStruService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(value = "mock目录管理", description = "mock目录管理")
@RequestMapping("/manage/mock/stru")
public class MockStruController extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(MockStruController.class);

    @Autowired
    MockStruService mockStruService;

    @ApiOperation(value = "新增mock目录")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    @LogAround("新增mock目录")
    public Object insert(@RequestParam(required = true, value = "struName") String struName,
                         @RequestParam(required = true, value = "struUrl") String struUrl,
                         @RequestParam(required = true, value = "struType") String struType,
                         @RequestParam(required = false, value = "parentId") String parentId,
                         @RequestParam(required = false, value = "isUse") String isUse,
                         @RequestParam(required = false, value = "orderSeq") Integer orderSeq) {

        MockStru mockStru = new MockStru();

        String uuid = IdGenerate.nextId();

        //mock_STRU
        mockStru.setStruId(uuid);
        mockStru.setStruName(struName);
        mockStru.setStruUrl(struUrl);
        mockStru.setStruType(struType);
        mockStru.setParentId(parentId);
        mockStru.setIsUse(isUse);
        mockStru.setOrderSeq(orderSeq);

        if(("folder").equals(struType)){
            mockStru.setMockId("");
        }else{
            mockStru.setMockId(uuid);
        }

        int count = mockStruService.insert(mockStru);

        return new BaseResult(BaseResultConstant.SUCCESS,mockStru);
    }

    @ApiOperation(value = "更新mock目录")
    @RequestMapping(value = "/update/{struId}",method = RequestMethod.POST)
    @ResponseBody
    @LogAround("更新mock目录")
    public Object update(@PathVariable("struId") String struId,
                         @RequestParam(required = true, value = "struName") String struName,
                         @RequestParam(required = true, value = "struUrl") String struUrl,
                         @RequestParam(required = true, value = "struType") String struType,
                         @RequestParam(required = false, value = "mockId") String mockId,
                         @RequestParam(required = false, value = "parentId") String parentId,
                         @RequestParam(required = false, value = "isUse") String isUse,
                         @RequestParam(required = false, value = "orderSeq") Integer orderSeq) {

        MockStru mockStru = new MockStru();

        //mock_STRU
        mockStru.setStruId(struId);
        mockStru.setStruName(struName);
        mockStru.setStruUrl(struUrl);
        mockStru.setStruType(struType);
        mockStru.setMockId(mockId);
        mockStru.setParentId(parentId);
        mockStru.setIsUse(isUse);
        mockStru.setOrderSeq(orderSeq);

        int count = mockStruService.updateByPrimaryKey(mockStru);

        return new BaseResult(BaseResultConstant.SUCCESS,mockStru);
    }

    @ApiOperation(value = "删除mock目录")
    @RequestMapping(value = "/delete/{struId}",method = RequestMethod.POST)
    @ResponseBody
    @LogAround("删除mock目录")
    public Object delete(@PathVariable("struId") String struId){

        MockStru mockStru = new MockStru();
        mockStru.setIsUse("0");

        MockStruExample mockStruExample =  new MockStruExample();
        mockStruExample.createCriteria().andStruIdEqualTo(struId);

        int count = mockStruService.updateByExampleSelective(mockStru,mockStruExample);

        return new BaseResult(BaseResultConstant.SUCCESS,mockStru);
    }

    @ApiOperation(value = "查询mock目录")
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("查询mock目录")
    public Object query(
            @RequestParam(required = false, value = "struId") String struId) {

        MockStruExample mockStruExample =  new MockStruExample();
        if(null!=struId && !("").endsWith(struId)) {
            mockStruExample.createCriteria().andStruIdEqualTo(struId);
        }

        MockStru mockStru = mockStruService.selectFirstByExample(mockStruExample);

        return new BaseResult(BaseResultConstant.SUCCESS,mockStru);
    }

    @ApiOperation(value = "查询mock子目录")
    @RequestMapping(value = "/queryChildren",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("查询mock子目录")
    public Object queryChildren(
            @RequestParam(required = false, value = "parentId") String parentId,
            @RequestParam(required = false, value = "hasDevStatus") String hasDevStatus) {

        MockStruExample mockStruExample =  new MockStruExample();
        MockStruExample.Criteria criteria = mockStruExample.createCriteria();

        if(null!=parentId && !("").endsWith(parentId)) {
            criteria.andParentIdEqualTo(parentId);
        }
        criteria.andIsUseEqualTo("1");

        mockStruExample.setOrderByClause("ORDER_SEQ ASC");

        List<MockStru> list = mockStruService.selectByExample(mockStruExample);

        if(null!=hasDevStatus && "1".endsWith(hasDevStatus)){
            if(null!=list && list.size()>0){
                for(int i=0;i<list.size();i++){
                    MockStru mockStru = list.get(i);
                    String mockType = mockStru.getStruType();

                    if("folder".equals(mockType)){
                        mockStru.setDevStatus(mockStruService.selectDevStatusByMockUrl(mockStru.getStruUrl()));
                    }else{
                        mockStru.setDevStatus(mockStruService.selectDevStatusByMockId(mockStru.getMockId()));
                    }
                }
            }
        }

        return new BaseResult(BaseResultConstant.SUCCESS,list);
    }

    @ApiOperation(value = "统计mock文件目录的开发状态数")
    @RequestMapping(value = "/getStruDevStatusByUrl",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("统计mock文件目录的开发状态数")
    public String getStruDevStatusByUrl(
            @RequestParam(required = true, value = "mockUrl") String mockUrl) {

        return mockStruService.selectDevStatusByMockUrl(mockUrl);
    }

}
