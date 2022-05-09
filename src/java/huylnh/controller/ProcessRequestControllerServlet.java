/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package huylnh.controller;

import huylnh.registration.RegistrationDAO;
import huylnh.registration.RegistrationDTO;
import huylnh.ulti.MyApplicationConstants;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HuyDev
 */
@WebServlet(name = "ProcessRequestControllerServlet", urlPatterns = {"/ProcessRequestControllerServlet"})
public class ProcessRequestControllerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //login.jsp
        String url = MyApplicationConstants.LoginFeatures.LOGIN_PAGE;

        try {
            //1. Get cookies from request
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                //2.Traverse all cookies to check authentication
                for (Cookie cookie : cookies) {
                    //3.Get username and password from name, value
                    String username = cookie.getName();
                    String password = cookie.getValue();
                    //4. call DAO to check authentication
                    RegistrationDAO dao = new RegistrationDAO();
                    boolean result = dao.checkLogin(username, password);
                    if (result) {
                        url = MyApplicationConstants.HomeFeatures.HOME_PAGE_CONTROLLER;
                        HttpSession session = request.getSession();
                        RegistrationDTO dto = dao.SearchOneUsername(username);
                        session.setAttribute("USERNAME", username);
                        session.setAttribute("LASTNAME", dto.getLastname());
                        session.setAttribute("ROLE", dto.isRole());
                        break;
                    }//end authentication is successfully checked
                }//end for traverse cookies
            }//end cookies is existed 
        } catch (SQLException ex) {
            log("Error SQLException_ProcessRequest " + ex.getMessage());
        } catch (NamingException ex) {
            log("Error NamingException_ProcessRequest " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
