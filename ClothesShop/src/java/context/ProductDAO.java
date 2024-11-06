/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Brand;
import model.Category;
import model.Gender;
import model.Product;
import model.Product_Size;
import model.Size;

public class ProductDAO extends DBContext {

    public ArrayList<Product> pagging(int index, int pageSize) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = """
                        SELECT * FROM product
                        ORDER BY pid
                        OFFSET ? ROWS
                        FETCH NEXT ? ROWS ONLY;
                        """;
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, (index - 1) * pageSize);
            stm.setInt(2, pageSize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product product = extractProduct(rs);
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public int calculateTotalPages(int pageSize) {
        int totalRecords = count();
        return (int) Math.ceil((double) totalRecords / pageSize);
    }

    public int count() {
        try {
            String sql = "SELECT COUNT(*) FROM product";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addProduct(Product product) {
        try {
            String sql = "INSERT INTO product (name, quantity, price, describe, img, releaseDate, cid, bid, gid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setString(1, product.getName());
            stm.setInt(2, product.getQuantity());
            stm.setDouble(3, product.getPrice());
            stm.setString(4, product.getDescribe());
            stm.setString(5, product.getImg());
            stm.setDate(6, new java.sql.Date(product.getReleaseDate().getTime()));
            stm.setInt(7, product.getCategory().getCid());
            stm.setInt(8, product.getBrand().getBid());
            stm.setInt(9, product.getGender().getGid());
            stm.executeUpdate();

            ResultSet generatedKeys = stm.getGeneratedKeys();
            if (generatedKeys.next()) {
                int productId = generatedKeys.getInt(1);
                product.setPid(productId); // Ensure product object has the generated ID
                addProductSizes(productId, product.getSizes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addProductSizes(int productId, List<Size> sizes) throws Exception {
        for (Size size : sizes) {
            Product_Size productSize = new Product_Size(size.getSid(), productId);
            addProductSize(productSize);
        }
    }

    public void addProductSize(Product_Size productSize) throws Exception {
        String sql = "INSERT INTO Product_Size (sid, pid) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, productSize.getSid());
            stmt.setInt(2, productSize.getPid());
            stmt.executeUpdate();
        }
    }
    public List<Size> getProductSizesById(int pid) {
        List<Size> sizes = new ArrayList<>();
        try (
             PreparedStatement stmt = connection.prepareStatement(""
                     + "SELECT s.* FROM size s JOIN product_size ps ON s.sid = ps.sid WHERE ps.pid = ?")) {
            stmt.setInt(1, pid);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Size size = new Size();
                size.setSid(rs.getInt("sid"));
                size.setName(rs.getString("name"));
                sizes.add(size);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sizes;
    }
    private List<Size> getProductSizes(int productId) throws Exception {
        List<Size> sizes = new ArrayList<>();
        String sql = "SELECT s.sid, s.name, s.description, s.gid FROM size s INNER JOIN product_size ps ON s.sid = ps.sid WHERE ps.pid = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, productId);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    int sid = rs.getInt("sid");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    int genderId = rs.getInt("gid");
                    Gender gender = getGenderById(genderId);
                    sizes.add(new Size(sid, name, description, gender));
                }
            }
        }
        return sizes;
    }

    public Size getSizeByIdAndGender(int id, String genderDescription) {
        Size size = null;
        try {
            String sql = """
                    SELECT s.sid, s.name, s.description, g.gid, g.description AS gender_description
                    FROM size s
                    JOIN gender g ON s.gid = g.gid
                    WHERE s.sid = ? AND g.description = ?""";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setString(2, genderDescription);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                int sid = rs.getInt("sid");
                String sizeName = rs.getString("name");
                String sizeDescription = rs.getString("description");
                int gid = rs.getInt("gid");
                Gender gender = new Gender(gid, genderDescription);
                size = new Size(sid, sizeName, sizeDescription, gender);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        try {
            String sql = "SELECT * FROM category";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("cid");
                String name = rs.getString("name");
                categories.add(new Category(id, name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    public List<Brand> getAllBrands() {
        List<Brand> brands = new ArrayList<>();
        try {
            String sql = "SELECT * FROM brand";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("bid");
                String name = rs.getString("name");
                brands.add(new Brand(id, name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return brands;
    }

    public List<Gender> getAllGenders() {
        List<Gender> genders = new ArrayList<>();
        try {
            String sql = "SELECT * FROM gender";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("gid");
                String description = rs.getString("description");
                genders.add(new Gender(id, description));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return genders;
    }

    public List<Size> getAllSizes() {
        List<Size> sizes = new ArrayList<>();
        String query = """
            SELECT s.sid, s.name, s.description, g.gid, g.description AS gender_description
            FROM size s
            LEFT JOIN gender g ON s.gid = g.gid
        """;

        try ( PreparedStatement stmt = connection.prepareStatement(query);  ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int sid = rs.getInt("sid");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int genderId = rs.getInt("gid");
                String genderDescription = rs.getString("gender_description");

                Gender gender = new Gender(genderId, genderDescription);
                Size size = new Size(sid, name, description, gender);
                sizes.add(size);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sizes;
    }

    private Product extractProduct(ResultSet rs) throws Exception {
        int pid = rs.getInt("pid");
        String name = rs.getString("name");
        int quantity = rs.getInt("quantity");
        double price = rs.getDouble("price");
        String describe = rs.getString("describe");
        String img = rs.getString("img");
        Date releaseDate = rs.getDate("releaseDate");
        int categoryId = rs.getInt("cid");
        int brandId = rs.getInt("bid");
        int genderId = rs.getInt("gid");

        Category category = getCategoryById(categoryId);
        Brand brand = getBrandById(brandId);
        Gender gender = getGenderById(genderId);
        List<Size> sizes = getProductSizes(pid);

        return new Product(pid, name, quantity, price, describe, img, releaseDate, category, brand, gender, sizes);
    }

    // Method to get Category by ID
    public Category getCategoryById(int id) {
        Category category = null;
        try {
            String sql = "SELECT * FROM category WHERE cid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                category = new Category(id, name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }

    public ArrayList<Product> searchByName(String name, int index) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = """
                        SELECT * FROM product
                        WHERE name LIKE ?
                        ORDER BY pid
                        OFFSET ? ROWS
                        FETCH NEXT 4 ROWS ONLY;
                        """;
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + name + "%");
            stm.setInt(2, (index - 1) * 4);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                // Extract product details and add to the list
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    // Method to get Brand by ID
    public Brand getBrandById(int id) {
        Brand brand = null;
        try {
            String sql = "SELECT * FROM brand WHERE bid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                brand = new Brand(id, name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return brand;
    }

    // Method to get Gender by ID
    public Gender getGenderById(int id) {
        Gender gender = null;
        try {
            String sql = "SELECT * FROM gender WHERE gid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                String description = rs.getString("description");
                gender = new Gender(id, description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gender;
    }

    public Product getProductById(int pid) {
        Product product = null;
        try {
            String sql = "SELECT * FROM product WHERE pid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pid);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                product = extractProduct(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    public ArrayList<Product> searchByName(String name, int pageIndex, int pageSize) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = """
                    SELECT * FROM product
                    WHERE name LIKE ?
                    ORDER BY pid
                    OFFSET ? ROWS
                    FETCH NEXT ? ROWS ONLY;
                    """;
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + name + "%");
            stm.setInt(2, (pageIndex - 1) * pageSize);
            stm.setInt(3, pageSize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product product = extractProduct(rs);
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public int countSearchResults(String search) {
        try {
            String sql = "SELECT COUNT(*) FROM product WHERE name LIKE ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + search + "%");
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<Product> paggingWithFilters(String category, String brand, String gender, Double priceFrom, Double priceTo, String size, int pageIndex, int pageSize) {

        ArrayList<Product> products = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM product WHERE 1=1");
            if (category != null && !category.isEmpty()) {
                sql.append(" AND cid = ?");
            }
            if (brand != null && !brand.isEmpty()) {
                sql.append(" AND bid = ?");
            }
            if (gender != null && !gender.isEmpty()) {
                sql.append(" AND gid = ?");
            }
            if (priceFrom != null) {
                sql.append(" AND price >= ?");
            }
            if (priceTo != null) {
                sql.append(" AND price <= ?");
            }
            sql.append(" ORDER BY pid OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;");

            PreparedStatement stm = connection.prepareStatement(sql.toString());
            int paramIndex = 1;
            if (category != null && !category.isEmpty()) {
                stm.setInt(paramIndex++, Integer.parseInt(category));
            }
            if (brand != null && !brand.isEmpty()) {
                stm.setInt(paramIndex++, Integer.parseInt(brand));
            }
            if (gender != null && !gender.isEmpty()) {
                stm.setInt(paramIndex++, Integer.parseInt(gender));
            }
            if (priceFrom != null) {
                stm.setDouble(paramIndex++, priceFrom);
            }
            if (priceTo != null) {
                stm.setDouble(paramIndex++, priceTo);
            }
            stm.setInt(paramIndex++, (pageIndex - 1) * pageSize);
            stm.setInt(paramIndex, pageSize);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product product = extractProduct(rs);
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public int countWithFilters(String category, String brand, String gender, Double priceFrom, Double priceTo, String size) {

        try {
            StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM product WHERE 1=1");
            if (category != null && !category.isEmpty()) {
                sql.append(" AND cid = ?");
            }
            if (brand != null && !brand.isEmpty()) {
                sql.append(" AND bid = ?");
            }
            if (gender != null && !gender.isEmpty()) {
                sql.append(" AND gid = ?");
            }
            if (priceFrom != null) {
                sql.append(" AND price >= ?");
            }
            if (priceTo != null) {
                sql.append(" AND price <= ?");
            }

            PreparedStatement stm = connection.prepareStatement(sql.toString());
            int paramIndex = 1;
            if (category != null && !category.isEmpty()) {
                stm.setInt(paramIndex++, Integer.parseInt(category));
            }
            if (brand != null && !brand.isEmpty()) {
                stm.setInt(paramIndex++, Integer.parseInt(brand));
            }
            if (gender != null && !gender.isEmpty()) {
                stm.setInt(paramIndex++, Integer.parseInt(gender));
            }
            if (priceFrom != null) {
                stm.setDouble(paramIndex++, priceFrom);
            }
            if (priceTo != null) {
                stm.setDouble(paramIndex, priceTo);
            }

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<Product> searchByNameAndFilters(String search, String category, String brand, String gender, Double priceFrom, Double priceTo, String size, int pageIndex, int pageSize) {

        ArrayList<Product> products = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM product WHERE name LIKE ?");
            if (category != null && !category.isEmpty()) {
                sql.append(" AND cid = ?");
            }
            if (brand != null && !brand.isEmpty()) {
                sql.append(" AND bid = ?");
            }
            if (gender != null && !gender.isEmpty()) {
                sql.append(" AND gid = ?");
            }
            if (priceFrom != null) {
                sql.append(" AND price >= ?");
            }
            if (priceTo != null) {
                sql.append(" AND price <= ?");
            }
            sql.append(" ORDER BY pid OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;");

            PreparedStatement stm = connection.prepareStatement(sql.toString());
            stm.setString(1, "%" + search + "%");
            int paramIndex = 2;
            if (category != null && !category.isEmpty()) {
                stm.setInt(paramIndex++, Integer.parseInt(category));
            }
            if (brand != null && !brand.isEmpty()) {
                stm.setInt(paramIndex++, Integer.parseInt(brand));
            }
            if (gender != null && !gender.isEmpty()) {
                stm.setInt(paramIndex++, Integer.parseInt(gender));
            }
            if (priceFrom != null) {
                stm.setDouble(paramIndex++, priceFrom);
            }
            if (priceTo != null) {
                stm.setDouble(paramIndex++, priceTo);
            }
            stm.setInt(paramIndex++, (pageIndex - 1) * pageSize);
            stm.setInt(paramIndex, pageSize);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product product = extractProduct(rs);
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public int countSearchResultsWithFilters(String search, String category, String brand, String gender, Double priceFrom, Double priceTo, String size) {

        try {
            StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM product WHERE name LIKE ?");
            if (category != null && !category.isEmpty()) {
                sql.append(" AND cid = ?");
            }
            if (brand != null && !brand.isEmpty()) {
                sql.append(" AND bid = ?");
            }
            if (gender != null && !gender.isEmpty()) {
                sql.append(" AND gid = ?");
            }
            if (priceFrom != null) {
                sql.append(" AND price >= ?");
            }
            if (priceTo != null) {
                sql.append(" AND price <= ?");
            }

            PreparedStatement stm = connection.prepareStatement(sql.toString());
            stm.setString(1, "%" + search + "%");
            int paramIndex = 2;
            if (category != null && !category.isEmpty()) {
                stm.setInt(paramIndex++, Integer.parseInt(category));
            }
            if (brand != null && !brand.isEmpty()) {
                stm.setInt(paramIndex++, Integer.parseInt(brand));
            }
            if (gender != null && !gender.isEmpty()) {
                stm.setInt(paramIndex++, Integer.parseInt(gender));
            }
            if (priceFrom != null) {
                stm.setDouble(paramIndex++, priceFrom);
            }
            if (priceTo != null) {
                stm.setDouble(paramIndex, priceTo);
            }

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Size getSizeById(int id) {
        Size size = null;
        try {
            String sql = "SELECT * FROM size WHERE sid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                int genderId = rs.getInt("gid");
                Gender gender = getGenderById(genderId);
                size = new Size(id, name, description, gender);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }
     public void updateProduct(Product product) {
        try {
            String sql = """
                    UPDATE product 
                    SET name = ?, quantity = ?, price = ?, describe = ?, img = ?, releaseDate = ?, cid = ?, bid = ?, gid = ? 
                    WHERE pid = ?
                    """;
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, product.getName());
            stm.setInt(2, product.getQuantity());
            stm.setDouble(3, product.getPrice());
            stm.setString(4, product.getDescribe());
            stm.setString(5, product.getImg());
            stm.setDate(6, new java.sql.Date(product.getReleaseDate().getTime()));
            stm.setInt(7, product.getCategory().getCid());
            stm.setInt(8, product.getBrand().getBid());
            stm.setInt(9, product.getGender().getGid());
            stm.setInt(10, product.getPid());
            stm.executeUpdate();

            // Cập nhật các kích thước của sản phẩm
            deleteProductSizes(product.getPid()); // Xóa các kích thước hiện tại
            addProductSizes(product.getPid(), product.getSizes()); // Thêm lại các kích thước mới
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteProductSizes(int productId) throws Exception {
        String sql = "DELETE FROM Product_Size WHERE pid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            stmt.executeUpdate();
        }
    }

    public void deleteProduct(int pid) {
        try {
            deleteProductSizes(pid); // Xóa các kích thước của sản phẩm trước
            String sql = "DELETE FROM product WHERE pid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pid);
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addProductWishList(int pId, int uId) {
        try {
            String sql = "INSERT INTO collection (uid,pid) VALUES (?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setInt(1, uId);
            stm.setInt(2, pId);
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     public ArrayList<Product> wishList(int uid) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = """
                        SELECT product.*
                        FROM product
                        INNER JOIN collection ON product.pId = collection.pid
                        WHERE collection.uid = ?
                        ORDER BY product.pId;
                        """;
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, uid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product product = extractProduct(rs);
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
     public void deleteProductWishList(int pid, int uid) {
        try {
            deleteProductSizes(pid); // Xóa các kích thước của sản phẩm trước
            String sql = "DELETE FROM collection WHERE pid = ? AND uid = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pid);
            stm.setInt(2, uid);
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
