/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.util.ArrayList;
import model.Category;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;

/**
 *
 * @author chien
 */
public class CategoryDAO extends DBContext {

    public ArrayList<Category> search(String key, int index, String sort, String type) {
        ArrayList<Category> list = new ArrayList<>();

        // Validate the sort column name to prevent SQL injection
        String sortColumn;
        switch (sort) {
            case "1":
                switch (type) {
                    case "1":
                        sortColumn = "cid asc";
                        break;
                    case "2":
                        sortColumn = "cid desc";
                        break;
                    default:
                        sortColumn = "cid asc";
                        break;
                }
                break;
            case "2":
                switch (type) {
                    case "1":
                        sortColumn = "name asc";
                        break;
                    case "2":
                        sortColumn = "name desc";
                        break;
                    default:
                        sortColumn = "name asc";
                        break;
                }
                break;
            default:
                sortColumn = "cid asc";
                break;
        }

        String sql = "SELECT * FROM category c WHERE c.name LIKE ? or c.cid LIKE ? "
                + "ORDER BY " + sortColumn + " "
                + "OFFSET ? ROWS "
                + "FETCH NEXT 5 ROWS ONLY;";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + key + "%");
            stm.setString(2, "%" + key + "%");
            stm.setInt(3, (index - 1) * 5);
       
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public ArrayList<Category> pagging(int index, String sort, String type) {
        ArrayList<Category> cate = new ArrayList<>();
        try {
            // Validate the sort column name and order to prevent SQL injection
            String sortColumn;
            switch (sort) {
                case "1":
                    switch (type) {
                        case "1":
                            sortColumn = "cid asc";
                            break;
                        case "2":
                            sortColumn = "cid desc";
                            break;
                        default:
                            sortColumn = "cid asc";
                            break;
                    }
                    break;
                case "2":
                    switch (type) {
                        case "1":
                            sortColumn = "name asc";
                            break;
                        case "2":
                            sortColumn = "name desc";
                            break;
                        default:
                            sortColumn = "name asc";
                            break;
                    }
                    break;
                default:
                    sortColumn = "cid asc";
                    break;
            }

            String sql = "SELECT * FROM category\n"
                    + "ORDER BY " + sortColumn + " \n"
                    + "OFFSET ? ROWS\n"
                    + "FETCH NEXT 5 ROWS ONLY;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, (index - 1) * 5);
         
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                cate.add(new Category(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Log the exception
        }
        return cate;
    }

    public int count() {
        try {
            String sql = "select count (*) from category";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int count2(String key) {
        try {
            String sql = "SELECT count(*) from category c where c.name like '%" + key + "%'";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public ArrayList<Category> getAllCate() {
        ArrayList<Category> listCate = new ArrayList<>();
        String sql = "select * from category";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                listCate.add(new Category(rs.getInt("cid"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listCate;
    }
    public Category getCateById(int cid) {

        String sql = "select * from category where cid = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return (new Category(rs.getInt("cid"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Category> sortCateByName() {
        ArrayList<Category> listCate = new ArrayList<>();
        String sql = "select * from category order by name desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                listCate.add(new Category(rs.getInt("cid"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listCate;
    }

    public void addCategory(String name) {
        String sql = "insert into category values (?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void deleteCate(int cid) {
        String sql = "delete from category\n"
                + "where cid=?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setInt(1, cid);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCate(int cid, String name) {
        String sql = "Update category set name = ? where cid =? ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.setInt(2, cid);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkDelete(int cid) {
        String sql = "select * from product where cid=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cid);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void main(String[] args) {

        CategoryDAO cd = new CategoryDAO();
        cd.addCategory("123");


  
        cd.deleteCate(45);
    }
}
