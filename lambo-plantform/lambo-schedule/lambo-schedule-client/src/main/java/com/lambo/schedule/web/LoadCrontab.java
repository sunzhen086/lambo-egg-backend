package com.lambo.schedule.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.lambo.schedule.Crontab;
import com.lambo.schedule.data.FileLoaderUtils;
import com.lambo.schedule.jdbc.DataSourceFactory;



/**
 * @Description: 定时器启动
 *
 * @ClassName: LoadCrontab
 */
public class LoadCrontab {
  private static Log log = LogFactory.getLog(LoadCrontab.class);
  public static void init(){
    String start = System.getProperty("schedule.start");
    if (start == null) {start = "true";}
    try
    {
      log.debug("LoadCrontabServlet.Schedule initing?...");
      process();
      log.debug("LoadCrontabServlet.Schedule init OK!");
    } catch (Exception e) {
      log.debug("",e);
    }
  }

  public static void process()
  {
    String props = "schedule.properties";
    if (!props.startsWith("/")) {
      props = "/" + props;
    }
    Properties propObj = new Properties();
    try {
      InputStream input = FileLoaderUtils.getResourceAsStream(props);
      if (input == null) {
        throw new IOException("File '" + props + "' not found");
      }
      propObj.load(input);
    } catch (IOException ioe) {
      log.error(ioe.toString(), ioe);
    }
    DataSourceFactory.newInstance();
    Crontab  crontab = Crontab.getInstance();
    try
    {
      crontab.init(propObj);
    } catch (Exception e) {
      log.error(e.toString(), e);
    }

  }

}
