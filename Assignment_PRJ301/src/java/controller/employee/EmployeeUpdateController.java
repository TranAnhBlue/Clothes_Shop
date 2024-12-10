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
import model.Department;
import model.Employee;
import model.Salaries;
import model.auth.User;

/**
 *
 * @author lenovo
 */
public class EmployeeUpdateController extends BaseRBACController {

    @Override
    protected void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        String field = req.getParameter("field");
        String value = req.getParameter("value");

        try {
            int id = Integer.parseInt(idParam);
            EmployeeDBContext db = new EmployeeDBContext();
            Employee employee = db.get(id);

            if (employee == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("Employee not found");
                return;
            }

            switch (field) {
                case "name":
                    employee.setName(value);
                    break;
                case "gender":
                    employee.setGender("male".equalsIgnoreCase(value));
                    break;
                case "dob":
                    employee.setDob(Date.valueOf(value));
                    break;
                case "phonenumber":
                    employee.setPhonenumber(value);
                    break;
                case "address":
                    employee.setAddress(value);
                    break;
                case "sid":
                    employee.getSals().setId(Integer.parseInt(value));
                    break;
                case "did":
                    employee.getDept().setId(Integer.parseInt(value));
                    break;
                default:
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    resp.getWriter().write("Invalid field name");
                    return;
            }

            db.update(employee);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Update successful");

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Error updating employee");
            e.printStackTrace();
        }
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        // Lấy tất cả tham số từ request
//        Enumeration<String> parameterNames = req.getParameterNames();
//        EmployeeDBContext db = new EmployeeDBContext();
//
//        while (parameterNames.hasMoreElements()) {
//            String paramName = parameterNames.nextElement();
//
//            // Kiểm tra xem tham số có phải là id của nhân viên không
//            if (paramName.startsWith("id_")) {
//                String idStr = paramName.substring(3);
//                int id = Integer.parseInt(idStr);
//
//                // Tìm nhân viên theo id
//                Employee e = db.get(id);
//
//                // Cập nhật các trường thông tin
//                e.setName(req.getParameter("name_" + id));
//                e.setGender(req.getParameter("gender_" + id).equals("male"));
//                e.setDob(Date.valueOf(req.getParameter("dob_" + id)));
//                e.setPhonenumber(req.getParameter("phonenumber_" + id));
//                e.setAddress(req.getParameter("address_" + id));
//
//                Department dept = new Department();
//                dept.setId(Integer.parseInt(req.getParameter("did_" + id)));
//                e.setDept(dept);
//
//                Salaries sal = new Salaries();
//                sal.setId(Integer.parseInt(req.getParameter("sid_" + id)));
//                e.setSals(sal);
//
//                // Cập nhật vào cơ sở dữ liệu
//                db.update(e);
//            }
//        }
//
//        // Gửi phản hồi thành công
//        resp.sendRedirect("../employee/list");
//    }
    @Override
    protected void doAuthorizedGet(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        // Get the 'eid' parameter from the request
        int id = Integer.parseInt(req.getParameter("eid"));

        // Fetch employee information using the parsed 'id'
        EmployeeDBContext dbEmp = new EmployeeDBContext();
        Employee e = dbEmp.get(id);
        if (e.getSals() == null) {
            e.setSals(new Salaries());  // Đảm bảo có đối tượng Salaries
        }
        if (e.getDept() == null) {
            e.setDept(new Department());  // Đảm bảo có đối tượng Department
        }
        req.setAttribute("e", e);

        DepartmentDBContext dbDept = new DepartmentDBContext();
        ArrayList<Department> depts = dbDept.list();
        SalaryDBContext dbSal = new SalaryDBContext();
        ArrayList<Salaries> sals = dbSal.list();

        // Set attributes and forward to JSP
        req.setAttribute("depts", depts);
        req.setAttribute("sals", sals);

        req.getRequestDispatcher("../view/employee/update.jsp").forward(req, resp);

    }
}
