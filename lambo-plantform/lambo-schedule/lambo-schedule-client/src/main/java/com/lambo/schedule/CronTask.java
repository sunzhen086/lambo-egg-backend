/**
 *  This file is part of the jcrontab package
 *  Copyright (C) 2001-2003 Israel Olalla
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free
 *  Software Foundation, Inc., 59 Temple Place, Suite 330, Boston,
 *  MA 02111-1307, USA
 *
 *  For questions, suggestions:
 *
 *  iolalla@yahoo.com
 *
 */

package com.lambo.schedule;

import java.util.*;

import org.apache.log4j.Logger;
import com.lambo.schedule.data.CrontabEntryBean;
import com.lambo.schedule.data.CrontabEntryDAO;
import com.lambo.schedule.jdbc.Task;
import com.lambo.schedule.jdbc.TaskDao;
import com.lambo.schedule.jdbc.TaskResultBean;
import com.lambo.schedule.jdbc.TaskResultDao;
import com.lambo.schedule.jdbc.UUIDHexGenerator;
import com.lambo.schedule.util.DateFormat;
import com.lambo.schedule.util.ResourceBundle;



/** 
 * Implements a runnable task that can be scheduled and executed by the
 * Crontab.
 * If a new kind of task is desired, this class should be extended and the
 * abstract method runTask should be overwritten.
 * @author $Author: fujw $
 * @version $Revision: 20709 $
 */
public abstract class CronTask extends Thread {
	static Logger Log = Logger.getLogger(CronTask.class.getName());

	//private Crontab crontab;
	private int identifier;

	private String[] strExtraInfo;

	private String strClassName;

	private String strMethodName;
	
	private int taskId;

	private String taskName;
	
	/**
     * 获取ResourceBundle实例 国际化
     */
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("resource-schedule");

	/**
	 * Constructor of a task.
	 * We always call the constructor with no arguments, because the tasks
	 * are created dinamically (by Class.forName).
	 * You should call the method setParams inmediatly after creating a new task
	 */
	public CronTask() {
	}

	/**
	 * Selects the initial parameters for the task. As a task is created loaded
	 * dinamically from the class name, the default constructor called is
	 * the one with no arguments. You should call this method after creating
	 * the new instance of the task.
	 * @cront The Crontab that creates and executes this task. It
	 * should be used to have access to other tasks, in order to wait for them
	 * or other tasks operations.
	 * @param iTaskID Identifier of the task
	 * @param strExtraInfo Extra information given to the task when created
	 */
	public final void setParams(int iTaskID, int taskId, String taskName, String strClassName, String strMethodName,
			String[] strExtraInfo) {
		identifier = iTaskID;
		this.strExtraInfo = strExtraInfo;
		this.strMethodName = strMethodName;
		this.strClassName = strClassName;
		this.taskName = taskName;
		this.taskId = taskId;
	}

	protected final String getClassName() {
		return strClassName;
	}

	protected final String getTaskName() {
		return taskName;
	}

	/**
	 * Returns the aditional parameters given to the task in construction
	 * @return The aditional parameters given to the task in construction
	 */
	protected final String[] getExtraInfo() {
		return strExtraInfo;
	}

	/**
	 * Returns the Method Name given to the task in construction
	 * @return The aditional parameters given to the task in construction
	 */
	protected final String getMethodName() {
		return strMethodName;
	}

	/**
	 * Runs this task. This method does the whole enchilada.
	 * This method decides wich method call in  the given class
	 * @throws Throwable 
	 */
	public abstract void runTask() throws Throwable;

	/**
	 * Runs this task
	 */
	@Override
	public String toString() {
		StringBuffer task = new StringBuffer();
		task.append("Task[" + getTaskName() + "] ");
		task.append(" Content: ");
		task.append(getClassName());
		task.append("@");
		task.append(getMethodName());
		if (getExtraInfo() != null) {
			for (int i = 0; i < getExtraInfo().length; i++) {
				task.append(" ");
				task.append(getExtraInfo()[i]);
			}
		}
		return task.toString();
	}
   @Override
	public final void run() {
		Calendar start_d = null;
		TaskResultBean taskResultBean=null;
		boolean isDB=false;
		try {
			CrontabEntryDAO dao=CrontabEntryDAO.getInstance();
			CrontabEntryBean entry = dao.find(taskName);
			isDB="DB".equals(dao.getDataSourceType());
			if (entry != null && entry.isStart()) {
				start_d = Calendar.getInstance(); 
				long s = System.currentTimeMillis();
				if(isDB){
					taskResultBean=new TaskResultBean();
					beforeRunTask(taskResultBean,start_d);
				}
				runTask();
				long e = System.currentTimeMillis();
				if(isDB){
					runTaskSuccess(taskResultBean);
				}
				if (Log.isInfoEnabled()) {
					StringBuffer sb = new StringBuffer();
					sb.append(resourceBundle.getString("CronTask.run_1")).append(taskName).append(resourceBundle.getString("CronTask.run_2"));//"定时任务{"  "}执行时间：["
					sb.append(start_d.get(Calendar.YEAR)).append("-").append(start_d.get(Calendar.MONTH) + 1).append(
							"-").append(start_d.get(Calendar.DAY_OF_MONTH));
					sb.append(" ");
					sb.append(start_d.get(Calendar.HOUR_OF_DAY)).append(":").append(start_d.get(Calendar.MINUTE));
					sb.append(resourceBundle.getString("CronTask.run_3"));//"]，任务执行成功，用时["
					sb.append((e - s) / 1000.0);
					sb.append(resourceBundle.getString("CronTask.run_4"));//"]秒。"
					Log.info(sb);
				}

				// Deletes the task from the crontab array
				Crontab.getInstance().deleteTask(identifier);
				//This line sends the email to the config
				if (PropertyMgr.getMailTo() != null) {
					SendMail sndm = new SendMail();
					sndm.send(this.toString());
					//tempFile.delete();
				}
			} else {
				if (Log.isInfoEnabled()) {
					Log.info(resourceBundle.getString("CronTask.run_1") + taskName + resourceBundle.getString("CronTask.run_5"));//"The timing task{"  "} was already stopped or removed,can not run."
				}
			}
		} catch (Throwable e) {
			if(isDB){
				runTaskFail(taskResultBean,e);
			}
			Log.error(e.toString(), e);
			if (Log.isInfoEnabled()) {
				StringBuffer sb = new StringBuffer();
				sb.append(resourceBundle.getString("CronTask.run_1")).append(taskName).append(resourceBundle.getString("CronTask.run_2"));//"定时任务{"  "}执行时间：["
				sb.append(start_d.get(Calendar.YEAR)).append("-").append(start_d.get(Calendar.MONTH) + 1).append("-")
						.append(start_d.get(Calendar.DAY_OF_MONTH));
				sb.append(" ");
				sb.append(start_d.get(Calendar.HOUR_OF_DAY)).append(":").append(start_d.get(Calendar.MINUTE));
				sb.append(resourceBundle.getString("CronTask.run_6"));//"]，任务执行失败，请查看错误日志！"
				sb.append(e);
				Log.info(sb);
			}
		}
	}
	private String dateFormat(Calendar date){
		return DateFormat.format(date);
	}
	private void beforeRunTask(TaskResultBean taskResultBean, Calendar start_d){
		taskResultBean.setTaskResultId(UUIDHexGenerator.getInstance().generate());
		taskResultBean.setTaskId(taskId);
		taskResultBean.setTaskName(taskName);
		taskResultBean.setStartTime(dateFormat(start_d));
		taskResultBean.setResult(Task.TASK_RUNNING);
		TaskResultDao.getInstance().insert(taskResultBean);
		TaskDao.getInstance().changeStateById(Task.CURRENT_STATE_RUNNING, taskId);
	}
	private void runTaskSuccess(TaskResultBean taskResultBean){
		Calendar end_d = null;
		end_d = Calendar.getInstance();				
		taskResultBean.setEndTime(dateFormat(end_d));
		taskResultBean.setResult(Task.TASK_RUN_SUCCESS);
		TaskResultDao.getInstance().updateById(taskResultBean);
		TaskDao.getInstance().changeRunToStartById(taskId);
	}
	private void runTaskFail(TaskResultBean taskResultBean, Throwable e){
		if(taskResultBean==null){return;}
		Calendar end_d = Calendar.getInstance();
		taskResultBean.setEndTime(dateFormat(end_d));
		taskResultBean.setResult(Task.TASK_RUN_FAIL);
		taskResultBean.setErrorMessage(e.toString());
		TaskResultDao.getInstance().updateById(taskResultBean);
		TaskDao.getInstance().changeRunToStartById(taskId);
	}
}
