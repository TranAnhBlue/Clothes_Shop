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
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import model.User;

@WebServlet(name = "AddProductServlet", urlPatterns = {"/addproduct"})
public class AddProductServlet extends HttpServlet {

    private final ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        if (user!= null && user.getRid()==1) {
           
       
        try {
            // Retrieve lists of categories, brands, genders, and sizes
            List<Category> categories = productDAO.getAllCategories();
            List<Brand> brands = productDAO.getAllBrands();
            List<Gender> genders = productDAO.getAllGenders();
            List<Size> sizes = productDAO.getAllSizes();

            // Set attributes
            request.setAttribute("categories", categories);
            request.setAttribute("brands", brands);
            request.setAttribute("genders", genders);
            request.setAttribute("sizes", sizes);

            // Forward to JSP
            request.getRequestDispatcher("product/addproduct.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error retrieving product data", e);
        }
         } else {
            // Redirect to login page if user session is not found
            response.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double price = Double.parseDouble(request.getParameter("price"));
            String describe = request.getParameter("describe");
            String img = request.getParameter("img");
            Date releaseDate = Date.valueOf(request.getParameter("releaseDate"));
            int categoryId = Integer.parseInt(request.getParameter("category"));
            int brandId = Integer.parseInt(request.getParameter("brand"));
            int genderId = Integer.parseInt(request.getParameter("gender"));

            // Handle multiple size selections
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

            Product product = new Product(0, name, quantity, price, describe, img, releaseDate, category, brand, gender, sizes);
            productDAO.addProduct(product);

            response.sendRedirect("listproduct");
        } catch (Exception e) {
            throw new ServletException("Error adding product", e);
        }
    }
}
