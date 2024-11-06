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

public class SearchProduct extends HttpServlet {

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

        String searchKeyword = request.getParameter("searchKeyword");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));

        HomeDAO dao = new HomeDAO();
        int totalProducts = dao.getTotalSearchedProducts(searchKeyword, categoryId);
        List<Product> products = dao.searchProductsByNameAndCategoryWithSortingAndPaging(
                searchKeyword, categoryId, sortBy, page, pageSize);
        List<Product> products2 = dao.getLatestProducts(3);
        ArrayList<Category> categories = dao.getAllCate();
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

// Ensure these attributes are set for the JSP
        request.setAttribute("searchKeyword", searchKeyword);
        request.setAttribute("categoryId", categoryId);
        List<Product> latestProducts = dao.getLatestProducts(3);
        request.setAttribute("products2", products2);
        request.setAttribute("products", products);
        request.setAttribute("categories", categories);
        request.setAttribute("latestProducts", latestProducts);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("sortBy", sortBy);

        request.getRequestDispatcher("homepage/searchproduct.jsp").forward(request, response);
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
        return "Servlet for searching products by name and category with sorting and pagination";
    }
}
