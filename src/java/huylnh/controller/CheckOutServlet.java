/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package huylnh.controller;

import huylnh.cart.CartObject;
import huylnh.orderdetail.OrderDetailDAO;
import huylnh.product.ProductDAO;
import huylnh.product.ProductDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
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
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {


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
   
        try {
            //Call DAO
            boolean checkout = true;
            String productId = null;
            HttpSession session = request.getSession(false);
            if (session != null) {
                //2. customer takes his/her cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    //3. Customer takes items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        //4 Get all selected Items
                        String[] insertOrder = request.getParameterValues("chkItems");
                        if (insertOrder != null) {
                            //call DAO Order Detail for convert to string
                            OrderDetailDAO dao = new OrderDetailDAO();
                            String username = (String) session.getAttribute("USERNAME");
                            String OrderItems = "";
                            for (String itemId : insertOrder) {
                                //Call DAO Product for check quantity product
                                ProductDAO daoProduct = new ProductDAO();
                                //Call DTO Product to get quantity
                                ProductDTO dtoProduct = daoProduct.getProductById(itemId);
                                if (dtoProduct.getQuantity() - items.get(itemId) >= 0) {
                                    OrderItems += itemId + "=" + items.get(itemId) + ",";
                                }//end if Product quantity - OrderDetail quantity >=0 
                                else {
                                    productId = itemId;
                                    checkout = false;
                                    break;
                                }
                            }

                            if (checkout) {
                                boolean result = dao.CheckOutOrder(OrderItems, username);
                                //remove items from cart
                                if (result) {
                                    for (String itemId : insertOrder) {
                                        cart.removeItemFromCart(itemId);
                                    }
                                    session.setAttribute("ERROR_VIEW", "Check Out Success!!");
                                }
                            } else {
                                session.setAttribute("ERROR_VIEW", productId + " not have enough for check out !!");
                            }
                            session.setAttribute("CART", cart);
                        }//end removedItems had choices
                    }//end items have existed
                }//end if cart has existed
            }//session has existed
        } catch (SQLException ex) {
            log("Error SQLException_CheckOutServlet " + ex.getMessage());
        } catch (NamingException ex) {
            log("Error NamingException_CheckOutServlet " + ex.getMessage());
        } finally {
            //6. refresh - call view cart again 
            String urlRewriting = "viewCart";
            response.sendRedirect(urlRewriting);
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
