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

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import com.lambo.schedule.data.FileLoaderUtils;
import org.apache.log4j.Logger;

/** 
 * Manages the creation and execution of all the scheduled tasks 
 * of jcrontab. This class is the core of the jcrontab
 * @author $Author: jiadx $
 * @version $Revision: 2880 $
 */

public class Crontab {
	static Logger Log = Logger.getLogger(Crontab.class.getName());

	private String version = "2.0";
	private HashMap tasks;
	private HashMap loadedClasses;
	private int iNextTaskID;
	private int iTimeTableGenerationFrec = 3;
	/** The Cron that controls the execution of the tasks */
	private Cron cron;
	private boolean stoping = false;
	private boolean daemon = true;

	private boolean isInternalConfig = true;
	private Class task_class = null;

	private Class view_class = null;

	/** The only instance of this cache */
	private static Crontab singleton = null;

	/**
	 * Crontab constructor
	 * Change the default constructor to public if you need 
	 * more than an instance running on the system
	 */
	private Crontab() {
		tasks = new HashMap();
		loadedClasses = new HashMap();
		iNextTaskID = 1;
	}
	/**
	 *  Returns the only instance of this class
	 *  we've choosen a singleton pattern to avoid launch different Crontab
	 *  If you need diferent crontab classes to be launched only should 
	 *  Change the private constructor to public.
	 *  @return singleton the only instance of this class
	 */
	public static synchronized Crontab getInstance() {
		if (singleton == null) {
			singleton = new Crontab();
		}
		return singleton;
	}

	/**
	 * Used by the LoadCrontabServlet to start Crontab with the configuration 
	 * passed in a Properties object.
	 *
	 * @param props a <code>Properties</code> object
	 * @ iTimeTableGenerationFrec Frecuency of regeneration of the events
	 * table
	 * @throws Exception
	 */
	public void init(Properties props) throws Exception {
		if (props != null) {
			PropertyMgr.initProperty(props);
		}
		//加载jdbc配置文件
		String propj = "/jdbc.properties";
		Properties propObj = new Properties();

		try {
			InputStream input = FileLoaderUtils.getResourceAsStream(propj);
			if (input == null) {
				throw new IOException("File '" + propj + "' not found");
			}
			propObj.load(input);
		} catch (IOException ioe) {
			Log.error(ioe.toString(), ioe);
		}
		if (propObj != null) {
			PropertyJDBC.initProperty(propObj);
		}
		String refreshFrequency = PropertyMgr.getRefresh();
		if (refreshFrequency != null) {
			this.iTimeTableGenerationFrec = Integer.parseInt(refreshFrequency);
		}
		task_class = Class.forName(PropertyMgr.getTaskRunner());
		// Creates the thread Cron, wich generates the engine events         
		cron = new Cron(this, iTimeTableGenerationFrec);
		cron.setName("Cron");
		cron.setDaemon(daemon);
		cron.start();
		stoping = false;
	}
	public boolean isStarted() {
		return !stoping;
	}
	public void restart() throws Exception {
		uninit();
		init(null);
	}
	/** 
	 * UnInitializes the Crontab. Calls to the method stopInTheNextMinute() 
	 * of the Cron.
	 * @ iSecondsToWait Number of seconds to wait for the tasks to end
	 * their process before returning from this method
	 */
	public void uninit() {
		if (stoping)
		{return;}
		stoping = true;
		cron.stopInTheNextMinute();
		cron.stop();
	}
	/** 
	 * UnInitializes the crontab. Calls to the method join() of each of
	 * the tasks running.
	 * @param iSecondsToWait Number of seconds to wait for the tasks to end
	 * their process before returning from this method
	 */
	public void uninit(int iSecondsToWait) {
		if (stoping)
		{return;}
		try {
			// Updates uninitializing flag
			stoping = true;
			cron.stopInTheNextMinute();
			CronTask[] tasks = getAllTasks();

			for (int i = tasks.length - 1; i >= 0; i--) {
				tasks[i].join(iSecondsToWait);
			}

		} catch (InterruptedException e) {
			Log.error(e.toString(), e);
		}
	}
	/**
	 * This method sets the Cron to daemon or not
	 *	@param  daemon
	 *  @throws Exception
	 */
	public void setDaemon(boolean daemon) {
		this.daemon = daemon;
	}

	/**
	 *	This method Stores in the properties File the given property and all the
	 *  "live" properties
	 *	@param property
	 *  @param value
	 */
	public void storeProperty(String property, String value, String strFileName) {
		PropertyMgr.storeProperty(property, value, strFileName);
	}
	/**
	 * Creates and runs a new task
	 * @param strClassName Name of the task
	 * @param strMethodName Name of the method that will be called
	 * @param strExtraInfo Extra Information given to the task
	 * @return The identifier of the new task created, or -1 if could not create
	 * the new task (maximum number of tasks exceeded or another error)
	 */
	public synchronized int newTask(int task_id, String task_name, String strClassName, String strMethodName, String[] strExtraInfo) {
		CronTask newTask;
		//Class cl;
		int iTaskID;
		// Do not run new tasks if it is uninitializing
		if (stoping) {
			return -1;
		}
		String params = "";
		try {
			iTaskID = iNextTaskID;

			// Creates the new task
			newTask = (CronTask) task_class.newInstance();
			newTask.setParams(iTaskID, task_id, task_name, strClassName, strMethodName, strExtraInfo);
			// Aded name to newTask to show a name instead of Threads whe 
			// logging
			// Thanks to Sander Verbruggen 
			int lastDot = strClassName.lastIndexOf(".");
			if (lastDot > 0 && lastDot < strClassName.length()) {
				String classOnlyName = strClassName.substring(lastDot + 1);
				newTask.setName(classOnlyName);
			}

			synchronized (tasks) {
				tasks.put(new Integer(iTaskID), new TaskTableEntry(strClassName, newTask));
			}
			// Starts the task execution
			newTask.setName("Crontask-" + iTaskID);
			newTask.start();

			if (strExtraInfo != null && strExtraInfo.length > 0) {
				for (int i = 0; i < strExtraInfo.length; i++) {
					params += strExtraInfo[i] + " ";
				}
			}
			//Log.info("newTask : " + strClassName + "#" + strMethodName + " " + params);
			// Increments the next task identifier
			iNextTaskID++;
			return iTaskID;

		} catch (Exception e) {
			Log.error("Smth was wrong with" + strClassName + "#" + strMethodName + " " + params, e);
		}
		return -1;
	}

	/**
	 * Removes a task from the internal arrays of active tasks. This method
	 * is called from method run() of CronTask when a task has finished.
	 * @return true if the task was deleted correctly, false otherwise
	 * @param iTaskID Identifier of the task to delete
	 */
	public boolean deleteTask(int iTaskID) {
		synchronized (tasks) {
			if (tasks.remove(new Integer(iTaskID)) == null)
			{return false;}
			return true;
		}
	}

	/**
	 * Returns an array with all active tasks
	 * @return An array with all active tasks
	 * NOTE: Does not returns the internal array because it is synchronized,
	 * returns a copy of it.
	 */
	public CronTask[] getAllTasks() {
		CronTask[] t;
		synchronized (tasks) {
			int i = 0;
			t = new CronTask[tasks.size()];
			Iterator iter = tasks.values().iterator();
			while (iter.hasNext()) {
				t[i] = ((TaskTableEntry) (iter.next())).task;
				i++;
			}
		}
		return t;
	}

	/** 
	 * Internal class that represents an entry in the task table 
	 */
	private class TaskTableEntry {
		String strClassName;
		CronTask task;

		/** Constructor of an entry of the task table
		 * @param strClassName Name of the class of the task
		 * @param task Reference to the task
		 */
		public TaskTableEntry(String strClassName, CronTask task) {
			this.strClassName = strClassName;
			this.task = task;
		}
	}

	/**
	 * @return
	 */
	public Class getViewClass() {
		return view_class;
	}

}
