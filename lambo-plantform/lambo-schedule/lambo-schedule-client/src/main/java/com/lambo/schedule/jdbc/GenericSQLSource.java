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

package com.lambo.schedule.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.log4j.Logger;
import com.lambo.schedule.data.CrontabEntryBean;
import com.lambo.schedule.data.CrontabEntryException;
import com.lambo.schedule.data.CrontabParser;
import com.lambo.schedule.data.DataNotFoundException;
import com.lambo.schedule.data.DataSource;

/**
 * This class is only a generic example and doesn't aim to solve all the needs
 * for the differents system's. if you want to make this class to fit your needs
 * feel free to do it and remember the license.
 * On of the things this class does is to open a connection to the database
 * , this is nasty and very expensive, y you want to integrate jcrontab with a 
 * pool like poolman or jboss it's quite easy, should substitute connection logic
 * with particular one.
 * @author $Author: jiadx $
 * @version $Revision: 2875 $
 */
public class GenericSQLSource implements DataSource {
	private static Logger logger = Logger.getLogger(GenericSQLSource.class.getName());

	private CrontabParser cp = new CrontabParser();

	private static GenericSQLSource instance;

	/** This Query gets all the Crontab entries from the
	 * events table
	 */
	public static String queryAll =
		"SELECT TASK_ID, TASK_NAME, MONTH, DAYOFMONTH, DAYOFWEEK, HOUR, MINUTE, CURRENT_STATE, OPERATION, EXTRAINFO FROM SCHEDULE_TASK";
	/** This Query gets all the Crontab entries from the
	 * events table but searching by the name
	 */
	public static String querySearching =
		"SELECT TASK_ID, TASK_NAME, MONTH, DAYOFMONTH, DAYOFWEEK, HOUR, MINUTE, YEAR, CURRENT_STATE, OPERATION, EXTRAINFO FROM SCHEDULE_TASK"
			+ " WHERE TASK_NAME = ? ";
	/** This Query stores the Crontab entries
	 */
	public static String queryStoring =
		"INSERT INTO SCHEDULE_TASK (TASK_ID, TASK_NAME, MONTH, DAYOFMONTH, DAYOFWEEK, HOUR, MINUTE, CURRENT_STATE, OPERATION, EXTRAINFO) " +
		"VALUES(?,?,?,?,?,?,?,?,?,?)";
	
	public static String queryStoringWithYear =
		"INSERT INTO SCHEDULE_TASK (TASK_ID, TASK_NAME, MONTH, DAYOFMONTH, DAYOFWEEK, HOUR, MINUTE, CURRENT_STATE, OPERATION, EXTRAINFO, YEAR) " +
		"VALUES(?,?,?,?,?,?,?,?,?,?,?)";

	/** This Query removes the given Crontab Entries
	 */
	public static String queryRemoving = "DELETE FROM SCHEDULE_TASK WHERE " + " TASK_NAME = ? ";

	/** This Query finds the next value in the sequence 
	 */
	public static String nextSequence = "SELECT MAX(TASK_ID) TASK_ID FROM SCHEDULE_TASK ";

	/** Creates new GenericSQLSource */

	public GenericSQLSource() {
	}

	/** This method grants this class to be a singleton
	 * and grants data access integrity
	 * @return returns the instance
	 */
	public DataSource getInstance() {
		if (instance == null) {
			instance = new GenericSQLSource();
		}
		return instance;
	}

	/**
	 *  This method searches the Crontab Entry that the class has the given name
	 *  @para CrontabEntryBean bean this method only lets store an
	 * entryBean each time.
	 *  @throws CrontabEntryException when it can't parse the line correctly
	 *  @throws ClassNotFoundException cause loading the driver can throw an
	 *  ClassNotFoundException
	 *  @throws SQLException Yep can throw an SQLException too
	 */
	public CrontabEntryBean find(CrontabEntryBean ceb)
		throws CrontabEntryException, ClassNotFoundException, SQLException, DataNotFoundException {
		CrontabEntryBean[] cebra = findAll();
		for (int i = 0; i < cebra.length; i++) {
			if (cebra[i].equals(ceb)) {
				return cebra[i];
			}
		}
		throw new DataNotFoundException("Unable to find :" + ceb);
	}

	public CrontabEntryBean findByName(String name)  throws CrontabEntryException, ClassNotFoundException,SQLException, DataNotFoundException {
		CrontabEntryBean[] cebra = findAll();
		for (int i = 0; i < cebra.length; i++) {
			if (cebra[i].getName().equals(name)) {
				//System.out.println("cebra encontrada : " + cebra[i]);
				return cebra[i];
			}
		}
		return null;
	}

	/**
	 *  This method searches all the CrontabEntries from the DataSource
	 *  @return CrontabEntryBean[] the array of CrontabEntryBeans.
	 *  @throws CrontabEntryException when it can't parse the line correctly
	 *  @throws ClassNotFoundException cause loading the driver can throw an
	 *  ClassNotFoundException
	 *  @throws SQLException Yep can throw an SQLException too
	 */
	public CrontabEntryBean[] findAll()
		throws CrontabEntryException, ClassNotFoundException, SQLException, DataNotFoundException {
		Vector list = new Vector();
		Connection conn = null;
		java.sql.Statement st = null;
		java.sql.ResultSet rs = null;
		try {
			conn = getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(queryAll);
			if (rs != null) {
				while (rs.next()) {
					int i=0;
					boolean[] bSeconds = new boolean[60];
					boolean[] bYears = new boolean[10];
					int taskId = rs.getInt("TASK_ID");
					String minute = rs.getString("MINUTE");
					String hour = rs.getString("HOUR");
					String dayofmonth = rs.getString("DAYOFMONTH");
					String month = rs.getString("MONTH");
					String dayofweek = rs.getString("DAYOFWEEK");
					//String year = rs.getString("YEAR");
					String operation = rs.getString("OPERATION");
					String state = "START";
					if("00".equals(rs.getString("CURRENT_STATE"))){
						state = "STOP";
					}
					String taskName = rs.getString("TASK_NAME");
					String extrainfo = rs.getString("extrainfo");
					StringBuffer line = new StringBuffer();
					line.append(minute);
					line.append(" ");
					line.append(hour);
					line.append(" ");
					line.append(dayofmonth);
					line.append(" ");
					line.append(month);
					line.append(" ");
					line.append(dayofweek);
					line.append(" ");
					line.append(operation);
					line.append("(").append(state).append(")");
					line.append("#").append(taskName).append("#");
					line.append(" ");
					line.append(extrainfo);
					try{
						CrontabEntryBean ceb = cp.marshall(line.toString()); //解析crontab
						if(ceb==null){
							logger.debug("定时任务ID" + taskId + "设置有问题");
							logger.debug("如果cel为null说明定时任务设置的有问题，忽略不做出来");
						}else{
							logger.debug("装载成CrontabEntryBean marshall="+(i++)+":::"+line);
							cp.parseToken("*", bYears, false);
							ceb.setBYears(bYears);
							ceb.setYears("*");
							cp.parseToken("0", bSeconds, false);
							ceb.setBSeconds(bSeconds);
							ceb.setSeconds("0");

							ceb.setId(taskId);

							list.add(ceb);
						}
					}catch(CrontabEntryException e){
						logger.error("Task["+line+"] format ERROR:", e);
					}
				}
				rs.close();
			} else {
				throw new DataNotFoundException("No CrontabEntries available");
			}
			logger.debug("list::"+list);
		} finally {
			try {
				st.close();
			} catch (Exception e) {
				logger.warn(e);
			}
			try {
				conn.close();
			} catch (Exception e2) {
				logger.warn(e2);
			}
		}
		CrontabEntryBean[] result = new CrontabEntryBean[list.size()];
		for (int i = 0; i < list.size(); i++) {
			result[i] = (CrontabEntryBean) list.get(i);
			//logger.debug("CrontabEntryBean加载成数组：：："+i);
		}
		return result;
	}

	/**
	*  This method removes the given Crontab Entries 
	*  @param CrontabEntryBean bean this method only lets store an 
	* entryBean each time.
	*  @throws CrontabEntryException when it can't parse the line correctly
	 *  @throws ClassNotFoundException cause loading the driver can throw an
	 *  ClassNotFoundException
	 *  @throws SQLException Yep can throw an SQLException too
	*/

	public void remove(String name) throws CrontabEntryException, ClassNotFoundException, SQLException,DataNotFoundException {
		TaskResultDao.getInstance().deleteByTaskName(name);
	  	TaskDao.getInstance().deleteByName(name);
	}

	/**
	 *  This method saves the CrontabEntryBean the actual problem with this
	 *  method is that doesn't store comments and blank lines from the 
	 *  original file any ideas?
	 *  @param CrontabEntryBean bean this method only lets store an 
	 * entryBean each time.
	 *  @throws CrontabEntryException when it can't parse the line correctly
	 *  @throws ClassNotFoundException cause loading the driver can throw an
	 *  ClassNotFoundException
	 *  @throws SQLException Yep can throw an SQLException too
	 */
	public void store(CrontabEntryBean[] beans) throws CrontabEntryException, ClassNotFoundException, SQLException {

		Connection conn = null;
		java.sql.PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(queryStoring);
			for (int i = 0; i < beans.length; i++) {
				if (beans[i].getId() == -1)
					addId(beans[i], conn);
				ps.setInt(1, beans[i].getId());
				ps.setString(2, beans[i].getName());
				ps.setString(3, beans[i].getMonths());
				ps.setString(4, beans[i].getDaysOfMonth());
				ps.setString(5, beans[i].getDaysOfWeek());
				ps.setString(6, beans[i].getHours());
				ps.setString(7, beans[i].getMinutes());
				if(!beans[i].isStart()){
					ps.setString(8, "00");
				}else{
					ps.setString(8, "10");
				}
				if (beans[i].getMethodName()==null||"".equals(beans[i].getMethodName())) {
					ps.setString(9, beans[i].getClassName());
				} else {
					String classAndMethod = beans[i].getClassName() + "@" + beans[i].getMethodName();
					ps.setString(9, classAndMethod);
				}

				String extraInfo[] = beans[i].getExtraInfo();
				String extraInfob = new String();
				if (extraInfo!=null&&extraInfo.length > 0) {
					for (int z = 0; z < extraInfo.length; z++) {
						if(z!=0){
							extraInfob += " ";
						}
						extraInfob += extraInfo[z];
					}
				}
				ps.setString(10, extraInfob);
				String year="*";
				if( beans[i].getYear()!=null && !"".equals(beans[i].getYear()) ){
					year=beans[i].getYear();
				}
				ps.setString(11, year);
				ps.executeUpdate();
			}
		} finally {
			try {
				ps.close();
			} catch (Exception e) {
				logger.warn(e);
			}
			try {
				conn.close();
			} catch (Exception e2) {
				logger.warn(e2);
			}
		}
	}

	/**
	 *  This method saves the CrontabEntryBean the actual problem with this
	 *  method is that doesn't store comments and blank lines from the 
	 *  original file any ideas?
	 *  @param CrontabEntryBean bean this method only lets store an 
	 * entryBean each time.
	 *  @throws CrontabEntryException when it can't parse the line correctly
	 *  @throws ClassNotFoundException cause loading the driver can throw an
	 *  ClassNotFoundException
	 *  @throws SQLException Yep can throw an SQLException too
	 */
	public void store(CrontabEntryBean bean) throws CrontabEntryException, ClassNotFoundException, SQLException {
		CrontabEntryBean[] list = { bean };
		store(list);
	}
	/**
	 * Retrieves a connection to the database.  May use a Connection Pool 
	 * DataSource or JDBC driver depending on the properties.
	 *
	 * @return a <code>Connection</code>
	 * @exception SQLException if there is an error retrieving the Connection.
	 */
	protected Connection getConnection() throws SQLException {
		return DataSourceFactory.getInstance().getDataSource().getConnection();
	}

	/** 
	 * This method adds the correct id to the Bean. This method is could be 
	 * replaced by other methods if you need to do this as protected plz let 
	 * me know
	 *
	 * @param CrontabEntryBean The CrontabEntryBean to add Id
	 * @param Connection the conn to access to the data
	 * 
	 * @exception SQLExcption if smth is wrong
	 */
	private void addId(CrontabEntryBean bean, Connection conn) throws SQLException {
		java.sql.Statement st = conn.createStatement();
		java.sql.ResultSet rs = st.executeQuery(nextSequence);
		if (rs != null) {
			int id=1;
			while (rs.next()) {
				id = rs.getInt("TASK_ID");
				id++;
			}
			bean.setId(id);
		}
		return;
	}

	/* （非 Javadoc）
	 * @see org.loushang.commons.schedule.data.DataSource#loadAllCrontabEntry()
	 */
	public void loadAllCrontabEntry() throws Exception {
		// TODO 自动生成方法存根
		
	}

	public String getType() {
		// TODO Auto-generated method stub
		return "DB";
	}
}
