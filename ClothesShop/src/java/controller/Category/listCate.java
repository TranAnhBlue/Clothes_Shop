/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Category;

import context.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Category;
import model.User;

/**
 *
 * @author chien
 */
public class listCate extends HttpServlet {

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
            out.println("<title>Servlet listCate</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet listCate at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        User o = (User) session.getAttribute("user");
          if (o == null) {
            response.sendRedirect("login");
            return; // Ensure the method returns to avoid further execution
        }
        if (o.getRid() == 1) {
            CategoryDAO cd = new CategoryDAO();

            String indexPage = request.getParameter("index");
            String searchCate = request.getParameter("searchCate");
            String name = request.getParameter("addCate_name");
            String sortCate = request.getParameter("sortCate");
            String sortType = request.getParameter("sortType");
            if (indexPage == null) {
                indexPage = "1";
            }
            if (sortCate == null) {
                sortCate = "0";
            }
            if (sortType == null) {
                sortType = "0";
            }

            if (searchCate == null || searchCate.trim() == "") {
                int index1 = Integer.parseInt(indexPage);
                int count = cd.count();
                int endPage = count / 5;
                if (count % 5 != 0) {
                    endPage++;
                }
                if (name != null) {
                    cd.addCategory(name);
                    request.setAttribute("add_suc", "Add category sucessfully!");
                }
                List<Category> listC = cd.pagging(index1, sortCate, sortType);
                request.setAttribute("endPage", endPage);
                request.setAttribute("listC", listC);
                request.setAttribute("searchCate", searchCate);
                request.setAttribute("count", count);
                request.setAttribute("sortType", sortType);
                request.setAttribute("sortCate", sortCate);

                request.getRequestDispatcher("category_brand/listCate2.jsp").forward(request, response);

            } else {
                int index1 = Integer.parseInt(indexPage);
                int count = cd.count2(searchCate);
                int endPage = count / 5;
                if (count % 5 != 0) {
                    endPage++;
                }
                if (name != null) {
                    cd.addCategory(name);
                    request.setAttribute("add_suc", "Add category sucessfully!");
                }
                List<Category> listC = cd.search(searchCate, index1, sortCate, sortType);
                request.setAttribute("endPage", endPage);
                request.setAttribute("listC", listC);
                request.setAttribute("searchCate", searchCate);
                request.setAttribute("count", count);
                request.setAttribute("sortType", sortType);
                request.setAttribute("sortCate", sortCate);

                request.getRequestDispatcher("category_brand/listCate2.jsp").forward(request, response);
            }
        } else {
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
