/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package huylnh.controller;

import huylnh.registration.RegistrationCreateError;
import huylnh.registration.RegistrationDAO;
import huylnh.registration.RegistrationDTO;
import huylnh.ulti.MyApplicationConstants;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HuyDev
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {

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
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");

        RegistrationCreateError errors = new RegistrationCreateError();
        boolean foundErrs = false;
        
        String url = MyApplicationConstants.AccountFeature.CREATE_ACCOUNT_ERROR;
        try {
            //1. Check all user's error
            if (username.trim().length() < 6 ||
                     username.trim().length() > 20) {
                foundErrs = true;
                errors.setUsernameLengthErr("Username iss required with 6 to 20 charaters");
            }

            if (password.trim().length() < 6 || 
                    password.trim().length() > 30) {
                foundErrs = true;
                errors.setPasswordLengthErr("Passowrd is required with 6 to 30 charaters");
            } else if (!confirm.trim().equals(password.trim())) {
                foundErrs = true;
                errors.setConfirmNotMatched("Confirm is not matched");
            }

            if (fullname.trim().length() < 2 ||
                     fullname.trim().length() > 50) {
                foundErrs = true;
                errors.setFullNameLengthErr("Fullname is required with 2 to 50 charaters");
            }
            //2. process 
            if(foundErrs) {
                //2.1 forward errors to user
                request.setAttribute("INSERT_ERROR", errors );
            }else {
                //.Insert to db
                RegistrationDTO dto = 
                        new RegistrationDTO(username, password, fullname, false);
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.createAccount(dto);
                
                if (result) {
                    //. transfer log 
                    url = MyApplicationConstants.LoginFeatures.LOGIN_PAGE;
                }//end account is created
            }
        }catch (SQLException ex) {
            String msg = ex.getMessage();
            log("CreateNewAccountServlet_SQL " + msg);
            if(msg.contains("duplicate")) {
                errors.setUsernameIsExited(username + " existed!!");
                request.setAttribute("INSERT_ERROR", errors);
            }
        }catch (NamingException ex){
            log("CreateNewAccountServlet_Naming " + ex.getMessage());
        }finally {
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
