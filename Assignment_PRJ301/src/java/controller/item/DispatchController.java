/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.item;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author lenovo
 */
@WebServlet(name="DispatchController", urlPatterns={"/DispatchController"})
public class DispatchController extends HttpServlet {
    private final String LOGIN_PAGE = "login.jsp";
    private final String NULL_CONTROLLER = "NullServlet";
    private final String ADD_ITEM_CONTROLLER = "AddItemServlet";
    private final String VIEW_ITEM_PAGE = "viewCart.jsp";
    private final String DELETE_ITEM_CONTROLLER = "DeleteItemServlet";
    private final String CREATE_NEW_ACCOUNT = "CreateNewAccount";
    /*
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
        String url = ADD_ITEM_CONTROLLER;
        //Which button did user click?
        String button = request.getParameter("btAction");
        
        try {
            if(button == null){
                url = NULL_CONTROLLER;        
            }
            else if (button.equals("Add product to your Cart")){
                url = ADD_ITEM_CONTROLLER;
            }
            else if (button.equals("View your Cart")){
                url = VIEW_ITEM_PAGE;
            }
            else if (button.equals("Remove selected Items")){
                url = DELETE_ITEM_CONTROLLER;
            }
            else if (button.equals("Create new Account")){
                url = CREATE_NEW_ACCOUNT;
            }
        }
        finally{
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