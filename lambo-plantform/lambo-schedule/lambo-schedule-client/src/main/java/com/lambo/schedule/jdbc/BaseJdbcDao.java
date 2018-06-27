package com.lambo.schedule.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public abstract class BaseJdbcDao {
	/**
	 * Retrieves a connection to the database.  May use a Connection Pool 
	 * DataSource or JDBC driver depending on the properties.
	 *
	 * @return a <code>Connection</code>
	 * @exception SQLException if there is an error retrieving the Connection.
	 */
	
	private static Logger logger = Logger.getLogger(BaseJdbcDao.class.getName());
	
	protected Connection getConnection() throws SQLException {
		Connection conn;
		try{
			conn=DataSourceFactory.getInstance().getDataSource().getConnection();
		}catch (SQLException e){
			logger.error("get SQL Connection ERROR:",e);
			throw e;
		}
		return conn;
	}
	
	protected List excuteQuery(String sql , List queryConditionList,String otherCondition){
		Connection conn=null;
		PreparedStatement ps = null;
		StringBuffer sb=new StringBuffer(sql);
		try {
			conn=getConnection();
			int size=queryConditionList.size();
			for(int i=0;i<size;i++){
				QueryCondition queryCondition=(QueryCondition)queryConditionList.get(i);
				sb.append(" and ").append(queryCondition.getSql());
			}
			if(otherCondition!=null){
				sb.append(otherCondition);
			}
			ps=conn.prepareStatement(sb.toString());
			for(int i=0;i<size;i++){
				QueryCondition queryCondition=(QueryCondition)queryConditionList.get(i);
				switch (queryCondition.getType()) {
				case Types.INTEGER:
				case Types.DECIMAL:
					ps.setInt(i+1, Integer.valueOf(queryCondition.getValue())) ;
				case Types.VARCHAR:
				case Types.DATE:
				case Types.TIME:
				case Types.TIMESTAMP:
				default:
					ps.setObject(i+1, String.valueOf(queryCondition.getValue()));
				}				
			}
			ResultSet rs=ps.executeQuery();
			List results=new ArrayList();
			while(rs.next()){
				Map map=new HashMap();
				ResultSetMetaData metaData=rs.getMetaData();
				for(int i=1;i<=metaData.getColumnCount();i++){
					String columnName=metaData.getColumnName(i);
					map.put(columnName,rs.getString(columnName));
				}
				results.add(map);				
			}
			return results;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("excuteQuery ERROR:", e);
		} catch (Throwable e){
			logger.error("excuteQuery ERROR:", e);
		} finally{
			try {
				ps.close();
			} catch (Exception e) {
				logger.warn("close PreparedStatement warn:",e);
			}
			try {
				conn.close();
			} catch (Exception e2) {
				logger.warn("close Connection warn:",e2);
			}
		}
		return null;
		
	}
	protected List excuteQuery(String sql , List queryConditionList){
		return this.excuteQuery(sql, queryConditionList, null);
	}
	
	
	protected List excuteUpdate(String sql , List queryConditionList){
		Connection conn=null;
		PreparedStatement ps = null;
		StringBuffer sb=new StringBuffer(sql);
		try {
			conn=getConnection();
			int size=queryConditionList.size();
			for(int i=0;i<size;i++){
				QueryCondition queryCondition=(QueryCondition)queryConditionList.get(i);
				sb.append(" and ").append(queryCondition.getSql());
			}
			ps=conn.prepareStatement(sb.toString());
			for(int i=0;i<size;i++){
				QueryCondition queryCondition=(QueryCondition)queryConditionList.get(i);
				switch (queryCondition.getType()) {
				case Types.INTEGER:
				case Types.DECIMAL:
					ps.setInt(i+1, Integer.valueOf(queryCondition.getValue())) ;
				case Types.VARCHAR:
				case Types.DATE:
				case Types.TIME:
				case Types.TIMESTAMP:
				default:
					ps.setObject(i+1, String.valueOf(queryCondition.getValue()));
				}				
			}
			ResultSet rs=ps.executeQuery();
			List results=new ArrayList();
			while(rs.next()){
				Map map=new HashMap();
				ResultSetMetaData metaData=rs.getMetaData();
				for(int i=0;i<metaData.getColumnCount();i++){
					String columnName=metaData.getColumnName(i);
					map.put(columnName,rs.getString(columnName));
				}
				results.add(map);				
			}
			return results;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("excuteUpdate ERROR:",e);
		} finally{
			try {
				ps.close();
			} catch (Exception e) {
				logger.warn("close PreparedStatement warn:",e);
			}
			try {
				conn.close();
			} catch (Exception e2) {
				logger.warn("close Connection warn:",e2);
			}
		}
		return null;
		
	}
}
