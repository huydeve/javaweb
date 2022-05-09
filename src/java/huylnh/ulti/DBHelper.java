/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package huylnh.ulti;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

/**
 *
 * @author HuyDev
 */
public class DBHelper implements Serializable{
    public static Connection makeConnection() 
            throws NamingException, SQLException {
        //1.use JNDI get current system file 
        Context context = new InitialContext();
        //2. từ context này get context của container
        Context tomContext = (Context)context.lookup("java:comp/env");
        //3.get datasoucre
        DataSource ds = (DataSource)tomContext.lookup("DSBlink");
        //4.get Connection
        Connection con = ds.getConnection();
        return con;
    }
    
    
    public static void getSiteMaps(ServletContext context) throws IOException {
        //get site  Map file
        String siteMapFilePath = context.getInitParameter("SITE_MAPS_FILE_PATH");
        Properties properties = new Properties();
        InputStream is = null;
        is = context.getResourceAsStream(siteMapFilePath);
        properties.load(is);
        
        context.setAttribute("SITE_MAP", properties);
    }
    
      public static void getAuthentication(ServletContext context) throws IOException {
        //get site  Map file
        String AuthenticationFilePath = context.getInitParameter("AUTHENTICATION_PROPERTIES_FILE_LOCATION");
        Properties properties = new Properties();
        InputStream is = null;
        is = context.getResourceAsStream(AuthenticationFilePath);
        properties.load(is);
        
        context.setAttribute("AUTHENTICATION_LIST", properties);
    }
      
     
}
