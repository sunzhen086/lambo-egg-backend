package com.lambo.schedule.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.lambo.schedule.PropertyMgr;
import com.lambo.schedule.data.FileLoaderUtils;


public class Test{
	public static void main(String[] args){
		Properties props=loadProperties();
		PropertyMgr.initProperty(props);
		for(int i=0;i<2;i++){
			Test1 test1=new Test1();
			test1.start();
		}
	}
	private static Properties loadProperties(){
		String props = "schedule.properties";
		Properties propObj = new Properties();
		try {
			//�����԰��ļ�����Ϊ���·��
			InputStream input = new FileInputStream(props);
			propObj.load(input);
		} catch (IOException e) {
			try {
				//�������쳣��ʹ����·�����������ļ�
				InputStream input = FileLoaderUtils.getResourceAsStream(props);
				if( input == null ){
					throw new IOException("File '" + props + "' not found");
				}
				propObj.load(input);
			} catch (IOException ioe) {
				//ioe.printStackTrace();
				return null;
			}
		}
		return propObj;
	}
}
