/*
 * Created on 2004-4-22
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.lambo.schedule;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import com.lambo.schedule.util.ResourceBundle;


/**
 * @author zxc
 * @date
 */
public class CronTaskImpl extends CronTask {
	static Logger Log = Logger.getLogger(CronTaskImpl.class.getName());
	private static Runnable runnable = null;
	final static String DEFAULT_METHOD = "main";
	final static String empty = "";

	/**
     * 获取ResourceBundle实例 国际化
     */
	private ResourceBundle resourceBundle = ResourceBundle
			.getBundle("resource-schedule");

	/**
	 * Runs this task. This method does the whole enchilada. This method decides
	 * wich method call in the given class
	 * 
	 * @throws Exception
	 */
	@Override
	public void runTask() throws Throwable {
		try {
			String className = getClassName();
			// Do class instantiation first (common to all cases of 'if' below)
			Class cl = CronTask.class.getClassLoader()
					.loadClass(getClassName());
			// Check if we have a Method
			String method_name = getMethodName();
			if (method_name==null||empty.equals(method_name)) {
				method_name = DEFAULT_METHOD;
			}
			Class[] argTypes = { String[].class };
			Object[] arg = { getExtraInfo() };

			// accessing the given method
			try {
				Method mMethod = cl.getMethod(method_name, argTypes);
				//调用任务类的静态方法
				mMethod.invoke(null, arg);
				//如果要调用非静态的方法则需要实例化对象
				//mMethod.invoke(cl.newInstance(),arg);
				if (Log.isDebugEnabled()) {
					Log.debug(resourceBundle
							.getString("CronTaskImpl.runTask_1")
							+ getTaskName()
							+ resourceBundle
									.getString("CronTaskImpl.runTask_2")
							+ method_name + "]");
				}
			} catch (NoSuchMethodException e) {
				//如果任务类没有待执行的方法，则假定任务类是一个实现了Runnable接口的类，即此任务类是多线程类
				//实例化线程然后启动线程
				if (Log.isDebugEnabled()) {
					Log.debug(resourceBundle
							.getString("CronTaskImpl.runTask_1")
							+ getTaskName()
							+ resourceBundle
									.getString("CronTaskImpl.runTask_3")
							+ method_name
							+ resourceBundle
									.getString("CronTaskImpl.runTask_4"));
				}
				try {
					// 任务类没有响应参数的构造函数
					Constructor con = cl.getConstructor(argTypes);
					runnable = (Runnable) con.newInstance(arg);
				} catch (NoSuchMethodException e2) {
					if (Log.isDebugEnabled()) {
						Log.debug(resourceBundle
								.getString("CronTaskImpl.runTask_1")
								+ getTaskName()
								+ resourceBundle
										.getString("CronTaskImpl.runTask_5"));
					}
					runnable = (Runnable) cl.newInstance();
				}
				runnable.run();
				if (Log.isDebugEnabled()) {
					Log.debug(resourceBundle
							.getString("CronTaskImpl.runTask_1")
							+ getTaskName()
							+ resourceBundle
									.getString("CronTaskImpl.runTask_6"));
				}
			}
			if (Log.isDebugEnabled()) {
				Log.debug(resourceBundle.getString("CronTaskImpl.runTask_1")
						+ getTaskName()
						+ resourceBundle.getString("CronTaskImpl.runTask_7")
						+ method_name
						+ resourceBundle.getString("CronTaskImpl.runTask_8"));
			}

		} catch (InvocationTargetException e) {
			Throwable throwable = e.getCause();
			Log.error(resourceBundle.getString("CronTaskImpl.runTask_1")
					+ getTaskName()
					+ resourceBundle.getString("CronTaskImpl.runTask_9"),e);
			if (throwable != null) {
				throw throwable;
			}
			throw e;
		} catch (Exception e) {
			Log.error(resourceBundle.getString("CronTaskImpl.runTask_1")
						+ getTaskName()
						+ resourceBundle.getString("CronTaskImpl.runTask_9"),e);
			throw e;
		} catch (Throwable t) {
			Log.error(resourceBundle.getString("CronTaskImpl.runTask_1")
						+ getTaskName()
						+ resourceBundle.getString("CronTaskImpl.runTask_9"),t);
			throw t;
		}
	}
}
