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
import java.util.ArrayList;
import model.Department;
import model.Employee;
import model.Salaries;
import model.auth.User;

/**
 *
 * @author lenovo
 */
public class EmployeeListController extends BaseRBACController {
   
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        EmployeeDBContext dbEmp = new EmployeeDBContext();
        ArrayList<Employee> emps = dbEmp.list();
        req.setAttribute("emps", emps);
        
        DepartmentDBContext dbDept = new DepartmentDBContext();
        ArrayList<Department> depts = dbDept.list();
        req.setAttribute("depts", depts);
        
        SalaryDBContext dbSal = new SalaryDBContext();
        ArrayList<Salaries> sals = dbSal.list();
        req.setAttribute("sals", sals);
//        int employeeCount = db.getEmployeeCount();
//        req.setAttribute("employeeCount", employeeCount);  // Đặt tổng số nhân viên vào request attribute
        req.getRequestDispatcher("../view/employee/list.jsp").forward(req, resp);
         
    }
    @Override
    protected void doAuthorizedGet(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        processRequest(req, resp, account);
    }

    @Override
    protected void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        processRequest(req, resp, account);
    }
    
}