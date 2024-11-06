package controller.home;

import context.HomeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Product;

public class AllProduct extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String sortBy = request.getParameter("sortBy");
        if (sortBy == null) {
            sortBy = "name";
        }

        int page = 1;
        int pageSize = 6;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        HomeDAO homeDAO = new HomeDAO();
        List<Product> products = homeDAO.getSortedAndPaginatedProducts(sortBy, page, pageSize);
        ArrayList<Category> categories = homeDAO.getAllCate();
        int totalProducts = homeDAO.getTotalProducts();
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);
        List<Product> products2 = homeDAO.getLatestProducts(3);
        request.setAttribute("products2", products2);
        request.setAttribute("categories", categories);
        request.setAttribute("products", products);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("sortBy", sortBy);

        request.getRequestDispatcher("homepage/allproduct.jsp").forward(request, response);
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
        return "Short description";
    }
}
