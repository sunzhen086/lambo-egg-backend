/**
 *  This file is part of the jcrontab package
 *  Copyright (C) 2001-2003 Sergey V. Oudaltsov
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
 *  svu@users.sourceforge.net
 *
 */

package com.lambo.schedule.data;

import java.io.InputStream;
import java.net.URL;

/**
 * This class Is the implementation of DataSource to access 
 * Info in a FileSystem
 * @author $Author: caoxm $
 * @version $Revision: 2867 $
 */
public class FileLoaderUtils {

	public static InputStream getResourceAsStream(String name) {
		if (name.startsWith("/")) {
			name = name.substring(1);
		}
		ClassLoader ccl = Thread.currentThread().getContextClassLoader();
		return ccl.getResourceAsStream(name);
	}
	public static URL getResource(String name) {
		if (name.startsWith("/")) {
			name = name.substring(1);
		}
		ClassLoader ccl = Thread.currentThread().getContextClassLoader();
		return ccl.getResource(name);
	}
	
}
