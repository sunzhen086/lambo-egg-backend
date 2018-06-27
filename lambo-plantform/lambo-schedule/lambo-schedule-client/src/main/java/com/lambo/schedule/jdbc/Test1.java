package com.lambo.schedule.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class Test1 extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		DataSource ds=null;
		Connection conn = null;
		try {
			ds=DefaultDataSourceFactory.getInstance().getDataSource();
			conn=ds.getConnection();
			//String sql="select * from FACT where id = ?";
			String sql="SELECT * FROM SCHEDULE_TASK";
			PreparedStatement ps=conn.prepareStatement(sql);
			//ps.setString(1, "001");
			ResultSet result=ps.executeQuery();
			//ResultSetMetaData metaData=result.getMetaData();
			while(result.next()){
				System.out.println(result.getString("TASK_NAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}


}
