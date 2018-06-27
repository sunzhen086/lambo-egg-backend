
package com.lambo.schedule.util;

import java.util.Calendar;


public  class DateFormat extends Thread {

	public static String format(Calendar date){
		StringBuffer sb = new StringBuffer();
		sb.append(date.get(Calendar.YEAR)).append("-");
		if(date.get(Calendar.MONTH)<9){sb.append(0);}
		sb.append(date.get(Calendar.MONTH) + 1).append("-");
		if(date.get(Calendar.DAY_OF_MONTH)<10){sb.append(0);}
		sb.append(date.get(Calendar.DAY_OF_MONTH));
		sb.append(" ");
		if(date.get(Calendar.HOUR_OF_DAY)<10){sb.append(0);}
		sb.append(date.get(Calendar.HOUR_OF_DAY)).append(":");
		if(date.get(Calendar.MINUTE)<10){sb.append(0);}
		sb.append(date.get(Calendar.MINUTE)).append(":");
		if(date.get(Calendar.SECOND)<10){sb.append(0);}
		sb.append(date.get(Calendar.SECOND));
		return sb.toString();
	}
}
