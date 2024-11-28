/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.ProductionPlanDetail;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PlanDetail;
import model.Product;
import model.ProductionPlanHeader;

/**
 *
 * @author ASUS
 */
public class ProductionPlanDetailDBContext extends DBContext<ProductionPlanDetail> {

    @Override
    public void insert(ProductionPlanDetail model) {
        String sql = "INSERT INTO [PlanDetails]\n"
                + "           ([phid]\n"
                + "           ,[sid]\n"
                + "           ,[date]\n"
                + "           ,[quantity])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        PreparedStatement stm = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, model.getHeader().getId());
            stm.setInt(2, model.getSid());
            stm.setDate(3, model.getDate());
            stm.setInt(4, model.getQuantity());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductionPlanDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
    }

    @Override
    public void update(ProductionPlanDetail model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(ProductionPlanDetail model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<ProductionPlanDetail> list() {
        String sql = "SELECT [pdid]\n"
                + "      ,[phid]\n"
                + "      ,[sid]\n"
                + "      ,[date]\n"
                + "      ,[quantity]\n"
                + "  FROM [PlanDetails]";
        ArrayList<ProductionPlanDetail> details= new ArrayList<>();
        PreparedStatement stm=null;
        try {
            stm= connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                ProductionPlanDetail detail=new ProductionPlanDetail();
                detail.setDate(rs.getDate("date"));
                detail.setQuantity(rs.getInt("quantity"));
                detail.setSid(rs.getInt("sid"));
                ProductionPlanHeader header= new ProductionPlanHeader();
                header.setId(rs.getInt("phid"));
                detail.setHeader(header);
                details.add(detail);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductionPlanDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductionPlanDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
        return details;
    }
    public static void main(String[] args) {
        ProductionPlanDBContext db = new ProductionPlanDBContext();
        System.out.println(db.list().size());
    }

    public List<PlanDetail> getDetailsByPlanId(int planId) {
        List<PlanDetail> details = new ArrayList<>();

        // SQL query to retrieve detailed shift information for a given plan ID
        String sql = "SELECT pd.pdid, pd.phid, pd.sid, pd.date, pd.quantity, pd.note, "
                + "s.sid, "
                + "p.pid AS product_id, p.pname AS product_name "
                + "FROM PlanDetails pd "
                + "JOIN PlanHeaders ph ON pd.phid = ph.phid "
                + "JOIN Products p ON ph.pid = p.pid "
                + "JOIN Shifts s ON pd.sid = s.sid "
                + "WHERE ph.plid = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, planId);  // Set the plan ID for the query
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Create a new PlanDetail object for each result row
                PlanDetail detail = new PlanDetail();
                detail.setPdid(rs.getInt("pdid")); // Set PlanDetail ID
                detail.setDate(rs.getDate("date")); // Set the date of the shift
                detail.setQuantity(rs.getInt("quantity")); // Set the quantity for the shift
                detail.setNote(rs.getString("note")); // Set the note if available

                detail.setSid(rs.getInt("sid"));

                // Set the associated product
                Product product = new Product();
                product.setId(rs.getInt("id")); // Set product ID
                product.setName(rs.getString("name")); // Set product name
                detail.setProduct(product);

                details.add(detail); // Add the detail to the list
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Consider logging the exception
        }

        return details;
    }
    
    @Override
    public ProductionPlanDetail get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    

}
