/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.auth;

import dal.UserDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.auth.User;

/**
 *
 * @author lenovo
 */
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param_user = req.getParameter("username");//user input
        String param_pass = req.getParameter("password");
        String mess = "";

        UserDBContext db = new UserDBContext();
        model.auth.User account = db.get(param_user, param_pass);

        if (account != null) {
//            resp.getWriter().println("login successful!");
            req.getSession().setAttribute("account", account);
            req.getRequestDispatcher("home.jsp").forward(req, resp);
        } else {
//            resp.getWriter().println("login failed!");
            mess = "Wrong username or password!";
            req.setAttribute("mess", mess); // Pass error message to the JSP
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }

//        String url = this.getInitParameter("url");
//        resp.getWriter().println(url);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

}
