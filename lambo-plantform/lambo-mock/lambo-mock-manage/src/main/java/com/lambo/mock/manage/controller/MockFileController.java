package com.lambo.mock.manage.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lambo.common.base.BaseController;
import com.lambo.common.utils.io.FileUtils;
import com.lambo.common.utils.mapper.JsonMapper;
import com.lambo.mock.manage.dao.api.MockSettingMapper;
import com.lambo.mock.manage.model.MockSetting;
import com.lambo.oss.client.constant.OssConstant;
import com.lambo.oss.client.constant.OssResult;
import com.lambo.oss.client.constant.OssResultConstant;
import com.lambo.oss.client.service.api.OssClientApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.util.ObjectUtils.isEmpty;

/**
 * 对象存储相关服务
 * Created by sunzhen on 2018/4/10.
 */
@Controller
@Api(value = "MOKE数据上传下载服务", description = "MOKE数据上传下载服务")
@RequestMapping("/manage/mock/file")
public class MockFileController extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(MockFileController.class);

    @Autowired
    OssClientApiService ossClientApiService;

    @Autowired
    MockSettingMapper mockSettingMapper;

    @ApiOperation(value = "保存文件" ,notes = "保存文件")
    @RequestMapping(value = "/put", method = RequestMethod.POST)
    @ResponseBody
    public Object put(@RequestParam("file") MultipartFile[] files) {
        logger.info("上传文件开始。。。");
        List dataList = new ArrayList();

        //判断file数组不能为空并且长度=1 单文件传输
        if (files != null && files.length > 0) {
            //单文件上传，故文件列表数为1
            MultipartFile file = files[0];
            //保存文件
            Map<String,Object> saveResultMap = ossClientApiService.saveFile(file);

            //解析excel文件
            String fileName = (String)saveResultMap.get("name");
            String filePath = FileUtils.path(FileUtils.getWebappPath() + OssConstant.UPLOAD_TEMP_PATH)+ fileName;

            File inputFile = new File(filePath);
            Workbook workbook = null;
            try {
                workbook = new XSSFWorkbook(inputFile);
                Sheet sheet = workbook.getSheetAt(0);

                int rowStart = sheet.getFirstRowNum();
                int rowEnd = sheet.getLastRowNum();

                //字段行
                Row fildRow = sheet.getRow(rowStart);
                int colStart = fildRow.getFirstCellNum();
                int colEnd = fildRow.getLastCellNum();
                int colNum = colEnd - colStart;

                String[] fildArr = new String[colNum];
                String[][] colType = new String[colNum][2];

                for(int i=0;i<colNum;i++){
                    String value = fildRow.getCell(i).getStringCellValue();
                    if(value.indexOf("|") != -1){
                        String[] valueArr = value.split("\\|");
                        fildArr[i] = valueArr[0].trim();
                        colType[i][0] = valueArr[1].trim();
                    }else{
                        fildArr[i] = value.trim();
                        colType[i][0] = "";
                    }
                }

                //数据行
                for(int i=rowStart+1;i<=rowEnd;i++){
                    Row dataRow = sheet.getRow(i);
                    Map dataMap = new LinkedHashMap();
                    for(int j=colStart;j<colEnd;j++){
                        Cell cell = dataRow.getCell(j);
                        if(!isEmpty(cell)){
                            if(null == colType[j][1]){
                                colType[j][1] = cell.getCellTypeEnum().toString();
                            }

                            if("STRING".equals(colType[j][1])){
                                String value = cell.getStringCellValue();
                                if(null != value){
                                    dataMap.put(fildArr[j],value);
                                }
                            }else if("BOOLEAN".equals(colType[j][1])){
                                Boolean value = cell.getBooleanCellValue();
                                if(null != value){
                                    dataMap.put(fildArr[j],value);
                                }
                            }else{
                                Double doubleVal = cell.getNumericCellValue();
                                long longVal = Math.round(cell.getNumericCellValue());
                                Object inputValue = null;// 单元格值
                                if(Double.parseDouble(longVal + ".0") == doubleVal){
                                    inputValue = longVal;
                                }else{
                                    inputValue = doubleVal;
                                }
                                if(null != inputValue){
                                    if(("").equals(colType[j][0]) ||  (colType[j][0]).equals(colType[j][1])){
                                        dataMap.put(fildArr[j],inputValue);
                                    }else if(("STRING").equals(colType[j][0])){
                                        dataMap.put(fildArr[j],inputValue.toString());
                                    }
                                }
                            }

                        }
                    }
                    dataList.add(dataMap);
                }


            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }finally{
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //删除上传文件
            logger.info("删除文件开始。。。");
            try{
                if (inputFile.delete()){
                    logger.info("删除文件成功。。。");
                }else{
                    logger.info("删除文件失败。。。");
                }
            }catch(Exception e){
                logger.info("删除文件异常e",e);
            }

            logger.info("删除文件结束。。。");
        }else{
            return new OssResult(OssResultConstant.FAILED,"文件上传出错");
        }

        logger.info("上传文件结束。。。");

        Map returnMap = new LinkedHashMap();
        returnMap.put("code","000");
        returnMap.put("msg","success");

        if(dataList.size()==1){
            returnMap.put("data",dataList.get(0));
        }else{
            returnMap.put("data",dataList);
        }




        JsonMapper jsonMapper = new JsonMapper();
        String returnJson = jsonMapper.toJsonString(returnMap);

        return new OssResult(OssResultConstant.SUCCESS, returnJson);

    }

    @ApiOperation(value = "下载文件" ,notes = "下载文件")
    @RequestMapping(value = "/get/{restId:.+}", method = RequestMethod.GET)
    @ResponseBody
    public void get(@PathVariable("restId") String restId,
                    HttpServletRequest request, HttpServletResponse response) {
        logger.info("下载文件开始。。。");

        //根据restId获取rest数据
        MockSetting  mockSetting = mockSettingMapper.selectByPrimaryKey(restId);
        String restName = mockSetting.getMockName();
        String mockData = mockSetting.getMockData();

        String filePath = FileUtils.path(FileUtils.getWebappPath()
                + OssConstant.UPLOAD_TEMP_PATH)
                + restName + ".xlsx";

        Workbook workbook = writeExcel(mockData);

        File file = new File(filePath);
        file.deleteOnExit();
        FileOutputStream tempout = null;
        try {
            tempout = new FileOutputStream(file);
            workbook.write(tempout);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            tempout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info("下载文件结束。。。");
        FileUtils.downFile(file, request, response);

    }

    private Workbook writeExcel(String mockData) {
        logger.info("生成EXCEL开始。。。");

        // 创建Excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        if(null!=mockData && !"".endsWith(mockData)){
            try{

                JSONObject obj = JSONObject.parseObject(mockData);
                JSONArray data = obj.getJSONArray("data");

                if(data.size()>0){

                    //按顺序获取字段
                    Row row = sheet.createRow(0);
                    mockData = mockData.substring(mockData.indexOf("data"));
                    String rowStr = mockData.substring(mockData.indexOf("{")+1,mockData.indexOf("}"));
                    String[] rowArr = rowStr.split(",");
                    int colNum = rowArr.length;
                    String[][] field = new String[colNum][2];
                    for(int i=0;i<colNum;i++){
                        String[] arr = rowArr[i].split(":");
                        field[i][0] = arr[0].replaceAll("\"", "").replaceAll("\'", "");
                        if(arr[1].indexOf("\"")!=-1 || arr[1].indexOf("\'")!=-1){
                            field[i][1] = "String";
                        }else{
                            if(isNumeric(arr[1])){
                                field[i][1] = "BigDecimal";
                            }else{
                                field[i][1] = "String";
                            }
                        }

                        //字段名称
                        Cell  cell = row.createCell(i);
                        cell.setCellValue(field[i][0]);
                    }


                    //数据行
                    for (int n= 0; n< data.size(); n++) {
                        Row row_value = sheet.createRow(n + 1);
                        JSONObject dataRow = data.getJSONObject(n);
                        for (int i = 0; i < colNum; i++) {
                            Cell cell_v = row_value.createCell(i);

                            // 在单元格中输入一些内容
                            if("BigDecimal".equals(field[i][1])){
                                cell_v.setCellType(CellType.NUMERIC);
                                cell_v.setCellValue(dataRow.getBigDecimal(field[i][0]).doubleValue());
                            }else{
                                cell_v.setCellValue(dataRow.getString(field[i][0]));
                            }

                        }
                    }
                }
            }catch(Exception e){
                logger.info("生成EXCEL失败",e);
            }

        }

        logger.info("生成EXCEL结束。。。");
        return workbook;
    }

    /**
     * 利用正则表达式判断字符串是否是数字
     * @param str
     * @return
     */
    private boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

}
