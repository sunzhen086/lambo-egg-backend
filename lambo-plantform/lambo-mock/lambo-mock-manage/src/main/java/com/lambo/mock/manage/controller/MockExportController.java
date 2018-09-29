package com.lambo.mock.manage.controller;

import com.lambo.common.base.BaseController;
import com.lambo.common.utils.io.FileUtils;
import com.lambo.mock.manage.model.*;
import com.lambo.mock.manage.service.api.MockSettingParamsService;
import com.lambo.mock.manage.service.api.MockSettingService;
import com.lambo.mock.manage.service.api.MockStruService;
import com.lambo.oss.client.constant.OssConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 对象存储相关服务
 * Created by sunzhen on 2018/4/10.
 */
@Controller
@Api(value = "MOKE服务导出", description = "MOKE服务导出")
@RequestMapping("/manage/mock/export")
public class MockExportController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(MockExportController.class);

    @Autowired
    MockStruService mockStruService;

    @Autowired
    MockSettingService mockSettingService;

    @Autowired
    MockSettingParamsService mockSettingParamsService;

    @ApiOperation(value = "下载word" ,notes = "下载word")
    @RequestMapping(value = "/getWord", method = RequestMethod.GET)
    @ResponseBody
    public void get(HttpServletRequest request, HttpServletResponse response) {
        logger.info("下载文件开始。。。");

        String filePath = FileUtils.path(FileUtils.getWebappPath()
                + OssConstant.UPLOAD_TEMP_PATH)
                + "Mock服务.docx";

        XWPFDocument document = new XWPFDocument();

        generateDoc(document);

        //创建段落
//        XWPFParagraph gParagraph=document.createParagraph();
//        //设置文本的对齐方式
//        gParagraph.setAlignment(ParagraphAlignment.CENTER);
//        //创建文本对象
//        XWPFRun frun=gParagraph.createRun();
//        frun.setBold(true);//是否加粗
//        frun.setColor("1AA160");//设置颜色
//        frun.setFontSize(30);//设置字体大小
//        frun.setText("我正在看Word文档的生成");
//
//       //创建表格
//        //参数说明：1、行数2、列数
//        XWPFTable table=document.createTable(10,1);
//        //设置表格属性
//        CTTbl ttbl=table.getCTTbl();
//        CTTblPr tp=ttbl.addNewTblPr();
//        //设置表格宽度
//        CTTblWidth cw=tp.addNewTblW();
//        cw.setW(BigInteger.valueOf(8000));
//        cw.setType(STTblWidth.DXA);//设置为固定。默认为AUTO
//
//        XWPFTableRow tr1=table.getRow(0);
//        XWPFTableCell tc1=tr1.getCell(0);
//        tc1.setColor("4D8BF4");
//        tc1.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);//竖直对齐方式
//        //表格的列中嵌套段落
//        XWPFParagraph gp=tc1.addParagraph();
//        XWPFRun fr1=gp.createRun();
//        fr1.setBold(true);
//        fr1.setDoubleStrikethrough(true);
//        fr1.setFontSize(25);
//        fr1.setColor("D14825");
//        fr1.setText("名次");
////      CTTc ct1=tc1.getCTTc();
////      CTTcPr cp1=ct1.addNewTcPr();
////      cp1.addNewVMerge().setVal(STMerge.CONTINUE);//合并表格的列
//        for(int i=1;i<table.getRows().size();i++){
//            XWPFTableCell c=table.getRow(i).getCell(0);
//            c.setText("第"+i+"名");
//        }
//
        File newFile = new File(filePath);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(newFile);
            document.write(fos);
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info("下载文件结束。。。");
        FileUtils.downFile(newFile, request, response);

    }

    private void generateDoc(XWPFDocument document){

        //标题
        XWPFParagraph gParagraph=document.createParagraph();
        //设置文本的对齐方式
        gParagraph.setAlignment(ParagraphAlignment.CENTER);
        //创建文本对象
        XWPFRun frun=gParagraph.createRun();
        frun.setBold(true);//是否加粗
        frun.setFontSize(30);//设置字体大小
        frun.setText("Mock服务");

        //获取数据
        Map mockData = getMockData();
        List<MockStru> mockStruList = (List)mockData.get("mockStruList");
        List<MockSetting> mockSettingList = (List)mockData.get("mockSettingList");
        List<MockSettingParams> mockSettingParamsList = (List)mockData.get("mockSettingParamsList");

        if(null!=mockStruList && mockStruList.size()>0){
            setParagraph(document,"","0",1,mockStruList,mockSettingList,mockSettingParamsList);
        }
    }

    private void setParagraph(XWPFDocument document,String parentPath,String parentId,int level,List mockStruList,List mockSettingList,List mockSettingParamsList){
        if(null!=mockStruList && mockStruList.size()>0){
            int number = 1;
            for(int i=0;i<mockStruList.size();i++){
                MockStru mockStru = (MockStru)mockStruList.get(i);
                if(parentId.equals(mockStru.getParentId())){

                    StringBuffer path = new StringBuffer();
                    if("".equals(parentPath)){
                        path.append(number);
                    }else{
                        path.append(parentPath).append(".").append(number);
                    }

                    StringBuffer title = new StringBuffer();
                    title.append(path).append(" ").append(mockStru.getStruName());

                    int fontSize = 24-level*2;
                    fontSize = fontSize<14?14:fontSize;

                    XWPFParagraph gParagraph=document.createParagraph();
                    //创建文本对象
                    XWPFRun frun=gParagraph.createRun();
                    frun.setFontSize(fontSize);//设置字体大小
                    frun.setTextPosition(20);// 设置上下两行之间的间距
                    frun.setText(title.toString());

                    if(("service").equals(mockStru.getStruType())){
                        setMockSetting(document,mockStru.getMockId(),mockSettingList,mockSettingParamsList);
                    }

                    mockStruList.remove(i);
                    i--;

                    setParagraph(document,path.toString(),mockStru.getStruId(),level+1,mockStruList,mockSettingList,mockSettingParamsList);

                    number++;

                }
            }
        }
    }

    private void setMockSetting(XWPFDocument document,String mockId,List<MockSetting> mockSettingList,List<MockSettingParams> mockSettingParamsList){
        MockSetting mockSetting = null;
        if(null!=mockSettingList){
            for(int i=0;i<mockSettingList.size();i++){
                MockSetting setting = mockSettingList.get(i);
                if(setting.getMockId().equals(mockId)){
                    mockSetting = setting;
                    mockSettingList.remove(i);
                    break;
                }
            }
        }

        List paramsList = new ArrayList();
        if(null!=mockSettingParamsList){
            for(int i=0;i<mockSettingParamsList.size();i++){
                MockSettingParams params = mockSettingParamsList.get(i);
                if(params.getMockId().equals(mockId)){
                    paramsList.add(params);
                    mockSettingParamsList.remove(i);
                    i--;
                }
            }
        }


        if(null!=mockSetting){

            int rowsNum = 5;
            if(null!=paramsList && paramsList.size()>0){
                rowsNum = rowsNum + 1 + paramsList.size();
            }

            //创建表格
            //参数说明：1、行数2、列数
            XWPFTable table=document.createTable(rowsNum,5);

            //设置表格属性
            CTTbl ttbl=table.getCTTbl();
            CTTblPr tp=ttbl.addNewTblPr();

            //设置表格宽度
            CTTblWidth cw=tp.addNewTblW();
            cw.setW(BigInteger.valueOf(8000));
            cw.setType(STTblWidth.DXA);//设置为固定。默认为AUTO

            //表格行高
            List<XWPFTableRow> rows = table.getRows();
            for(XWPFTableRow row:rows){
                CTTrPr trPr = row.getCtRow().addNewTrPr();
                CTHeight ht = trPr.addNewTrHeight();
                ht.setVal(BigInteger.valueOf(500));
            }

            int curRow = 0;

            setTableRowX(table,curRow,"URL",mockSetting.getMockUrl());
            curRow++;

            setTableRowX(table,curRow,"服务类型","HTTP   "+mockSetting.getMockType());
            curRow++;

            setTableRowX(table,curRow,"功能描述",mockSetting.getNote());
            curRow++;

            if(null!=paramsList && paramsList.size()>0){
                setTableRowY(table,curRow,"输入参数",paramsList);
                curRow = curRow + paramsList.size()+1;
            }

            setTableRowX(table,curRow,"输出参数",mockSetting.getParamsDes());
            curRow++;

            setTableRowX(table,curRow,"输出样例",mockSetting.getMockData());

        }
    }

    private void setTableRowX(XWPFTable table,int row,String cellName,String cellValue){

        XWPFTableCell cell0=table.getRow(row).getCell(0);

        //单元格宽度
        CTTcPr tcpr = cell0.getCTTc().addNewTcPr();
        CTTblWidth cellw = tcpr.addNewTcW();
        cellw.setType(STTblWidth.DXA);
        cellw.setW(BigInteger.valueOf(1600));

        cell0.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);//垂直居中

        cell0.setText(cellName);

        int fromCell = 1;
        int toCell = 4;
        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {
            XWPFTableCell cell1 = table.getRow(row).getCell(cellIndex);
            if ( cellIndex == fromCell ) {
                cell1.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
            } else {
                cell1.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
            }

            cell1.setText(cellValue);
        }

    }

    private void setTableRowY(XWPFTable table,int row,String cellName,List<MockSettingParams> list){

        int fromRow = row;
        int toRow = row + list.size();
        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
            XWPFTableCell cell0 = table.getRow(rowIndex).getCell(0);
            if ( rowIndex == fromRow ) {
                cell0.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
            } else {
                cell0.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);
            }

            //单元格宽度
            CTTcPr tcpr = cell0.getCTTc().addNewTcPr();
            CTTblWidth cellw = tcpr.addNewTcW();
            cellw.setType(STTblWidth.DXA);
            cellw.setW(BigInteger.valueOf(1600));

            cell0.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);//垂直居中

            cell0.setText(cellName);
        }


        XWPFTableCell cell1=table.getRow(row).getCell(1);
        cell1.setText("参数名称");

        XWPFTableCell cell2=table.getRow(row).getCell(2);
        cell2.setText("参数类型");

        XWPFTableCell cell3=table.getRow(row).getCell(3);
        cell3.setText("是否必须");

        XWPFTableCell cell4=table.getRow(row).getCell(4);
        cell4.setText("参数说明");

        for(int i=0;i<list.size();i++){
            MockSettingParams params = list.get(i);

            row++;

            XWPFTableCell cell01=table.getRow(row).getCell(1);
            cell01.setText(params.getParamKey());

            XWPFTableCell cell02=table.getRow(row).getCell(2);
            cell02.setText(params.getParamType());


            XWPFTableCell cell03=table.getRow(row).getCell(3);
            cell03.setText(("1").equals(params.getNecessary())?"是":"否");

            XWPFTableCell cell04=table.getRow(row).getCell(4);
            cell04.setText(params.getNote());
        }

    }


    private Map getMockData(){
        Map data = new HashMap();

        MockStruExample mockStruExample =  new MockStruExample();
        MockStruExample.Criteria criteria = mockStruExample.createCriteria();
        criteria.andIsUseEqualTo("1");
        mockStruExample.setOrderByClause("ORDER_SEQ ASC");
        List<MockStru> mockStruList = mockStruService.selectByExample(mockStruExample);


        MockSettingExample mockSettingExample =  new MockSettingExample();
        List<MockSetting> mockSettingList = mockSettingService.selectByExampleWithBLOBs(mockSettingExample);

        MockSettingParamsExample mockSettingParamsExample =  new MockSettingParamsExample();
        mockSettingParamsExample.setOrderByClause("ORDER_SEQ ASC");
        List<MockSettingParams> mockSettingParamsList = mockSettingParamsService.selectByExample(mockSettingParamsExample);

        data.put("mockStruList",mockStruList);
        data.put("mockSettingList",mockSettingList);
        data.put("mockSettingParamsList",mockSettingParamsList);

        return data;
    }


}
