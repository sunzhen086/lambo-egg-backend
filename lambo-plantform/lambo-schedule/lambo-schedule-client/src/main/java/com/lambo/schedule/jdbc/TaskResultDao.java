package com.lambo.schedule.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class TaskResultDao extends BaseJdbcDao{
	private static TaskResultDao instance;
	private static String querySQL="SELECT TASK_RESULT_ID, TASK_ID, TASK_NAME, START_TIME, END_TIME, RESULT, ERROE_MESSAGE FROM SCHEDULE_TASK_RESULT WHERE 1=1 ";
	
	private static String insertSQL="INSERT INTO SCHEDULE_TASK_RESULT (TASK_RESULT_ID, TASK_ID, TASK_NAME, START_TIME, END_TIME, RESULT, ERROE_MESSAGE) "
		+ "VALUES(?,?,?,?,?,?,?)";
	
	private static String updateByIdSQL="UPDATE SCHEDULE_TASK_RESULT "+
		"SET TASK_ID =?, TASK_NAME =?, START_TIME =?, END_TIME=?, RESULT=?, ERROE_MESSAGE=? WHERE TASK_RESULT_ID = ?";
	
	
	private static String deleteByIdSQL="DELETE FROM SCHEDULE_TASK_RESULT WHERE TASK_RESULT_ID = ?";
	
	private static String deleteByTaskIdSQL="DELETE FROM SCHEDULE_TASK_RESULT WHERE TASK_ID= ?";
	
	private static String deleteByTaskNameSQL="DELETE FROM SCHEDULE_TASK_RESULT WHERE TASK_NAME= ?";
	
	private static Logger logger = Logger.getLogger(BaseJdbcDao.class.getName());
	
	private TaskResultDao(){
		
	}
	
	public static TaskResultDao getInstance(){
		if(instance==null){
			instance=new TaskResultDao();
		}
		return instance;
	}
	
	public void insert(List taskResultBeans) throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(insertSQL);
			for (int i = 0; i < taskResultBeans.size(); i++) {
				//TASK_RESULT_ID, TASK_ID, TASK_NAME, START_TIME, END_TIME, RESULT, ERROE_MESSAGE
				TaskResultBean taskResultBean=(TaskResultBean)taskResultBeans.get(i);
				ps.setString(1, taskResultBean.getTaskResultId());
				ps.setInt(2, taskResultBean.getTaskId());
				ps.setString(3, taskResultBean.getTaskName());
				ps.setString(4, taskResultBean.getStartTime());
				ps.setString(5, taskResultBean.getEndTime());
				ps.setString(6, taskResultBean.getResult());
				ps.setString(7, taskResultBean.getErrorMessage());	
				ps.executeUpdate();
			}
		} catch(SQLException e){
			logger.error("insert taskResultBeans ERROR:",e);
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
	
	public void insert(TaskResultBean taskResultBean){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(insertSQL);
			ps.setString(1, taskResultBean.getTaskResultId());
			ps.setInt(2, taskResultBean.getTaskId());
			ps.setString(3, taskResultBean.getTaskName());
			ps.setString(4, taskResultBean.getStartTime());
			ps.setString(5, taskResultBean.getEndTime());
			ps.setString(6, taskResultBean.getResult());
			ps.setString(7, taskResultBean.getErrorMessage());	
			ps.executeUpdate();
		} catch(SQLException e){
			logger.error("insert taskResultBean ERROR:",e);
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
	
	public void updateById(List taskResultBeans){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(updateByIdSQL);
			for (int i = 0; i < taskResultBeans.size(); i++) {
				TaskResultBean taskResultBean=(TaskResultBean)taskResultBeans.get(i);
				ps.setInt(1, taskResultBean.getTaskId());
				ps.setString(2, taskResultBean.getTaskName());
				ps.setString(3, taskResultBean.getStartTime());
				ps.setString(4, taskResultBean.getEndTime());
				ps.setString(5, taskResultBean.getResult());
				ps.setString(6, taskResultBean.getErrorMessage());	
				ps.setString(7, taskResultBean.getTaskResultId());			
				ps.executeUpdate();
			}
		} catch(SQLException e){
			logger.error("update By Id ERROR:",e);
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
	
	public void updateById(TaskResultBean taskResultBean){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(updateByIdSQL);
			ps.setInt(1, taskResultBean.getTaskId());
			ps.setString(2, taskResultBean.getTaskName());
			ps.setString(3, taskResultBean.getStartTime());
			ps.setString(4, taskResultBean.getEndTime());
			ps.setString(5, taskResultBean.getResult());
			ps.setString(6, taskResultBean.getErrorMessage());	
			ps.setString(7, taskResultBean.getTaskResultId());			
			ps.executeUpdate();
		} catch(SQLException e){
			logger.error("update By Id ERROR:",e);			
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
		List list=excuteQuery(querySQL, queryConditionList," ORDER BY START_TIME DESC");
		if(list!=null){
			List taskResultBeans=new ArrayList();
			//TASK_RESULT_ID, TASK_ID, TASK_NAME, START_TIME, END_TIME, RESULT, ERROE_MESSAGE
			for(int i=0;i<list.size();i++){
				TaskResultBean taskResultBean=new TaskResultBean();
				Map map=(Map) list.get(i);
				taskResultBean.setTaskResultId((String)map.get("TASK_RESULT_ID"));
				taskResultBean.setTaskId(Integer.parseInt((String)map.get("TASK_ID")));
				taskResultBean.setTaskName((String)map.get("TASK_NAME"));
				taskResultBean.setStartTime((String)map.get("START_TIME"));
				taskResultBean.setEndTime((String)map.get("END_TIME"));
				taskResultBean.setResult((String)map.get("RESULT"));
				taskResultBean.setErrorMessage((String)map.get("ERROE_MESSAGE"));
				taskResultBeans.add(taskResultBean);
			}
			return taskResultBeans;
		}
		return new ArrayList();	
	}
	
	public void deleteById(String taskResultId){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(deleteByIdSQL);
			ps.setString(1, taskResultId);			
			ps.executeUpdate();
		} catch(SQLException e){
			logger.error("Delete By Id ERROR:",e);
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
	
	public void deleteByTaskName(String taskName){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(deleteByTaskNameSQL);
			ps.setString(1, taskName);			
			ps.executeUpdate();
		} catch(SQLException e){
			logger.error("Delete By Task Name ERROR:",e);
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
	
	public void deleteByTaskId(int taskId){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(deleteByTaskIdSQL);
			ps.setInt(1, taskId);		
			ps.executeUpdate();
		} catch(SQLException e){
			logger.error("Delete By Task Id ERROR:",e);
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
}
