/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.employee;

import controller.auth.BaseRBACController;
import dal.DepartmentDBContext;
import dal.EmployeeDBContext;
import dal.SalaryDBContext;
import dal.ShiftDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import model.Department;
import model.Employee;
import java.util.*;
import model.Salaries;
import model.auth.User;

/**
 *
 * @author sonnt-local hand-some
 */
public class EmployeeCreateController extends BaseRBACController {

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
        ArrayList<Department> depts = dbDept.list();
        request.setAttribute("depts", depts);
        SalaryDBContext dbSal = new SalaryDBContext();
        ArrayList<Salaries> sals = dbSal.list();
        request.setAttribute("sals", sals);
        EmployeeDBContext dbEmp = new EmployeeDBContext();
        request.setAttribute("emps", dbEmp);
        request.getRequestDispatcher("../view/employee/create.jsp").forward(request, response);
    }

    @Override
    protected void doAuthorizedPost(HttpServletRequest request, HttpServletResponse response, User account)
            throws ServletException, IOException {
        // Đọc các tham số từ request
        String raw_ename = request.getParameter("ename");
        String raw_gender = request.getParameter("gender");
        String raw_dob = request.getParameter("dob");
        String raw_address = request.getParameter("address");
        String raw_sid = request.getParameter("sid");
        String raw_did = request.getParameter("did");
        String raw_phonenumber = request.getParameter("phonenumber");

        // Gán dữ liệu và parse
        Employee e = new Employee();
        e.setName(raw_ename);
        e.setDob(Date.valueOf(raw_dob));  // Đặt giá trị ngày sinh sau khi kiểm tra hợp lệ
        // Xử lý ngày sinh, kiểm tra giá trị hợp lệ
        if (raw_dob != null && !raw_dob.isEmpty()) {
            e.setDob(Date.valueOf(raw_dob));  // Đặt giá trị ngày sinh sau khi kiểm tra hợp lệ
        } else {
            response.getWriter().println("Date of birth is required.");
            return;
        }
        e.setGender(raw_gender.equals("male"));
        e.setAddress(raw_address);
        e.setPhonenumber(raw_phonenumber);
         
        // Parse department ID
        Department d = new Department();
        d.setId(Integer.parseInt(raw_did));
        e.setDept(d);

        Salaries s = new Salaries();
        s.setId(Integer.parseInt(raw_sid));
        e.setSals(s);
        
        // Lưu dữ liệu
        EmployeeDBContext db = new EmployeeDBContext();
        db.insert(e);

        // Phản hồi tới người dùng
        response.getWriter().println("Employee has been added successfully! ID: " + e.getId());
        response.sendRedirect("list");
    }
}
