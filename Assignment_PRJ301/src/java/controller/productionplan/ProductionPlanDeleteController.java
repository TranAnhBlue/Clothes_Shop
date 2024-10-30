/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.productionplan;

import controller.auth.BaseRBACController;
import dal.ProductionPlanDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProductionPlan;
import model.auth.User;

/**
 *
 * @author lenovo
 */
public class ProductionPlanDeleteController extends BaseRBACController {
   
    @Override
    protected void doAuthorizedGet(HttpServletRequest request, HttpServletResponse response, User account)
    throws ServletException, IOException {
        response.sendError(403,"You are not allowed to access this feature by this way!");
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doAuthorizedPost(HttpServletRequest request, HttpServletResponse response, User account)
    throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ProductionPlanDBContext db = new ProductionPlanDBContext();
        ProductionPlan pp = new ProductionPlan();
        pp.setId(id);
        db.delete(pp);
        response.sendRedirect("list");
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
