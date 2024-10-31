/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.shift;

import dal.ShiftDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Shift;

/**
 *
 * @author lenovo
 */
public class ShiftListController extends HttpServlet {
   
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShiftDBContext db = new ShiftDBContext();
        ArrayList<Shift> shifts = db.list();
        req.setAttribute("shifts", shifts);
        req.getRequestDispatcher("../view/shift/list.jsp").forward(req, resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

}
