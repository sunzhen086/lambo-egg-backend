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
package com.lambo.schedule.data;

import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import com.lambo.schedule.data.CrontabEntryBean;
/** This class parses a Line and returns CrontabEntryBean. This class
 * is done to do more modular and eficient 
 * @author $Author: jiadx $
 * @version $Revision: 2879 $
 */

public class CrontabParser {
	final static String EACH = "*";
	static Logger log = Logger.getLogger(CrontabParser.class.getName());
	/**
	 * Parses a string describing this time table entry and sets the 
	 * neded variables in order to build a CrontabEntry.
	 * Crontab Line usually something similar to:
	 * * * * * * org.loushang.commons.schedule.jcrontab
	 * @param entry the line to parse
	 * @throws CrontabEntryException Error parsing the string
	 */
	public CrontabEntryBean marshall(String entry)  {
		CrontabEntryBean ceb = new CrontabEntryBean();
      try{
		log.debug("CrontabEntryBean的marshall函数开始。。。");
		boolean[] bHours = new boolean[24];
		boolean[] bMinutes = new boolean[60];
		boolean[] bMonths = new boolean[12];
		boolean[] bDaysOfWeek = new boolean[7];
		boolean[] bDaysOfMonth = new boolean[31];

		StringTokenizer tokenizer = new StringTokenizer(entry);

		int numTokens = tokenizer.countTokens();
		for (int i = 0; tokenizer.hasMoreElements(); i++) {
			String token = tokenizer.nextToken();
			log.debug("tokenizer的值:"+token);
			switch (i) {
				case 0 : // Minutes
					parseToken(token, bMinutes, false);
					ceb.setBMinutes(bMinutes);
					ceb.setMinutes(token);
					break;
				case 1 : // Hours
					parseToken(token, bHours, false);
					ceb.setBHours(bHours);
					ceb.setHours(token);
					break;
				case 2 : // Days of month
					parseToken(token, bDaysOfMonth, true);
					ceb.setBDaysOfMonth(bDaysOfMonth);
					ceb.setDaysOfMonth(token);
					break;
				case 3 : // Months
					parseToken(token, bMonths, true);
					ceb.setBMonths(bMonths);
					ceb.setMonths(token);
					break;
				case 4 : // Days of week
					parseToken(token, bDaysOfWeek, false);
					ceb.setBDaysOfWeek(bDaysOfWeek);
					ceb.setDaysOfWeek(token);
					break;
				case 5 : // Name of the class
					String className, methodName;
					try {
						int left = 0, right = 0;
						//名称在后，先取名称
						left = token.indexOf("#");
						right = token.lastIndexOf("#");
						if (left > 0 && right > 0 && right > left) {
							String name = token.substring(left + 1, right);
							ceb.setName(name);
						}
						if (left > 0)
							token = token.substring(0, left);

						//然后取状态
						left = token.indexOf("(");
						right = token.indexOf(")");
						if (left > 0 && right > 0 && right > left) {
							String state = token.substring(left + 1, right).toUpperCase();
							ceb.setState(state);
						}
						if (left > 0)
							token = token.substring(0, left);
						//最后取任务名和方法名
						int m_index = token.indexOf("@");
						if (m_index > 0) {
							//System.out.println("token: " + token);
							StringTokenizer tokenize = new StringTokenizer(token, "@");
							className = tokenize.nextToken();
							if (tokenize.hasMoreElements())
								methodName = tokenize.nextToken();
							else
								methodName = "";
							ceb.setClassName(className);
							ceb.setMethodName(methodName);
						} else {
							className = token;
							ceb.setClassName(className);
						}
					} catch (Exception e) {
						e.printStackTrace();
						throw new CrontabEntryException(e, entry);
					}
					break;
				case 6 : // Extra Information
					String[] extraInfo = new String[numTokens - 6];
					boolean bextraInfo = true;
					for (extraInfo[i - 6] = token; tokenizer.hasMoreElements(); extraInfo[i - 6] = tokenizer.nextToken()) {
						i++;
					}
					ceb.setBExtraInfo(bextraInfo);
					ceb.setExtraInfo(extraInfo);
					break;
				default :
					break;
			}
			log.debug("BHours:"+ceb.getBHours());
			log.debug("Hours:"+ceb.getHours());
		}

		// At least 6 token
		if (numTokens < 6) {
			 log.debug("错误321");
				return null; 
		}
      }catch(Exception e){
    	  log.debug("错误212");
  		return null; 
      }
		return ceb;
	}

	/** 
	 * Parses a string describing this time table entry
	 * @return String describing the time table entry usuarlly something like:
	 * * * * * * org.loushang.commons.schedule.jcrontab
	 * @throws CrontabEntryException Error parsing the string
	 */
	public String unmarshall(CrontabEntryBean ceb) throws CrontabEntryException {
		final StringBuffer sb = new StringBuffer();
		sb.append(ceb.getMinutes() + " ");
		sb.append(ceb.getHours() + " ");
		sb.append(ceb.getDaysOfMonth() + " ");
		sb.append(ceb.getMonths() + " ");
		sb.append(ceb.getDaysOfWeek() + " ");
		if ("".equals(ceb.getMethodName())) {
			sb.append(ceb.getClassName());
		} else {
			sb.append(ceb.getClassName() + "@" + ceb.getMethodName());
		}
		sb.append("(" + ceb.getState() + ")");
		sb.append("#" + ceb.getName() + "# ");

		String[] extraInfo = ceb.getExtraInfo();
		if (extraInfo != null) {
			for (int i = 0; i < extraInfo.length; i++) {
				sb.append(extraInfo[i] + " ");
			}
		}
		return sb.toString();

	}
	/** 
		 * Parses a string describing this time table entry
		 * @return String describing the time table entry usuarlly something like:
		 * * * * * * org.loushang.commons.schedule.jcrontab
		 * @throws CrontabEntryException Error parsing the string
		 */
//	public String format(CrontabEntryBean ceb) throws Exception {
//		CrontabView view = (CrontabView) Crontab.getInstance().getViewClass().newInstance();
//		return view.convert(ceb);
//	}

	/** 
	 * Parses a token and fills the array of booleans that represents this 
	 * CrontabEntryBean
	 * @param token String to parser usually smth like [ * , 2-3 , 2,3,4 ,4/5 ]
	 * @param arrayBool this array is the most efficient way to compare entries
	 * @param bBeginInOne says if the array begins in 0 or in 1  
	 * @throws CrontabEntryException Error parsing the string
	 */

	public void parseToken(String token, boolean[] arrayBool, boolean bBeginInOne) throws CrontabEntryException {
		// This line initializes all the array of booleans instead of doing so
		// in the CrontabEntryBean Constructor.               
		// for (int i = 0; i < arrayBool.length ; i++) arrayBool[i]=false;
		log.debug("parseToken令牌 剖析===="+token);
	//	log.debug("");
		int i;
		int index;
		int each = 1;
		try {
			// Look for step first
			index = token.indexOf("/");
			if (index > 0) {
				each = Integer.parseInt(token.substring(index + 1));
				token = token.substring(0, index);
			}

			if (token.equals("*")) {
				for (i = 0; i < arrayBool.length; i += each) {
					arrayBool[i] = true;
				}
				return;
			}

			index = token.indexOf(",");
			if (index > 0) {
				StringTokenizer tokenizer = new StringTokenizer(token, ",");
				while (tokenizer.hasMoreTokens()) {
					parseToken(tokenizer.nextToken(), arrayBool, bBeginInOne);
				}
				return;
			}

			index = token.indexOf("-");
			if (index > 0) {
				int start = Integer.parseInt(token.substring(0, index));
				int end = Integer.parseInt(token.substring(index + 1));

				if (bBeginInOne) {
					start--;
					end--;
				}

				for (int j = start; j <= end; j += each)
					arrayBool[j] = true;
				return;
			}

			int iValue = Integer.parseInt(token);
			if (bBeginInOne) {
				iValue--;
			}
			arrayBool[iValue] = true;
			log.debug("解析后的值："+arrayBool);
			return;
		} catch (Exception e) {
			throw new CrontabEntryException("Smth was wrong with " + token);
		}
	}
}
