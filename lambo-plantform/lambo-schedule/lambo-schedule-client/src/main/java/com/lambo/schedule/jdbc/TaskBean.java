package com.lambo.schedule.jdbc;

public class TaskBean {
	private int taskId=-1;
	private String taskName;
	private String month;
	private String dayofmonth;
	private String dayofweek;
	private String hour;
	private String minute;
	private String currentState;
	private String operation;
	private String extrainfo;
	private String year;
	private String description;
	
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDayofmonth() {
		return dayofmonth;
	}
	public void setDayofmonth(String dayofmonth) {
		this.dayofmonth = dayofmonth;
	}
	public String getDayofweek() {
		return dayofweek;
	}
	public void setDayofweek(String dayofweek) {
		this.dayofweek = dayofweek;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public String getMinute() {
		return minute;
	}
	public void setMinute(String minute) {
		this.minute = minute;
	}
	public String getCurrentState() {
		return currentState;
	}
	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getExtrainfo() {
		return extrainfo;
	}
	public void setExtrainfo(String extrainfo) {
		this.extrainfo = extrainfo;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getYear() {
		return year;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}

}
