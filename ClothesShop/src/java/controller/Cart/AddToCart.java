/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.Cart;

import com.google.gson.Gson;
import context.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Product;

/**
 *
 * @author ADMIN
 */
public class AddToCart extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddToCart</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddToCart at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         HttpSession session = request.getSession();
    List<Product> cart = (List<Product>) session.getAttribute("cart");
    Integer productInCart = (Integer)session.getAttribute("productInCart");
    ProductDAO dao = new ProductDAO();
    boolean outOfStock = false;
    Double totalPrice =(Double) session.getAttribute("totalPrice");
    if (totalPrice == null) {
    totalPrice = 0.0;
}
    if (productInCart == null) {
    productInCart = 0;
}
    if (cart == null) {
        cart = new ArrayList<>();
        totalPrice = 0.0;
    }
  

    // Giả sử bạn có một phương thức để lấy sản phẩm theo ID
    String productId = request.getParameter("pId");
    Product product = dao.getProductById(Integer.parseInt(productId));
    
    if (product != null && product.getQuantity()>0) {
        
        totalPrice += product.getPrice();
        
        boolean found = false;
        for (Product p : cart) {
            if (p.getPid()== product.getPid()) { 
                if(product.getQuantity() <= p.getQuantity()){
                    outOfStock = true;
                    found = true;
                    break;
                }
                p.setQuantity(p.getQuantity() + 1);
                found = true;
                productInCart ++;
                break;
            }
        }

        if (!found) {
            product.setQuantity(1);
            cart.add(product);
            productInCart ++;
        }
 
        
       session.setAttribute("productInCart", productInCart);
       session.setAttribute("totalPrice", totalPrice);
        session.setAttribute("cart", cart);
    }
    else{
        outOfStock = true;
    }

    // Chuyển đổi danh sách sản phẩm thành JSON
    Gson gson = new Gson();
    Map<String, Object> result = new HashMap<>();
        result.put("outOfStock", outOfStock);
        result.put("productInCart", productInCart);
    String jsonCart = gson.toJson(result);
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(jsonCart);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
