package controller.product;

import context.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;
import model.Size;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductDetailServlet", urlPatterns = {"/productdetail"})
public class ProductDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pid = Integer.parseInt(request.getParameter("pid"));
        ProductDAO dao = new ProductDAO();
        Product product = dao.getProductById(pid);

        // Ensure the product's sizes are loaded
        List<Size> sizes = dao.getProductSizesById(pid);
        product.setSizes(sizes);

        request.setAttribute("product", product);
        request.getRequestDispatcher("product/productdetail.jsp").forward(request, response);
    }
}
