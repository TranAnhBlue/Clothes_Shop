/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.util.ArrayList;
import model.Brand;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author chien
 */
public class BrandDAO extends DBContext{
    
  public ArrayList<Brand> search(String key, int index, String sort, String type) {
        ArrayList<Brand> list = new ArrayList<>();

        // Validate the sort column name to prevent SQL injection
        String sortColumn;
        switch (sort) {
            case "1":
                switch (type) {
                    case "1":
                        sortColumn = "bid asc";
                        break;
                    case "2":
                        sortColumn = "bid desc";
                        break;
                    default:
                        sortColumn = "bid asc";
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
                sortColumn = "bid asc";
                break;
        }

        String sql = "SELECT * FROM brand c WHERE c.name LIKE ? or c.bid LIKE ? "
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
                list.add(new Brand(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public ArrayList<Brand> pagging(int index, String sort, String type) {
        ArrayList<Brand> cate = new ArrayList<>();
        try {
            // Validate the sort column name and order to prevent SQL injection
            String sortColumn;
            switch (sort) {
                case "1":
                    switch (type) {
                        case "1":
                            sortColumn = "bid asc";
                            break;
                        case "2":
                            sortColumn = "bid desc";
                            break;
                        default:
                            sortColumn = "bid asc";
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
                    sortColumn = "bid asc";
                    break;
            }

            String sql = "SELECT * FROM brand\n"
                    + "ORDER BY " + sortColumn + " \n"
                    + "OFFSET ? ROWS\n"
                    + "FETCH NEXT 5 ROWS ONLY;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, (index - 1) * 5);
         
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                cate.add(new Brand(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Log the exception
        }
        return cate;
    }

    public int count() {
        try {
            String sql = "select count (*) from brand";
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
            String sql = "SELECT count(*) from brand c where c.name like '%" + key + "%' or c.bid like '%" + key + "%'";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public ArrayList<Brand> getAllCate() {
        ArrayList<Brand> listCate = new ArrayList<>();
        String sql = "select * from brand";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                listCate.add(new Brand(rs.getInt("bid"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listCate;
    }
    public Brand getCateById(int bid) {

        String sql = "select * from brand where bid = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, bid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return (new Brand(rs.getInt("bid"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Brand> sortCateByName() {
        ArrayList<Brand> listCate = new ArrayList<>();
        String sql = "select * from brand order by name desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                listCate.add(new Brand(rs.getInt("bid"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listCate;
    }

    public void addBrand(String name) {
        String sql = "insert into brand values (?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void deleteCate(int bid) {
        String sql = "delete from brand\n"
                + "where bid=?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setInt(1, bid);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCate(int bid, String name) {
        String sql = "Update brand set name = ? where bid =? ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.setInt(2, bid);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkDelete(int bid) {
        String sql = "select * from product where bid=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, bid);

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
        BrandDAO cb = new BrandDAO();
        System.out.println(cb.count2("a"));
    }
}
