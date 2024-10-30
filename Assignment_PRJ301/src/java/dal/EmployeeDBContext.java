/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dal.DBContext;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Department;
import model.Employee;
import model.Salaries;

/**
 *
 * @author lenovo
 */
public class EmployeeDBContext extends DBContext<Employee> {

    public ArrayList<Employee> search(Integer eid, String name, Boolean gender, Date from, Date to, String address, Integer did, String phonenumber, Integer sid) {
        String sql = "SELECT e.eid, e.ename, d.did, d.dname, e.phonenumber, e.address, s.sid, s.slevel, s.salary, e.gender, e.dob\n"
                + "FROM Employees e\n"
                + "INNER JOIN Departments d ON e.did = d.did\n"
                + "INNER JOIN Salaries s ON s.sid = e.sid\n"
                + "WHERE 1=1";
        ArrayList<Employee> emps = new ArrayList<>();
        ArrayList<Object> paramValues = new ArrayList<>();

        // Điều kiện tìm kiếm theo id
        if (eid != null) {
            sql += " AND e.eid = ?";
            paramValues.add(eid);
        }
        // Điều kiện tìm kiếm theo tên (LIKE)
        if (name != null && !name.trim().isEmpty()) {
            sql += " AND e.ename LIKE ?";
            paramValues.add("%" + name + "%"); // Sử dụng % trước và sau tên
        }
        // Điều kiện tìm kiếm theo giới tính
        if (gender != null) {
            sql += " AND e.gender = ?";
            paramValues.add(gender ? 1 : 0); // Chuyển boolean thành 1 (nam) và 0 (nữ)
        }
        // Điều kiện tìm kiếm theo địa chỉ (LIKE)
        if (address != null && !address.trim().isEmpty()) {
            sql += " AND e.address LIKE ?";
            paramValues.add("%" + address + "%"); // Sử dụng % trước và sau địa chỉ
        }
        // Điều kiện tìm kiếm theo số điện thoại
        if (phonenumber != null && !phonenumber.trim().isEmpty()) {
            sql += " AND e.phonenumber LIKE ?";
            paramValues.add("%" + phonenumber + "%"); // Tìm kiếm giống LIKE với số điện thoại
        }
        // Điều kiện tìm kiếm theo ngày sinh từ
        if (from != null) {
            sql += " AND e.dob >= ?";
            paramValues.add(from);
        }
        // Điều kiện tìm kiếm theo ngày sinh đến
        if (to != null) {
            sql += " AND e.dob <= ?";
            paramValues.add(to);
        }

        // Điều kiện tìm kiếm theo mã phòng ban
        if (did != null) {
            sql += " AND d.did = ?";
            paramValues.add(did);
        }
        // Điều kiện tìm kiếm theo Salary ID (sid)
        if (sid != null) {
            sql += " AND s.sid = ?";
            paramValues.add(sid);
        }
        PreparedStatement stm = null;

        try {
            // Chuẩn bị câu truy vấn với các tham số
            stm = connection.prepareStatement(sql);
            for (int i = 0; i < paramValues.size(); i++) {
                stm.setObject((i + 1), paramValues.get(i));
            }

            // Thực thi truy vấn
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getInt("eid"));
                e.setName(rs.getNString("ename"));
                e.setGender(rs.getBoolean("gender")); // Đọc giá trị giới tính
                e.setDob(rs.getDate("dob"));
                e.setAddress(rs.getNString("address"));
                e.setPhonenumber(rs.getNString("phonenumber")); // Đọc số điện thoại

                Department d = new Department();
                d.setId(rs.getInt("did"));
                d.setName(rs.getNString("dname"));

                e.setDept(d); // Gán phòng ban cho nhân viên

                Salaries s = new Salaries();
                s.setId(rs.getInt("sid")); // Đọc Salary ID
                s.setLevel(rs.getNString("slevel"));
                s.setSalary(rs.getBigDecimal("salary"));

                e.setSals(s); // Gán Salary ID cho nhân viên

                emps.add(e);

            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return emps;
    }

    public void insert(Employee model) {
        try {
            connection.setAutoCommit(false);
            String sql_insert = "INSERT INTO [Employees]\n"
                    + "           ([ename]\n"
                    + "           ,[did]\n"
                    + "           ,[phonenumber]\n"
                    + "           ,[address]\n"
                    + "           ,[sid]\n"
                    + "           ,[gender]\n"
                    + "           ,[dob])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            String sql_getEid = "SELECT @@IDENTITY as eid";

            PreparedStatement stm_insert = connection.prepareCall(sql_insert);

            stm_insert = connection.prepareStatement(sql_insert);
            stm_insert.setNString(1, model.getName());
            stm_insert.setInt(2, model.getDept().getId());
            stm_insert.setNString(3, model.getPhonenumber());
            stm_insert.setNString(4, model.getAddress());
            stm_insert.setInt(5, model.getSals().getId());
            stm_insert.setBoolean(6, model.isGender());
            stm_insert.setDate(7, model.getDob());
            stm_insert.executeUpdate();

            PreparedStatement stm_getEid = connection.prepareStatement(sql_getEid);
            ResultSet rs = stm_getEid.executeQuery();
            if (rs.next()) {
                model.setId(rs.getInt("eid"));
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public boolean isPhoneNumberExists(String phoneNumber) {
        String sql = "SELECT COUNT(*) FROM Employees WHERE phonenumber = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, phoneNumber);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException ex) {

            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public void update(Employee model) {
//        e.eid, e.ename, d.did, d.dname, e.phonenumber, e.address, s.sid, s.slevel, s.salary, e.gender, e.dob
        String sql_update = "UPDATE [Employees]\n"
                + "   SET [ename] = ?,\n"
                + "       [did] = ?,\n"
                + "       [phonenumber] = ?,\n"
                + "       [address] = ?,\n"
                + "       [sid] = ?,\n"
                + "       [gender] = ?,\n"
                + "       [dob] = ?\n"
                + " WHERE eid = ?";
        PreparedStatement stm_update = null;
        try {
            stm_update = connection.prepareStatement(sql_update);
            stm_update.setNString(1, model.getName());
            stm_update.setInt(2, model.getDept().getId());
            stm_update.setNString(3, model.getPhonenumber());
            stm_update.setNString(4, model.getAddress());
            stm_update.setInt(5, model.getSals().getId());
            stm_update.setBoolean(6, model.isGender());
            stm_update.setDate(7, model.getDob());
            stm_update.setInt(8, model.getId()); // khóa chính
            stm_update.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Close the prepared statement and database connection
            if (stm_update != null) {
                try {
                    stm_update.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void delete(Employee model) {
        String sql_update = "DELETE FROM Employees\n"
                + " WHERE eid = ?";
        PreparedStatement stm_update = null;
        try {
            stm_update = connection.prepareStatement(sql_update);
            stm_update.setInt(1, model.getId());
            stm_update.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class
                    .getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBContext.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ArrayList<Employee> list() {
        ArrayList<Employee> emps = new ArrayList<>();
        PreparedStatement stm = null;
        try {
            String sql = "SELECT e.eid, e.ename, d.dname, e.phonenumber, e.[address], s.salary, e.gender, e.dob from Employees e\n"
                    + "INNER JOIN Departments d on e.did = d.did\n"
                    + "INNER JOIN Salaries s on e.[sid] = s.[sid]\n"
                    + "WHERE 1=1";

            stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getInt("eid"));
                e.setName(rs.getNString("ename"));
                e.setGender(rs.getBoolean("gender")); // Đọc giá trị giới tính
                e.setDob(rs.getDate("dob"));
                e.setAddress(rs.getNString("address"));
                e.setPhonenumber(rs.getNString("phonenumber")); // Đọc số điện thoại

                Department d = new Department();
                d.setName(rs.getNString("dname"));
                e.setDept(d); // Gán phòng ban cho nhân viên

                Salaries s = new Salaries();
                s.setSalary(rs.getBigDecimal("salary"));

                e.setSals(s); // Gán Salary ID cho nhân viên

                emps.add(e);

            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBContext.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return emps;
    }

    // Phương thức đếm tổng số nhân viên
    public int getEmployeeCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM Employees";  // Replace 'Employees' with your actual table name
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, e);
        }
        return count;
    }

    @Override
    public Employee get(int id) {
        PreparedStatement stm = null;
        try {
            String sql = "SELECT \n"
                    + "	e.eid,e.ename, e.did, e.phonenumber,e.[address], s.sid, e.gender, e.dob,\n"
                    + "FROM Employees e INNER JOIN Departments d ON d.did = e.did\n"
                    + "INNER JOIN Salaries s on s.[sid] = e.[sid]\n"
                    + "WHERE e.eid = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getInt("eid"));
                e.setName(rs.getNString("ename"));
                e.setPhonenumber(rs.getNString("phonenumber"));
                e.setAddress(rs.getNString("address"));
                e.setGender(rs.getBoolean("gender"));
                e.setDob(rs.getDate("dob"));

                Department d = new Department();
                d.setId(rs.getInt("did"));
                d.setName(rs.getString("dname"));
                e.setDept(d);

                Salaries s = new Salaries();
                s.setId(rs.getInt("sid"));
                s.setLevel(rs.getNString("slevel"));
                s.setSalary(rs.getBigDecimal("salary"));
                e.setSals(s);

                return e;

            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBContext.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

}
