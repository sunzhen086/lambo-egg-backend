package com.lambo.schedule.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class TaskDao extends BaseJdbcDao{
	private static TaskDao instance;
	private static String querySQL="SELECT TASK_ID, TASK_NAME, MONTH, DAYOFMONTH, DAYOFWEEK, HOUR, MINUTE, CURRENT_STATE, OPERATION, EXTRAINFO, YEAR, DESCRIPTION FROM SCHEDULE_TASK WHERE 1=1 ";
	
	private static String insertSQL="INSERT INTO SCHEDULE_TASK (TASK_ID, TASK_NAME, MONTH, DAYOFMONTH, DAYOFWEEK, HOUR, MINUTE, CURRENT_STATE, OPERATION, EXTRAINFO, YEAR, DESCRIPTION) "
		+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
	
	private static String updateByIdSQL="UPDATE SCHEDULE_TASK "+
		"SET MONTH =?, DAYOFMONTH =?, DAYOFWEEK=?, HOUR=?, MINUTE=?, CURRENT_STATE=?, OPERATION=?, EXTRAINFO=?, YEAR=?, DESCRIPTION=? WHERE TASK_ID = ?";
	
	private static String updateByNameSQL="UPDATE SCHEDULE_TASK "+
		"SET MONTH=?,DAYOFMONTH=?, DAYOFWEEK=?, HOUR=?, MINUTE=?, CURRENT_STATE=?, OPERATION=?, EXTRAINFO=?,YEAR=?, DESCRIPTION=? WHERE TASK_NAME = ?";
	
	private static String deleteByIdSQL="DELETE FROM SCHEDULE_TASK WHERE TASK_ID = ?";
	
	private static String deleteByNameSQL="DELETE FROM SCHEDULE_TASK WHERE TASK_NAME= ?";
	
	private static String changeStateByIdSQL="UPDATE SCHEDULE_TASK SET CURRENT_STATE=?  WHERE TASK_ID = ?";
	
	private static String changeStateByNameSQL="UPDATE SCHEDULE_TASK SET CURRENT_STATE=?  WHERE TASK_Name = ?";
	
	private static String changeRunToStartByIdSQL="UPDATE SCHEDULE_TASK SET CURRENT_STATE=?  WHERE TASK_ID = ? AND CURRENT_STATE = ?";
	
	/** This Query finds the next value in the sequence 
	 */
	private static String nextSequence = "SELECT MAX(TASK_ID) TASK_ID FROM SCHEDULE_TASK ";
	
	private static Logger logger = Logger.getLogger(BaseJdbcDao.class.getName());
	
	private TaskDao(){
		
	}
	
	public static TaskDao getInstance(){
		if(instance==null){
			instance=new TaskDao();
		}
		return instance;
	}
	
	public void insert(List taskBeans) throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(insertSQL);
			for (int i = 0; i < taskBeans.size(); i++) {
				TaskBean taskBean=(TaskBean)taskBeans.get(i);
				if (taskBean.getTaskId() == -1)
					addId(taskBean, conn);
				ps.setInt(1, taskBean.getTaskId());
				ps.setString(2, taskBean.getTaskName());
				ps.setString(3, taskBean.getMonth());
				ps.setString(4, taskBean.getDayofmonth());
				ps.setString(5, taskBean.getDayofweek());
				ps.setString(6, taskBean.getHour());
				ps.setString(7, taskBean.getMinute());
				ps.setString(8, taskBean.getCurrentState());
				ps.setString(9, taskBean.getOperation());
				ps.setString(10, taskBean.getExtrainfo());	
				ps.setString(11, taskBean.getYear());
				ps.setString(12, taskBean.getDescription());
				ps.executeUpdate();
			}
		} catch(SQLException e){
			logger.error("insert taskBeans ERROR:",e);			
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
	public void insert(TaskBean taskBean) throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(insertSQL);
			if (taskBean.getTaskId() == -1)
				addId(taskBean, conn);
			ps.setInt(1, taskBean.getTaskId());
			ps.setString(2, taskBean.getTaskName());
			ps.setString(3, taskBean.getMonth());
			ps.setString(4, taskBean.getDayofmonth());
			ps.setString(5, taskBean.getDayofweek());
			ps.setString(6, taskBean.getHour());
			ps.setString(7, taskBean.getMinute());
			ps.setString(8, taskBean.getCurrentState());
			ps.setString(9, taskBean.getOperation());
			ps.setString(10, taskBean.getExtrainfo());	
			ps.setString(11, taskBean.getYear());
			ps.setString(12, taskBean.getDescription());
			ps.executeUpdate();
		} catch(SQLException e){
			logger.error("insert taskBean ERROR:",e);		
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
	
	public void updateById(List taskBeans){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(updateByIdSQL);
			for (int i = 0; i < taskBeans.size(); i++) {
				TaskBean taskBean=(TaskBean)taskBeans.get(i);
				ps.setString(1, taskBean.getMonth());
				ps.setString(2, taskBean.getDayofmonth());
				ps.setString(3, taskBean.getDayofweek());
				ps.setString(4, taskBean.getHour());
				ps.setString(5, taskBean.getMinute());
				ps.setString(6, taskBean.getCurrentState());
				ps.setString(7, taskBean.getOperation());
				ps.setString(8, taskBean.getExtrainfo());
				ps.setString(9, taskBean.getYear());
				ps.setString(10, taskBean.getDescription());
				ps.setInt(11, taskBean.getTaskId());		
				ps.executeUpdate();
			}
		} catch(SQLException e){
			logger.error("Update By Id ERROR:",e);			
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
	
	public void updateById(TaskBean taskBean){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(updateByIdSQL);
				ps.setString(1, taskBean.getMonth());
				ps.setString(2, taskBean.getDayofmonth());
				ps.setString(3, taskBean.getDayofweek());
				ps.setString(4, taskBean.getHour());
				ps.setString(5, taskBean.getMinute());
				ps.setString(6, taskBean.getCurrentState());
				ps.setString(7, taskBean.getOperation());
				ps.setString(8, taskBean.getExtrainfo());
				ps.setString(9, taskBean.getYear());
				ps.setString(10, taskBean.getDescription());
				ps.setInt(11, taskBean.getTaskId());	
				ps.executeUpdate();
		} catch(SQLException e){
			logger.error("Update By Id ERROR:",e);
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
	
	public void updateByName(List taskBeans){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(updateByNameSQL);
			for (int i = 0; i < taskBeans.size(); i++) {
				TaskBean taskBean=(TaskBean)taskBeans.get(i);
				ps.setString(1, taskBean.getMonth());
				ps.setString(2, taskBean.getDayofmonth());
				ps.setString(3, taskBean.getDayofweek());
				ps.setString(4, taskBean.getHour());
				ps.setString(5, taskBean.getMinute());
				ps.setString(6, taskBean.getCurrentState());
				ps.setString(7, taskBean.getOperation());
				ps.setString(8, taskBean.getExtrainfo());
				ps.setString(9, taskBean.getYear());
				ps.setString(10, taskBean.getDescription());
				ps.setString(11, taskBean.getTaskName());
				ps.executeUpdate();
			}
		} catch(SQLException e){
			logger.error("Update By Name ERROR:",e);
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
	
	public void changeStateById(String state, int id){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(changeStateByIdSQL);
			ps.setString(1, state);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch(SQLException e){
			logger.error("Change State By Id ERROR:",e);
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
	
	
	public void changeStateByName(String state, String taskName){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(changeStateByNameSQL);
			ps.setString(1, state);
			ps.setString(2, taskName);
			ps.executeUpdate();
		} catch(SQLException e){
			logger.error("Change State By Name ERROR:",e);
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
	public void changeRunToStartById(int id){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(changeRunToStartByIdSQL);
			ps.setString(1, Task.CURRENT_STATE_START);
			ps.setInt(2, id);
			ps.setString(3, Task.CURRENT_STATE_RUNNING);
			ps.executeUpdate();
		} catch(SQLException e){
			logger.error("Change Run To Start By Id ERROR:",e);		
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
	
	public List query(List queryConditionList){
		List list=excuteQuery(querySQL, queryConditionList);
		if(list!=null){
			List taskBeans=new ArrayList();
			//TASK_ID, TASK_NAME, MONTH, DAYOFMONTH, DAYOFWEEK, HOUR, MINUTE, CURRENT_STATE, OPERATION, EXTRAINFO, BUSINESS_DAYS
			for(int i=0;i<list.size();i++){
				TaskBean taskBean=new TaskBean();
				Map map=(Map) list.get(i);
				taskBean.setTaskId(Integer.parseInt((String)map.get("TASK_ID")));
				taskBean.setTaskName((String)map.get("TASK_NAME"));
				taskBean.setMonth((String)map.get("MONTH"));
				taskBean.setDayofmonth((String)map.get("DAYOFMONTH"));
				taskBean.setDayofweek((String)map.get("DAYOFWEEK"));
				taskBean.setHour((String)map.get("HOUR"));
				taskBean.setMinute((String)map.get("MINUTE"));
				taskBean.setOperation((String)map.get("OPERATION"));
				taskBean.setCurrentState((String)map.get("CURRENT_STATE"));
				taskBean.setExtrainfo((String)map.get("EXTRAINFO"));
				taskBean.setDescription((String)map.get("DESCRIPTION"));
				String year=(String)map.get("YEAR");
				if(year==null||"".equals(year)){
					year="*";
				}
				taskBean.setYear(year);
				taskBeans.add(taskBean);
			}
			return taskBeans;
		}
		return new ArrayList();	
	}
	
	public void deleteById(int taskId){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(deleteByIdSQL);
			ps.setInt(1, taskId);			
			ps.executeUpdate();
		} catch(SQLException e){
			logger.warn("Delete By Id ERROR:",e);	
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
	
	public void deleteByName(String taskName){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(deleteByNameSQL);
			ps.setString(1, taskName);			
			ps.executeUpdate();
		} catch(SQLException e){
			logger.error("Delete By Name ERROR:",e);		
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
	 * This method adds the correct id to the Bean. This method is could be 
	 * replaced by other methods if you need to do this as protected plz let 
	 * me know
	 *
	 * @param CrontabEntryBean The CrontabEntryBean to add Id
	 * @param Connection the conn to access to the data
	 * 
	 * @exception SQLExcption if smth is wrong
	 */
	private void addId(TaskBean bean, Connection conn) throws SQLException {
		java.sql.Statement st = conn.createStatement();
		java.sql.ResultSet rs = st.executeQuery(nextSequence);
		if (rs != null) {
			int taskId=1;
			while (rs.next()) {
				taskId = rs.getInt("TASK_ID");
				taskId++;
			}
			bean.setTaskId(taskId);
		}
		return;
	}

}
