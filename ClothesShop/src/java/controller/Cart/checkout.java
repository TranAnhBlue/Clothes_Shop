/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Cart;

import context.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Product;
import model.User;

/**
 *
 * @author chien
 */
public class checkout extends HttpServlet {

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
            out.println("<title>Servlet checkout</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet checkout at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        if (user != null) {
            request.getRequestDispatcher("Order/checkout.jsp").forward(request, response);
        } else {
            // Redirect to login page if user session is not found
            response.sendRedirect("login");
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
        // Retrieve parameters from the URL4
        String address = request.getParameter("addressInput");
        String note = request.getParameter("Note");
        String payment = request.getParameter("pay");

        // Retrieve session
        HttpSession session = request.getSession(true);

        // Retrieve user session attribute
        User user = (User) session.getAttribute("user");

        // Check if user is logged in
        OrderDAO ord = new OrderDAO();
        String output = "";
        boolean outOfStock = false;

        if (address.isBlank()) {
            output += "Please enter address!";
            request.setAttribute("output", output);
            request.getRequestDispatcher("Order/checkout.jsp").forward(request, response);
            return;
        }
        // Retrieve cart items from session
        List<Product> listP = (List<Product>) session.getAttribute("cart");
        if (listP == null) {
            output += "Cart is null. Please add product to cart!";

            request.setAttribute("output", output);
            request.getRequestDispatcher("Order/checkout.jsp").forward(request, response);
            return;
        }

        // Check each product in the cart
        for (Product p : listP) {
            if (ord.checkValidProduct(p)) {
                ord.reduceNumberProduct(p.getPid(), p.getQuantity());
            } else {
                output += "The product " + p.getName() + " is out of stock, please wait until we restock.";
                request.setAttribute("output", output);
                request.getRequestDispatcher("Order/checkout.jsp").forward(request, response);
                return;
            }
        }

        // Proceed with order if all products are in stock
        if (!outOfStock) {
            double totalMoney = (double) session.getAttribute("totalPrice");
            if (ord.checkValidBuy(totalMoney, user) && payment.equals("payWallet1")) {
                // Add order to database

                int oid = ord.addOrder(user, address, note, totalMoney);
                for (Product product : listP) {
                    ord.addOrderDetal(oid, product.getPid(), product.getQuantity());

                }

                ord.reduceWallet(user, totalMoney);
                
                  session.removeAttribute("wallet");
                double wallet = ord.getWalletByUId(user);
                session.setAttribute("wallet", wallet);
                // Clear session attributes related to cart
                output += "Checkout success!";
                session.removeAttribute("cart");
                session.removeAttribute("totalPrice");
                session.removeAttribute("productInCart");
            } else if (payment.equals("payNoWallet1")) {
                int oid = ord.addOrder(user, address, note, totalMoney);
                for (Product product : listP) {
                    ord.addOrderDetal(oid, product.getPid(), product.getQuantity());

                }

                // Clear session attributes related to cart
                output += "Your order will be delivered soon!";
                session.removeAttribute("cart");
                session.removeAttribute("totalPrice");
                session.removeAttribute("productInCart");
            } else {
                output += "Insufficient funds.";
            }
        }

        // Set output attribute and forward to checkout.jsp
        request.setAttribute("output", output);
        request.getRequestDispatcher("Order/checkout.jsp").forward(request, response);

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
