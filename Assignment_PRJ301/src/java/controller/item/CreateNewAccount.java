/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoan.servlet;

import controller.registration.RegistrationInsertError;
import dal.UserDBContext;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Teacher
 */
@WebServlet(name = "CreateNewAccount", urlPatterns = {"/CreateNewAccount"})
public class CreateNewAccount extends HttpServlet {
    private final String INSERT_ERROR_PAGE = "createNewAccount.jsp";
    private final String LOGIN_PAGE = "login.jsp";
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
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirmPassword = request.getParameter("txtConfirmPassword");
        String email = request.getParameter("txtEmail");

        String url = INSERT_ERROR_PAGE;
        RegistrationInsertError errors = new RegistrationInsertError();
        boolean bErr = false;
        if(username.trim().length() < 6 || username.trim().length() > 20){
            bErr = true;
            errors.setUsernameLengthErr("Username requires 6 - 20 chars");
        }// end if username
        if(password.trim().length() < 6 || password.trim().length() > 30){
            bErr = true;
            errors.setPasswordLengthErr("Password requires 6 - 30 chars");
        }
        else if(!password.trim().equals(confirmPassword.trim())){
            bErr = true;
            errors.setConfirmNotMatch("Confirm must match Password");
        }// end if password
        if (email.trim().length() < 6 || email.trim().length() > 100) {
            bErr = true;
            errors.setFullNameLengthErr("Email requires 6 - 100 chars");
        }// end if username
        if(bErr){
            request.setAttribute("INSERTERR", errors);
        }
        else{
            UserDBContext dbUser = new UserDBContext();
            boolean result = dbUser.insertRecord(username, password, url);
            if(result){
                url = LOGIN_PAGE;
            }
        }//enf if bErr
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
