/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.PlanDetail;
import model.Product;

/**
 *
 * @author lenovo
 */
public class PlanDetailDBContext extends DBContext<PlanDetail> {

    public void insert(List<PlanDetail> details) {
        String sql = "INSERT INTO PlanDetails (phid, sid, date, quantity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (PlanDetail detail : details) {
                ps.setInt(1, detail.getHeader().getId());
                ps.setInt(2, detail.getSid());
                ps.setDate(3, detail.getDate());
                ps.setInt(4, detail.getQuantity());
                ps.addBatch();
            }
            ps.executeBatch(); // Execute batch for better performance when inserting multiple rows
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(PlanDetail model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(PlanDetail model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(PlanDetail model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<PlanDetail> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PlanDetail get(int pdid) {
        PlanDetail planDetail = null;
        String sql = "SELECT pd.pdid, pd.quantity, pd.date, pd.sid, ph.pid, p.pname "
                + "FROM PlanDetails pd "
                + "JOIN PlanHeaders ph ON pd.phid = ph.phid "
                + "JOIN Products p ON ph.pid = p.pid "
                + "WHERE pd.pdid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, pdid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                planDetail = new PlanDetail();
                planDetail.setPdid(rs.getInt("pdid"));
                planDetail.setQuantity(rs.getInt("quantity"));
                planDetail.setDate(rs.getDate("date"));
                planDetail.setSid(rs.getInt("sid"));

                Product product = new Product();
                product.setId(rs.getInt("pid"));
                product.setName(rs.getString("pname"));
                planDetail.setProduct(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return planDetail;
    }
}
