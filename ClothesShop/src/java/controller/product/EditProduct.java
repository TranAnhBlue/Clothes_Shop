package controller.product;

import context.ProductDAO;
import model.Brand;
import model.Category;
import model.Gender;
import model.Product;
import model.Size;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EditProduct", urlPatterns = {"/editproduct"})
public class EditProduct extends HttpServlet {

    private final ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            Product product = productDAO.getProductById(productId);

            List<Category> categories = productDAO.getAllCategories();
            List<Brand> brands = productDAO.getAllBrands();
            List<Gender> genders = productDAO.getAllGenders();
            List<Size> sizes = productDAO.getAllSizes();

            request.setAttribute("product", product);
            request.setAttribute("categories", categories);
            request.setAttribute("brands", brands);
            request.setAttribute("genders", genders);
            request.setAttribute("sizes", sizes);

            request.getRequestDispatcher("product/editproduct.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error retrieving product data", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            String name = request.getParameter("name");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double price = Double.parseDouble(request.getParameter("price"));
            String describe = request.getParameter("describe");
            String img = request.getParameter("img");
            Date releaseDate = Date.valueOf(request.getParameter("releaseDate"));
            int categoryId = Integer.parseInt(request.getParameter("category"));
            int brandId = Integer.parseInt(request.getParameter("brand"));
            int genderId = Integer.parseInt(request.getParameter("gender"));

            String[] sizeIds = request.getParameterValues("size");
            List<Size> sizes = new ArrayList<>();
            if (sizeIds != null) {
                for (String sizeId : sizeIds) {
                    Size size = productDAO.getSizeById(Integer.parseInt(sizeId));
                    sizes.add(size);
                }
            }

            Category category = productDAO.getCategoryById(categoryId);
            Brand brand = productDAO.getBrandById(brandId);
            Gender gender = productDAO.getGenderById(genderId);

            Product product = new Product(productId, name, quantity, price, describe, img, releaseDate, category, brand, gender, sizes);
            productDAO.updateProduct(product);

            response.sendRedirect("listproduct");
        } catch (Exception e) {
            throw new ServletException("Error updating product", e);
        }
    }
}
