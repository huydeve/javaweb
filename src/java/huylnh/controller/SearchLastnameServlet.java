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
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HuyDev
 */
@WebServlet(name = "SearchLastnameServlet", urlPatterns = {"/SearchLastnameServlet"})
public class SearchLastnameServlet extends HttpServlet {

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
        String url = MyApplicationConstants.SearchFeatures.SEARCH_PAGE_WELCOME;
        String searchValue = request.getParameter("txtSearchValue");
        try {
            if (!searchValue.trim().isEmpty()) {
                //Call DAO
                HttpSession session = request.getSession(false);
                if (session != null) {
                    String username = (String) session.getAttribute("USERNAME");
                    boolean role = (boolean) session.getAttribute("ROLE");
                    RegistrationDAO dao = new RegistrationDAO();
                    dao.searchLastName(searchValue, username);
                    List<RegistrationDTO> result = dao.getAccount();
                    if (!role) {
                        url = MyApplicationConstants.SearchFeatures.SEARCH_PAGE_USER;
                        request.setAttribute("SEARCH_RESULT", result);
                    } else {
                        url = MyApplicationConstants.SearchFeatures.SEARCH_PAGE_ADMIN;
                        request.setAttribute("SEARCH_RESULT", result);
                    }

                }
            }//end searchValue has inputted valid value 
        } catch (SQLException ex) {
            log("Error SQLException_SearchLastName " + ex.getMessage());
        } catch (NamingException ex) {
            log("Error NamingException_SearchLastName " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
