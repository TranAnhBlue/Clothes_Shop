package controller.home;

import context.HomeDAO;
import context.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Product;
import model.Size;

public class DetailProduct extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Lấy ID sản phẩm từ request
        int pid = Integer.parseInt(request.getParameter("pid"));

        ProductDAO dao = new ProductDAO();
        HomeDAO dao2 = new HomeDAO();
        Product product = dao.getProductById(pid);
        List<Size> sizes = dao.getProductSizesById(pid);
        product.setSizes(sizes);
        List<Product> relatedProducts = dao2.getProductsByCategory(product.getCategory().getCid());

        request.setAttribute("relatedProducts", relatedProducts);
        request.setAttribute("product", product);
        request.getRequestDispatcher("homepage/productdetail.jsp").forward(request, response);
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
