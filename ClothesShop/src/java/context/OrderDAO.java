/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.time.LocalDate;
import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Product;

/**
 *
 * @author chien
 */
public class OrderDAO extends DBContext {

    //add order 
    public int addOrder(User u, String address, String note, double totalPrice) {

        LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString();
        try {

            // add vào bảng Order
            String sql = "INSERT INTO [dbo].[Order]\n"
                    + "           ([orderDate]\n"
                    + "           ,[totalPrice]\n"
                    + "           ,[address]\n"
                    + "           ,[status]\n"
                    + "           ,[note]\n"
                    + "           ,[uid])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?)";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, date);
            st.setDouble(2, totalPrice);
            st.setString(3, address);
            st.setString(4, "0");
            st.setString(5, note);
            st.setInt(6, u.getUid());
            st.executeUpdate();

            //get id  order
            int oid;
            String sql1 = "SELECT [oid]\n"
                    + "  FROM [dbo].[Order]\n"
                    + "  Where uid= ? and  orderDate like ?";
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setInt(1, u.getUid());
            st1.setString(2, date);
            ResultSet rs1 = st1.executeQuery();
            if (rs1.next()) {
                oid = rs1.getInt("oid");
                return oid;
            }

        } catch (SQLException e) {
        }
        return -1;
    }

    //add order detail
    public void addOrderDetal(int oid, int pid, int quantity) {
        try {
            String sql = "INSERT INTO [dbo].[OrderDetail]\n"
                    + "           ([oid]\n"
                    + "           ,[pid]\n"
                    + "           ,[numP])\n"
                    + "     VALUES\n"
                    + "           (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, oid);
            ps.setInt(2, pid);
            ps.setInt(3, quantity);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getOrderDetail(int uid) {
        List<Product> listPOrder = new ArrayList<>();
        try {
            String sql = "SELECT od.[pid] as [pid]\n"
                    + "      ,[name]\n"
                    + "      ,od.numP as [quantity] \n"
                    + "      ,od.numP*p.price as [price]\n"
                    + "      ,[describe]\n"
                    + "      ,[img]\n"
                    + "      ,o.orderDate as [releaseDate]\n"
                    + "      ,[cid] \n"
                    + "      ,[bid]\n"
                    + "      ,[gid]\n"
                    + "  FROM [ClothesShop].[dbo].[OrderDetail] od\n"
                    + "  join   [Order] o on  o.oid = od.oid\n"
                    + "  join [Product] p on p.pid = od.pid\n"
                    + "  where uid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, uid);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listPOrder.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDate(7), null, null, null, null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPOrder;
    }

    public void reduceNumberProduct(int pid, int numberP) {
        String sql = "UPDATE Product SET quantity = quantity - ? WHERE pid = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, numberP);
            st.setInt(2, pid);
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void reduceWallet(User u, double totalMoney) {
        String sql = "UPDATE [dbo].[wallet]\n"
                + "   SET wallet = wallet - ?"
                + " WHERE uid = ? ";
        String sql1 = "UPDATE [dbo].[wallet]\n"
                + "   SET wallet = wallet + ?"
                + " WHERE uid = 1 ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setDouble(1, totalMoney);
            st.setInt(2, u.getUid());
            st.executeUpdate();

            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setDouble(1, totalMoney);
            st1.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public double getWalletByUId(User u) {
        int uid = u.getUid();
        String sql = "SELECT wallet\n"
                + "  FROM [dbo].[wallet]\n"
                + "  where uid = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, uid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("wallet");
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public boolean checkValidBuy(double totalMoney, User u) {
        return totalMoney <= getWalletByUId(u);
    }

    //Bơm tiền
    private void AddWallet(int uid) {
        String sql = "UPDATE [dbo].[wallet]\n"
                + "   SET wallet = 99999"
                + " WHERE uid = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, uid);
            st.executeUpdate();

        } catch (SQLException e) {
        }
    }

    //update quantity=1
    private void testCart(String name) {
        String sql = "UPDATE [dbo].[Product]\n"
                + "   SET [quantity] = 1\n"
                + " WHERE name =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.executeUpdate();

        } catch (SQLException e) {
        }
    }

    public boolean checkValidProduct(Product p) {
        String sql = "select quantity from product where pid =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, p.getPid());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int numberP = rs.getInt("quantity");
                if (numberP >= p.getQuantity()) {
                    return true;
                }
            }
        } catch (SQLException e) {
        }
        return false;
    }
    //validbuy = true thì trừ sản phẩm rồi thêm vào order

    public static void main(String[] args) {
        OrderDAO ord = new OrderDAO();
        ord.AddWallet(4);//user 123
        ord.AddWallet(5);//user1 123
        ord.testCart("Basic T-shirt");
//        UserDAO ud = new UserDAO();
//        LocalDate curDate = java.time.LocalDate.now();
//        String date = curDate.toString();
//        System.out.println(date);
        System.out.println(ord.getOrderDetail(4).size());
    }
}
