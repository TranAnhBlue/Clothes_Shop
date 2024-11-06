/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Login;

import context.OrderDAO;
import context.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author chien
 */
public class login extends HttpServlet {

    /**
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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet login at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie arr[] = request.getCookies();
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("username")) {
                    request.setAttribute("username", o.getValue());
                }
                if (o.getName().equals("password")) {
                    request.setAttribute("password", o.getValue());
                }
            }
             request.getRequestDispatcher("login/login.jsp").forward(request, response);
        }else{
            response.sendRedirect("Home");
        }
       
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
        String u = request.getParameter("username");
        String pass = request.getParameter("password");
        String r = request.getParameter("rem");
        UserDAO ud = new UserDAO();
        OrderDAO ord = new OrderDAO();
        String p = ud.toSHA1(pass);
        HttpSession session = request.getSession();
        User user = ud.checkUser(u, p);
        if (user == null) {
            request.setAttribute("err", "Invalid user or pass");
            request.getRequestDispatcher("login/login.jsp").forward(request, response);
        } else if (user.getActive() == 0) {
            request.setAttribute("err", "Your account has been locked, please contact admin fix the status! ");
            request.getRequestDispatcher("login/login.jsp").forward(request, response);
        } else if (user.getRid() == 0) {
            double wallet = ord.getWalletByUId(user);
            session.setAttribute("wallet", wallet);
            session.setAttribute("user", user);
            Cookie username = new Cookie("username", u);
            Cookie password = new Cookie("password", pass);
            Cookie remem = new Cookie("rem", r);
            if (r == null) {
                username.setMaxAge(0);
                password.setMaxAge(0);
                remem.setMaxAge(0);
            } else {
                username.setMaxAge(24 * 60 * 60 * 60);
                password.setMaxAge(24 * 60 * 60 * 60);
                remem.setMaxAge(24 * 60 * 60 * 60);
            }
            response.addCookie(username);//lưu lên trình duyệt mở
            response.addCookie(password);
            response.addCookie(remem);
            response.sendRedirect("Home");
        } else {
            double wallet = ord.getWalletByUId(user);
            session.setAttribute("wallet", wallet);
            session.setAttribute("user", user);
            Cookie username = new Cookie("username", u);
            Cookie password = new Cookie("password", pass);
            Cookie remem = new Cookie("rem", r);
            if (r == null) {
                username.setMaxAge(0);
                password.setMaxAge(0);
                remem.setMaxAge(0);
            } else {
                username.setMaxAge(24 * 60 * 60 * 60);
                password.setMaxAge(24 * 60 * 60 * 60);
                remem.setMaxAge(24 * 60 * 60 * 60);
            }
            response.addCookie(username);//lưu lên trình duyệt mở
            response.addCookie(password);
            response.addCookie(remem);
            response.sendRedirect("listproduct");
        }
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
