/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.registration;

import dal.UserDBContext;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.regex.Pattern;
import model.auth.User;

/**
 *
 * @author lenovo
 */
@WebServlet(name = "CreateNewAccount", urlPatterns = {"/CreateNewAccount"})
public class CreateNewAccount extends HttpServlet {

    private final String INSERT_ERROR_PAGE = "createNewAccount.jsp";
    private final String LOGIN_PAGE = "login.html";

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
        String confirm = request.getParameter("txtConfirm");
        String email = request.getParameter("txtEmail");

        String url = INSERT_ERROR_PAGE;
        RegistrationInsertError errors = new RegistrationInsertError();
        boolean bErr = false;
        try {
            // Username validation
            if (username.trim().length() < 6 || username.trim().length() > 20) {
                bErr = true;
                errors.setUsernameLengthErr("Username requires 6 - 20 characters.");
            }

            // Password validation
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                bErr = true;
                errors.setPasswordLengthErr("Password requires 6 - 30 characters.");
            } else if (!password.trim().equals(confirm.trim())) {
                bErr = true;
                errors.setConfirmNotMatch("Confirm password must match the password.");
            }

            // Email validation
            if (!isValidEmail(email)) {
                bErr = true;
                errors.setEmailInvalidErr("Invalid email format.");
            }

            if (bErr) {
                // If there are validation errors, set them as request attributes
                request.setAttribute("INSERTERR", errors);
            } else {
                // Try to insert the record into the database
                UserDBContext userDB = new UserDBContext();
                boolean result = userDB.insertRecord(username, password, email);

                if (result) {
                    // Redirect to login page if successful
                    url = LOGIN_PAGE;
                } else {
                    // Handle case where username or email already exists
                    errors.setUsernameIsExited(username + " already exists.");
                    request.setAttribute("INSERTERR", errors);
                }
            }
        
//    catch (SQLException ex) {
//            log("CreateNewAccount _SQLException: " + ex.getMessage());
//            errors.setUsernameIsExited("A database error occurred. Please try again.");
//            request.setAttribute("INSERTERR", errors);
        } catch (Exception ex) {
            log("CreateNewAccount _Exception: " + ex.getMessage());
            errors.setUsernameIsExited("An unexpected error occurred. Please try again.");
            request.setAttribute("INSERTERR", errors);
        } finally {
            // Forward to the appropriate page
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // Helper method to validate email format
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(emailRegex, email);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Handles user account creation.";
    }
}
