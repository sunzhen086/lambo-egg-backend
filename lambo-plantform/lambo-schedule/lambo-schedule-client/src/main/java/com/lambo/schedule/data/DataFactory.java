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

import org.apache.log4j.Logger;
import com.lambo.schedule.PropertyMgr;

/**
 * This Factory builds a dao using teh given information.
 * Initializes the system with the given properties or 
 * loads the default config
 * @author $Author: jiadx $
 * @version $Revision: 2875 $
 */

public class DataFactory {
	static Logger Log = Logger.getLogger(DataFactory.class.getName());

	private static DataFactory instance;

	private static DataSource dao = null;

	/**
	 *	Default Constructor private
	 */
	private DataFactory() {
		if (dao == null) {
			try {
				Log.debug("看是哪个数据连接方式并且实例化");
				dao = ((DataSource) Class.forName(PropertyMgr.getDataSource()).newInstance()).getInstance();
			} catch (Exception e) {
				Log.error(e.toString(), e);
				throw new RuntimeException("Please check file 'schedule.properties',[loushang.schedule.data.datasource="+PropertyMgr.getDataSource()+ "] is ERROR! "+e);
			}
		}
	}
	/** 
	 * This method returns the DataFactory of the System This method
	 * grants the Singleton pattern
	 * @return DataSource I have a lot of doubts about how this method 
	 * is done.
		 */
	public synchronized static DataFactory getInstance() {
		if (instance == null) {
			instance = new DataFactory();
		}
		return instance;
	}

	/** 
	 * This method returns the DataSource of the System
	 * @return DataSource I have a lot of doubts about how this method 
	 * is done.
		 */

	public static DataSource getDAO() {
		return dao;
	}
}
