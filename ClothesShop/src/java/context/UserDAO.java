/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Role;

/**
 *
 * @author lenovo
 */
public class UserDAO extends DBContext {

    //_____________________________Reset Password_____________________________
    public void changePassByEmail(String email, String pass) {
        String sql = "UPDATE [dbo].[User]\n"
                + "   SET \n"
                + "[password] = ?\n"
                + " WHERE [email] = ?";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, pass);
            pst.setString(2, email);
            pst.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void changePass(String username, String pass) {
        String sql = "UPDATE [dbo].[User]\n"
                + "   SET \n"
                + "[password] = ?\n"
                + " WHERE [username] = ?";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, pass);
            pst.setString(2, username);
            pst.executeUpdate();
        } catch (SQLException e) {
        }
    }

    //___________________________Active User_________________________
    // set active of account
    public void changeActive(int uid, int active) {
        String sql = "UPDATE [dbo].[User]\n"
                + "   SET [active] = ?\n"
                + " WHERE [uid] =?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, active);
            ps.setInt(2, uid);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    //set role of account
    public void changeRole(int uid, int role) {
        String sql = "UPDATE [dbo].[User]\n"
                + "   SET [role] = ?\n"
                + " WHERE [uid] =?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, uid);
            ps.setInt(2, role);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }
    public void changeRole2(String username, int role) {
        String sql = "UPDATE [dbo].[User]\n"
                + "   SET [rid] = ?\n"
                + " WHERE [username] =?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, role);
            ps.setString(2, username);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    //get all user
    public List<User> getListU(int index, int quantity, String sortField, String sortOrder) {
        List<User> listU = new ArrayList<>();
        String sql = "SELECT [uid], [fullName], [phone], [address], [email], [username], [password], [dob], [gender], [rid], [active] "
                + "FROM [User] ";

        // Add sorting
        if (sortField != null && sortOrder != null) {
            sql += "ORDER BY " + sortField + " " + sortOrder + " ";
        } else {
            sql += "ORDER BY uid ";
        }

        sql += "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, (index - 1) * quantity);
            stm.setInt(2, quantity);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                listU.add(new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getInt(10),
                        rs.getInt(11)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listU;
    }

    //search user
//Name Phone Email Username
    public List<User> searchU(String key, int rid, int active, int index, int quantity, String sortField, String sortOrder) {
        List<User> listU = new ArrayList<>();
        String sql = "SELECT [uid], [fullName], [phone], [address], [email], [username], [password], [dob], [gender], [rid], [active] "
                + "FROM [dbo].[User] u "
                + "WHERE (u.fullName LIKE ? OR u.phone LIKE ? OR u.email LIKE ? OR u.username LIKE ?) ";
        if (rid != -1) {
            sql += "AND u.rid = ? ";
        }
        if (active != -1) {
            sql += "AND u.active = ? ";
        }

        // Add sorting
        if (sortField != null && sortOrder != null) {
            sql += "ORDER BY u." + sortField + " " + sortOrder + " ";
        } else {
            sql += "ORDER BY u.uid "; // Default sorting
        }

        sql += "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + key + "%");
            stm.setString(2, "%" + key + "%");
            stm.setString(3, "%" + key + "%");
            stm.setString(4, "%" + key + "%");
            int paramIndex = 5;
            if (rid != -1) {
                stm.setInt(paramIndex++, rid);
            }
            if (active != -1) {
                stm.setInt(paramIndex++, active);
            }
            int offset = Math.max(0, (index - 1) * quantity); // Ensure offset is non-negative
            stm.setInt(paramIndex++, offset);
            stm.setInt(paramIndex, quantity);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                listU.add(new User(rs.getInt("uid"),
                        rs.getString("fullName"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("dob"),
                        rs.getInt("gender"),
                        rs.getInt("rid"),
                        rs.getInt("active")));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception properly
        }

        return listU;
    }

    //get all role
    public List<Role> listRole() {
        List<Role> listR = new ArrayList<>();
        String sql = "select * from [Role]";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                listR.add(new Role(rs.getInt(1),
                        rs.getString(2)));
            }
        } catch (SQLException e) {
        }
        return listR;
    }

    //_____________________________________Register Account______________________________
    //resgister with customer
    public void registerAcc(String name, String phone, String address, String email, String username, String password, String dbo, int gender) {
        String sql = "INSERT INTO [dbo].[User]\n"
                + "           ([fullName]\n"
                + "           ,[phone]\n"
                + "           ,[address]\n"
                + "           ,[email]\n"
                + "           ,[username]\n"
                + "           ,[password]\n"
                + "           ,[dob]\n"
                + "           ,[gender])\n"
                + "     VALUES\n"
                + "    (?, ?, ?, ?, ?, ?, ?, ?);";
        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, address);
            ps.setString(4, email);
            ps.setString(5, username);
            ps.setString(6, password);
            ps.setString(7, dbo);
            ps.setInt(8, gender);
            ps.executeUpdate();

        } catch (SQLException e) {
        }
    }

    public User checkUser(String user, String pass) {
        String sql = "SELECT  [uid]\n"
                + "      ,[fullName]\n"
                + "      ,[phone]\n"
                + "      ,[address]\n"
                + "      ,[email]\n"
                + "      ,[username]\n"
                + "      ,[password]\n"
                + "      ,[dob]\n"
                + "      ,[gender]\n"
                + "      ,[rid]\n"
                + "      ,[active]\n"
                + "  FROM [ClothesShop].[dbo].[User]\n"
                + "  where username =? and password=? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getInt(10),
                        rs.getInt(11));
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public boolean checkUsername(String username, String email) {
        String sql = "SELECT [username] FROM [dbo].[User] WHERE [username] = ? or [email] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Username already exists in the database
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Print the stack trace for debugging
        }
        return true;
    }

    //sent code to email
    public static boolean verifyCode(String mailTo, String code) {
        final String from = "champion22062004@gmail.com";
        final String password = "uqqy hrpu monf efam";

        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com"); // Use the correct SMTP server
        props.setProperty("mail.smtp.port", "587");//Cổng smtp tls-587
        props.setProperty("mail.smtp.auth", "true");//xác định cần xác thực mail
        props.setProperty("mail.smtp.starttls.enable", "true");// Kích hoạt STARTTLS để bảo mật kết nối.

        //Authenticator: cugn cấp thông tin xác thực khi cần
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        //using mail api, content send to mail.
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo, false));
            msg.setSubject("verifile code");
            msg.setSentDate(new Date());
            String emailContent = "<html>"
                    + "<head>"
                    + "<style>"
                    + "p { font-size: 16px; }"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<h1>This is your code: <h1 style=\"color: #33ff33\">" + code + "</h1></h1>"
                    + "<p>Thank you for using our service.</p>"
                    + "</body>"
                    + "</html>";
            msg.setContent(emailContent, "text/html; charset=UTF-8");
            Transport.send(msg);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //cretate random code send to email
    public static String getCode() {
        Random rd = new Random();
        int number = rd.nextInt(1000000);
        return String.format("%06d", number);
    }

    //encode string  
    public static String toSHA1(String str) {
        String tim_ho = "kaisd#$%^&*(sg~~sda";
        String result = null;

        str = str + tim_ho;
        try {
            byte[] dataByte = str.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] hashBytes = md.digest(dataByte);
            result = Base64.getEncoder().encodeToString(hashBytes);
        } catch (Exception e) {
        }
        return result;

    }

    public void updateProfile(User user) {
         if (connection == null) {
        throw new IllegalStateException("Database connection is not initialized.");
    }
    
    if (user == null) {
        throw new IllegalArgumentException("User object cannot be null.");
    }

    String sql = "UPDATE [User] "
                + "SET [fullName] = ?, "
                + "[phone] = ?, "
                + "[address] = ?, "
                + "[email] = ?, "
                + "[dob] = ?, "
                + "[gender] = ? "
                + "WHERE [uid] = ?";
    
    try {
        // Chuyển đổi ngày tháng từ String sang java.sql.Date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = sdf.parse(user.getDob());
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, user.getFullName());
            stm.setString(2, user.getPhone());
            stm.setString(3, user.getAddress());
            stm.setString(4, user.getEmail());
            stm.setDate(5, sqlDate);
            stm.setInt(6, user.getGender());
            stm.setInt(7, user.getUid());

            int rowsUpdated = stm.executeUpdate();
            if (rowsUpdated == 0) {
                System.out.println("No user found with UID: " + user.getUid());
            } else {
                System.out.println("User profile updated successfully.");
            }
        }
    } catch (ParseException e) {
        System.err.println("Date parsing error: " + e.getMessage());
        e.printStackTrace();
    } catch (SQLException e) {
        System.err.println("SQL error: " + e.getMessage());
        e.printStackTrace();
    }
    }
    

    public static void main(String[] args) {
        UserDAO ud = new UserDAO();
//        User user = new User();
//        user.setFullName("Chiennnnn");
//        user.setDob("02/12/2002");
//        user.setAddress("123HoanKiem");
//        user.setGender(0);
//        user.setEmail("chien19042003@gmail.com");
//        user.setPhone("1234567890");
//        user.setUid(11);
//        user.setRid(1);
//        ud.updateProfile(user);
//        ud.verifyCode("chien19042003@gmail.com", ud.getCode());
//         System.out.println(ud.getCode());
//      System.out.println(ud.toSHA1("12345"));
//        ud.changePassByEmail("chien19042003@gmail.com", "1BvTL8F1vT5msPyDEcEhPR4m8po=");
        ud.changePass("user", "1BvTL8F1vT5msPyDEcEhPR4m8po=");
        ud.changePass("admin", "1BvTL8F1vT5msPyDEcEhPR4m8po=");

        ud.changeRole2("12312132123", 2);
//        System.out.println(ud.toSHA1("123"));
//        System.out.println(ud.checkUser("admin", ud.toSHA1("123")));

//        ud.registerAcc("123", "00000000", "123", "123", "123", "123", "1985-05-15", 1);
//        System.out.println(ud.checkUsername("admin"));
//        List<User> l = ud.getListU(2, 3);
//        System.out.println(l.size());
//        System.out.println(ud.count());
//        List<Role> r = ud.listRole();
//        for (Role role : r) {
//            System.out.println(role.getRid());
//        }
//        System.out.println(ud.checkUsername("user", ""));
    }
}
