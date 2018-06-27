package com.lambo.schedule.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Properties;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import com.lambo.schedule.Crontab;
import com.lambo.schedule.data.FileLoaderUtils;
import com.lambo.schedule.jdbc.DataSourceFactory;
import com.lambo.schedule.util.ResourceBundle;


public class LoadCrontabServlet extends HttpServlet
{
  private static final long serialVersionUID = 1L;
  //String start = System.getProperty("schedule.start");
  String start="true";
  static Logger log = Logger.getLogger(LoadCrontabServlet.class.getName());
  private Crontab crontab = null;

  private ResourceBundle resourceBundle = ResourceBundle.getBundle("resource-schedule");
  @Override
  public void init(ServletConfig config)
    throws ServletException
  {
    super.init(config);

    if (this.start == null) {this.start = "true";}
    try
    {
      log.debug("LoadCrontabServlet.Schedule initing?...");
      process();
      log.debug("LoadCrontabServlet.Schedule init OK!");
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }

  protected InputStream createPropertiesStream(String name)
    throws IOException
  {
    return getClass().getResourceAsStream(name);
  }

  public void process()
  {
    String propz = "schedule.properties";
    String props = getServletConfig().getInitParameter("PROPERTIES_FILE");
    if (props == null) {
      log.debug("LoadCrontabServlet.process.schedule.properties config is null!");
      props = propz;
    }
    if (!props.startsWith("/")) {
      props = "/" + props;
    }
    log.debug("LoadCrontabServlet.Propertie file name : " + props);

    Properties propObj = new Properties();
    try
    {
      InputStream input = createPropertiesStream(props);
      propObj.load(input);
    }
    catch (IOException e) {
      try {
        InputStream input = FileLoaderUtils.getResourceAsStream(props);
        if (input == null) {
          throw new IOException("File '" + props + "' not found");
        }
        propObj.load(input);
      } catch (IOException ioe) {
        log.error(ioe.toString(), ioe);
      }
    }

    ServletConfig c = getServletConfig();
    Enumeration keys = c.getInitParameterNames();
    while (keys.hasMoreElements()) {
      String key = (String)keys.nextElement();
      propObj.setProperty(key, c.getInitParameter(key));
    }


    String springDatasourceBeanId = propObj.getProperty("lambo.schedule.jdbc.springDatasource.beanId");

    if ((springDatasourceBeanId != null) && (!"".equals(springDatasourceBeanId))) {

      DataSourceFactory.newInstance(springDatasourceBeanId).setServletContext(getServletContext());
    }
    else {

      DataSourceFactory.newInstance().setServletContext(getServletContext());
    }

    if ("true".equals(this.start)) {

      this.crontab = Crontab.getInstance();
      try
      {
        ShutdownHook();
        this.crontab.init(propObj);
      } catch (Exception e) {
        log.error(e.toString(), e);
      }
    }else{
    	 log.debug("LoadCrontabServlet.process.Schedule stop");
    }
  }
@Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    response.setContentType("text/html;charset=gb2312");

    PrintWriter out = response.getWriter();

    out.println("<html><head><title>LoadCrontabServlet</title></head><body>");
    out.println(this.resourceBundle.getString("LoadCrontabServlet.doGet_1"));
    out.println(this.resourceBundle.getString("LoadCrontabServlet.doGet_2"));
    if (Crontab.getInstance().isStarted())
    {out.println(this.resourceBundle.getString("LoadCrontabServlet.doGet_3"));}
    else {
      out.println(this.resourceBundle.getString("LoadCrontabServlet.doGet_4"));
    }
    out.println("</body></html>");
  }

  public void ShutdownHook()
    throws Exception
  {
    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        LoadCrontabServlet.this.doStop();
      } } );
  }
@Override
  public void destroy() {
    doStop();
  }

  public void doStop() {
    log.info("Shutting down...");

    this.crontab.uninit();
    log.info("Stoped");
  }
}