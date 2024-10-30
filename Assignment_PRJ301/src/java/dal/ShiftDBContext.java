/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Shift;
import java.sql.*;
import model.Salaries;

/**
 *
 * @author lenovo
 */
public class ShiftDBContext extends DBContext<Shift> {

    @Override
    public void insert(Shift shift) {
        String sql = "INSERT INTO Shifts (name, start, end) VALUES (?, ?, ?)";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, shift.getName());
//            stm.setDate(2, shift.getStart());
//            stm.setDate(3, shift.getEnd());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Shift model) {
        String sql = "UPDATE Shifts SET name = ?, start = ?, end = ? WHERE id = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, model.getName());
//            stm.setDate(2, model.getStart());
//            stm.setDate(3, model.getEnd());
            stm.setInt(4, model.getId());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Shift model) {
        String sql = "DELETE FROM Shifts WHERE id = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, model.getId());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Shift> list() {
        ArrayList<Shift> shifts = new ArrayList<>();
        String sql = "  select s.[sid], s.sname, s.starttime, s.endtime, sal.slevel, sal.salary from Shifts s \n"
                + "  left join Salaries sal on s.[sid] = sal.[sid]";  // Giả sử shift_id là khóa ngoại trong Salaries

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Shift shift = new Shift();
            shift.setId(rs.getInt("sid"));
            shift.setName(rs.getNString("sname"));
            shift.setStart(rs.getTime("starttime"));
            shift.setEnd(rs.getTime("endtime"));
            
            // Đối tượng Salary
            Salaries sals = new Salaries();
            sals.setLevel(rs.getNString("slevel"));
            sals.setSalary(rs.getBigDecimal("salary"));

            // Gán salary vào shift
            shift.setSalary(sals);

            shifts.add(shift);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return shifts;
}

    @Override
    public Shift get(int id) {
        String sql = "SELECT * FROM Shifts WHERE id = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Shift shift = new Shift();
                shift.setId(rs.getInt("id"));
                shift.setName(rs.getString("name"));
//                shift.setStart(rs.getDate("start"));
//                shift.setEnd(rs.getDate("end"));
                return shift;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
