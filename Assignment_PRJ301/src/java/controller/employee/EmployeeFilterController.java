/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.employee;

import controller.auth.BaseRBACController;
import dal.DepartmentDBContext;
import dal.EmployeeDBContext;
import dal.SalaryDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import model.Department;
import model.Employee;
import model.Salaries;
import model.auth.User;

/**
 *
 * @author lenovo
 */
public class EmployeeFilterController extends BaseRBACController {

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
        // Lấy các tham số từ request
        String raw_eid = request.getParameter("eid");
        String raw_ename = request.getParameter("ename");
        String raw_gender = request.getParameter("gender");
        String raw_from = request.getParameter("from");
        String raw_to = request.getParameter("to");
        String raw_address = request.getParameter("address");
        String raw_did = request.getParameter("did");
        String raw_phonenumber = request.getParameter("phonenumber");
        String raw_sid = request.getParameter("sid");



        // Xử lý dữ liệu từ request, bao gồm việc chuyển đổi các giá trị null, blank
        Integer eid = (raw_eid != null) && (!raw_eid.isBlank())
                ? Integer.parseInt(raw_eid) : null;
        String ename = raw_ename;
        Boolean gender = (raw_gender != null) && (!raw_gender.equals("both"))
                ? raw_gender.equals("male") : null;
        Date from = (raw_from != null) && (!raw_from.isBlank())
                ? Date.valueOf(raw_from) : null;
        Date to = (raw_to != null) && (!raw_to.isBlank())
                ? Date.valueOf(raw_to) : null;
        String address = raw_address;
        Integer did = (raw_did != null) && (!raw_did.equals("-1"))
                ? Integer.parseInt(raw_did) : null;
        String phonenumber = raw_phonenumber;
        Integer sid = (raw_sid != null) && (!raw_sid.equals("-1"))
                ? Integer.parseInt(raw_sid) : null;

        EmployeeDBContext dbEmp = new EmployeeDBContext();
        ArrayList<Employee> emps = dbEmp.search(eid, ename, gender, from, to, address, did, phonenumber, sid);

        DepartmentDBContext dbDept = new DepartmentDBContext();
        ArrayList<Department> depts = dbDept.list();

        SalaryDBContext dbSals = new SalaryDBContext();
        ArrayList<Salaries> sals = dbSals.list();

        request.setAttribute("depts", depts);
        request.setAttribute("emps", emps);
        request.setAttribute("sals", sals);

        request.getRequestDispatcher("../view/employee/filter.jsp").forward(request, response);

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
