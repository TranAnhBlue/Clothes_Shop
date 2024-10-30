/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.productionplan;

import controller.auth.BaseRBACController;
import dal.DepartmentDBContext;
import dal.ProductionPlanDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import model.Department;
import model.ProductionPlan;
import model.auth.User;

/**
 *
 * @author lenovo
 */
public class ProductionPlanFilterController extends BaseRBACController {

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
        // lấy các tham số từ request
        String raw_plid = request.getParameter("id");
        String raw_plname = request.getParameter("name");
        String raw_start = request.getParameter("start");
        String raw_end = request.getParameter("end");
        String raw_did = request.getParameter("did");

        // xử lý dữ liệu từ request, bao gồm chuyển đổi các giá trị null, blank
        Integer plid = (raw_plid != null) && (!raw_plid.isBlank())
                ? Integer.parseInt(raw_plid) : null;
        String plname = raw_plname;
        Date start = (raw_start != null) && (!raw_start.isBlank())
                ? Date.valueOf(raw_start) : null;
        Date end = (raw_end != null) && (!raw_end.isBlank())
                ? Date.valueOf(raw_end) : null;
        Integer did = (raw_did != null) && (!raw_did.equals("-1"))
                ? Integer.parseInt(raw_did) : null;

        ProductionPlanDBContext dbPplan = new ProductionPlanDBContext();
        ArrayList<ProductionPlan> pps = dbPplan.search(plid, plname, start, end, did);

        DepartmentDBContext dbDept = new DepartmentDBContext();
        request.setAttribute("depts", dbDept.get("Production"));
        
        request.setAttribute("pps", pps);

        request.getRequestDispatcher("../view/productionplan/filter.jsp").forward(request, response);

    }

    @Override
    protected void doAuthorizedGet(HttpServletRequest request, HttpServletResponse response, User account)
            throws ServletException, IOException {
        processRequest(request, response, account);
    }

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
