/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.productionplan;

import controller.auth.BaseRBACController;
import dal.DepartmentDBContext;
import dal.ProductDBContext;
import dal.ProductionPlanDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Department;
import model.ProductionPlan;
import java.sql.Date;
import model.auth.User;

/**
 *
 * @author lenovo
 */
public class ProductionPlanUpdateController extends BaseRBACController {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductionPlanUpdateController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductionPlanUpdateController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        DepartmentDBContext dbDept = new DepartmentDBContext();
        ProductionPlanDBContext dbPplan = new ProductionPlanDBContext();

        request.setAttribute("depts", dbDept.get("Production"));
        int planId = Integer.parseInt(request.getParameter("plid"));

        // Fetch the production plan from the database
        ProductionPlanDBContext dbContext = new ProductionPlanDBContext();
        ProductionPlan plan = dbContext.get(planId);
        request.setAttribute("plan", plan);

        request.getRequestDispatcher("../view/productionplan/update.jsp").forward(request, response);
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
        int id = Integer.parseInt(request.getParameter("plid"));
        String name = request.getParameter("plname");
        String start = request.getParameter("startdate");
        String end = request.getParameter("enddate");
        String raw_did = request.getParameter("did");
//        int departmentId = Integer.parseInt(request.getParameter("did"));

        // Create the ProductionPlan object
        ProductionPlan plan = new ProductionPlan();
        plan.setId(id);
        plan.setName(name);
        plan.setStart(Date.valueOf(start)); // Assuming start is in the format YYYY-MM-DD
        plan.setEnd(Date.valueOf(end));

        Department d = new Department();
        d.setId(Integer.parseInt(raw_did));
        plan.setDept(d);

        // Assuming you have a method to fetch the department object
        // Department department = ... // Fetch the department by ID
        // plan.setDept(department); // Set the department
        // Update the production plan in the database
        ProductionPlanDBContext dbContext = new ProductionPlanDBContext();
        dbContext.update(plan);

        // Redirect to the list page
        response.sendRedirect("../productionplan/list"); // Assuming you have a list endpoint for production plans
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
