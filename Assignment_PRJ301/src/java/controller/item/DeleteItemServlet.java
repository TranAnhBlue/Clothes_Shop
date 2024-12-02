/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.item;

import controller.auth.BaseRBACController;
import controller.session.CartObj;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.auth.User;

/**
 *
 * @author lenovo
 */
@WebServlet(name="DeleteItemServlet", urlPatterns={"/DeleteItemServlet"})
public class DeleteItemServlet extends BaseRBACController {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, User account)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try{
            //toi cho lay gio
            HttpSession session = request.getSession(false);
            if(session != null){
                //lay gio hang
                CartObj cart = (CartObj)session.getAttribute("CART");
                //lay ra cai cu
                if(cart != null){
                    String[] items = request.getParameterValues("chkItem");
                    if(items != null){
                        for(String item : items){
                            cart.removeItemFromCart(item);
                        }// end for travesing items
                        //thiet lap lai
                        session.setAttribute("CART", cart);
                    }//end if items
                }//end if cart
            }//end if session
            String urlRewriting = "DispatchController?btAction=View your Cart";
            response.sendRedirect(urlRewriting);
        }
        finally{
        
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
    protected void doAuthorizedGet(HttpServletRequest request, HttpServletResponse response, User account)
            throws ServletException, IOException {
        processRequest(request, response, account);
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
    protected void doAuthorizedPost(HttpServletRequest request, HttpServletResponse response, User account)
            throws ServletException, IOException {
        processRequest(request, response, account);
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

