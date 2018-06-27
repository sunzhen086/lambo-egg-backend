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

/**
 * This interface says which methods a DataSource should have in
 * order to be compatible with the DataFactory
 * @author $Author: jiadx $
 * @version $Revision: 2873 $
 */

public interface DataSource {
	/**
	 *	returns the only valid DataSource of this kind
	 * @return DataSource
	 */
	abstract DataSource getInstance();
	/**
	 *	searches  the CrontabEntryBean from the DataSource
	 * @return CrontabEntryBean
	 * @throws Exception
	 */
	abstract CrontabEntryBean find(CrontabEntryBean ceb) throws Exception;
	abstract CrontabEntryBean findByName(String task_name) throws Exception;
	/**
	 *	Gets all the CrontabEntryBean from the DataSource
	 * @return CrontabEntryBean[]
	 * @throws Exception
	 */
	abstract CrontabEntryBean[] findAll() throws Exception;
	/**
	 *	stores CrontabEntryBean in  the DataSource
	 * @param CrontabEntryBean
	 * @throws Exception
	 */
	abstract void store(CrontabEntryBean ceb) throws Exception, DataNotFoundException;
	/**
	 *	stores CrontabEntryBean in  the DataSource
	 * @param CrontabEntryBean list
	 * @throws Exception
	 */
	abstract void store(CrontabEntryBean[] ceb) throws Exception, DataNotFoundException;
	/**
	 * removes CrontabEntryBean from the DataSource
	 * @param CrontabEntryBean
	 * @throws Exception
	 */
	abstract void remove(String name) throws Exception;
	
	
	abstract void loadAllCrontabEntry() throws Exception ;
	
	abstract String getType();
}
