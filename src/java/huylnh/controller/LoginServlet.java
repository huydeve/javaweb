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

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;

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
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

//    static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
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
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String url = MyApplicationConstants.LoginFeatures.LOGIN_ERROR_PAGE;
        boolean result = false;
        try {
            //call DAO
            RegistrationDAO dao = new RegistrationDAO();
            if (username.isEmpty()) {
                request.setAttribute("ERROR", "Username must be filled out");
            } else if (password.isEmpty()) {
                request.setAttribute("ERROR", "Password must be filled out");
            } else {
                result = dao.checkLogin(username, password);
                if (result) {
                    HttpSession session = request.getSession();
                    //get lastname by username to show in homepage

                    RegistrationDTO dto = dao.SearchOneUsername(username);
                    session.setAttribute("USERNAME", username);
                    session.setAttribute("LASTNAME", dto.getLastname());
                    session.setAttribute("ROLE", dto.isRole());

                    Cookie cookie = new Cookie(username, password);
                    cookie.setMaxAge(60 * 30);
                    response.addCookie(cookie);

                    url = MyApplicationConstants.HomeFeatures.HOME_PAGE_CONTROLLER;
                    log("Login success!");
                } else {
                    request.setAttribute("ERROR", "Invalid Username or Password");
                }
            }
        } catch (SQLException ex) {
            log("SQLException_LoginServlet " + ex.getMessage());
        } catch (NamingException ex) {
            log("NamingException_LoginServlet " + ex.getMessage());
        } finally {
            if (result) {
                response.sendRedirect(url);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }
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
