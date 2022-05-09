/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/ServletListener.java to edit this template
 */
package huylnh.listener;

import huylnh.ulti.DBHelper;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author HuyDev
 */
public class MyServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //get context Scope
        ServletContext context = sce.getServletContext();
        //Store site maps
        try {
            DBHelper.getSiteMaps(context);
            DBHelper.getAuthentication(context);

        } catch (IOException ex) {
            context.log("MyServletListener_IO " + ex.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
//        ServletContext context = sce.getServletContext();
//        context.removeAttribute("SITE_MAP");
//        context.removeAttribute("AUTHENTICATION_LIST");
    }
}
