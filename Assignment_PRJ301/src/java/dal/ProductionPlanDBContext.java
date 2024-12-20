/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dal.DBContext;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ProductionPlan;
import java.sql.*;
import java.util.List;
import model.Department;
import model.Product;
import model.ProductionPlanHeader;

/**
 *
 * @author lenovo
 */
public class ProductionPlanDBContext extends DBContext<ProductionPlan> {
    
//    public static void main(String[] args) {
//        ProductionPlanDBContext dbContext = new ProductionPlanDBContext();
//        int count = dbContext.list();
//        System.out.println("Total ProductionPlans: " + count);
//    }

    public List<ProductionPlan> getPlans(String name, String month, String year, String deptId) {
        List<ProductionPlan> plans = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT p.plid, p.plname, p.startdate, p.enddate, d.did, d.dname\n"
                + "FROM Plans p\n"
                + "JOIN Departments d ON p.did = d.did\n"
                + "WHERE p.isDeleted = 0"
        );

        // Build query dynamically based on filter parameters
        if (name != null && !name.isEmpty()) {
            sql.append(" AND p.plname LIKE ?");
        }
        if (month != null && !month.isEmpty() && year != null && !year.isEmpty()) {
            sql.append(" AND MONTH(p.startdate) = ? AND YEAR(p.startdate) = ?");
        } else if (year != null && !year.isEmpty()) {
            sql.append(" AND YEAR(p.startdate) = ?");
        }
        if (deptId != null && !deptId.isEmpty()) {
            sql.append(" AND d.did = ?");
        }

        try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            int index = 1;
            if (name != null && !name.isEmpty()) {
                ps.setString(index++, "%" + name + "%");
            }
            if (month != null && !month.isEmpty() && year != null && !year.isEmpty()) {
                ps.setInt(index++, Integer.parseInt(month));
                ps.setInt(index++, Integer.parseInt(year));
            } else if (year != null && !year.isEmpty()) {
                ps.setInt(index++, Integer.parseInt(year));
            }
            if (deptId != null && !deptId.isEmpty()) {
                ps.setString(index++, deptId);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductionPlan plan = new ProductionPlan();
                plan.setId(rs.getInt("plid"));
                plan.setName(rs.getString("plname"));
                plan.setStart(rs.getDate("startdate"));
                plan.setEnd(rs.getDate("enddate"));

                // Set the associated department
                Department dept = new Department();
                dept.setId(rs.getInt("did"));
                dept.setName(rs.getString("dname"));
                plan.setDept(dept);

                plans.add(plan);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider proper logging here
        }

        return plans;
    }
    
    public ArrayList<ProductionPlan> search(Integer plid, String plname, Date start, Date end, Integer did) {
        String sql = "select p.plid, p.plname, p.startdate, p.enddate, d.dname from Plans p\n"
                + "inner join Departments d on d.did = p.did\n"
                + "Where 1=1";
        ArrayList<ProductionPlan> pps = new ArrayList<>();
        ArrayList<Object> paramValues = new ArrayList<>();

        // điều kiện tìm kiếm theo id
        if (plid != null) {
            sql += " AND p.plid = ?";
            paramValues.add(plid);
        }

        // điều kiện tìm kiếm theo tên
        if (plname != null && !plname.trim().isEmpty()) {
            sql += " AND p.plname LIKE ?";
            paramValues.add("%" + plname + "%");
        }

        // điều kiện tìm kiếm ngày bắt đầu làm việc
        if (start != null) {
            sql += " AND p.startdate >= ?";
            paramValues.add(start);
        }

        // điều kiện tìm kiếm ngày kết thúc làm việc
        if (end != null) {
            sql += " AND p.enddate <= ?";
            paramValues.add(end);
        }

        // điều kiện tìm kiếm theo mã phòng ban
        if (did != null) {
            sql += " AND d.did = ?";
            paramValues.add(did);
        }
        PreparedStatement stm = null;

        try {
            // Chuẩn bị câu truy vấn với các tham số
            // ví dụ 
            stm = connection.prepareStatement(sql);
            for (int i = 0; i < paramValues.size(); i++) {
                stm.setObject((i + 1), paramValues.get(i));
            }

            // Thực thi truy vấn
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                ProductionPlan plan = new ProductionPlan();
                plan.setId(rs.getInt("plid"));
                plan.setName(rs.getNString("plname"));
                plan.setStart(rs.getDate("startdate"));
                plan.setEnd(rs.getDate("enddate"));

                Department d = new Department();
                d.setName(rs.getNString("dname"));
                plan.setDept(d);

                pps.add(plan);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductionPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductionPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return pps;
    }

    @Override
    public void insert(ProductionPlan model) {
        try {
            connection.setAutoCommit(false);
            String sql_insert_plan = "INSERT INTO [Plans]\n"
                    + "           ([plname]\n"
                    + "           ,[startdate]\n"
                    + "           ,[enddate]\n"
                    + "           ,[did])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            PreparedStatement stm_insert_plan = connection.prepareStatement(sql_insert_plan);
            stm_insert_plan.setString(1, model.getName());
            stm_insert_plan.setDate(2, model.getStart());
            stm_insert_plan.setDate(3, model.getEnd());
            stm_insert_plan.setInt(4, model.getDept().getId());
            stm_insert_plan.executeUpdate();

            String sql_select_plan = "SELECT @@IDENTITY as plid";
            PreparedStatement stm_select_plan = connection.prepareStatement(sql_select_plan);
            ResultSet rs = stm_select_plan.executeQuery();
            if (rs.next()) {
                model.setId(rs.getInt("plid"));
            }

            String sql_insert_header = "INSERT INTO [PlanHeaders]\n"
                    + "           ([plid]\n"
                    + "           ,[pid]\n"
                    + "           ,[quantity]\n"
                    + "           ,[estimatedeffort])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            for (ProductionPlanHeader header : model.getHeaders()) {
                PreparedStatement stm_insert_header = connection.prepareStatement(sql_insert_header);
                stm_insert_header.setInt(1, model.getId());
                stm_insert_header.setInt(2, header.getProduct().getId());
                stm_insert_header.setInt(3, header.getQuantity());
                stm_insert_header.setFloat(4, header.getEstimatedeffort());
                stm_insert_header.executeUpdate();
            }

            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ProductionPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ProductionPlanDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ProductionPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductionPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void update(ProductionPlan model) {
        try {
            String sql_update_plan = "UPDATE [Plans] SET [plname] = ?, [startdate] = ?, [enddate] = ?, [did] = ? WHERE [plid] = ?";
            PreparedStatement stm_update_plan = connection.prepareStatement(sql_update_plan);
            stm_update_plan.setString(1, model.getName());
            stm_update_plan.setDate(2, model.getStart());
            stm_update_plan.setDate(3, model.getEnd());
            stm_update_plan.setInt(4, model.getDept().getId());
            stm_update_plan.setInt(5, model.getId());
            stm_update_plan.executeUpdate();

            // Assuming we need to update headers as well
            String sql_delete_headers = "DELETE FROM [PlanHeaders] WHERE [plid] = ?";
            PreparedStatement stm_delete_headers = connection.prepareStatement(sql_delete_headers);
            stm_delete_headers.setInt(1, model.getId());
            stm_delete_headers.executeUpdate();

            String sql_insert_header = "INSERT INTO [PlanHeaders] ([plid], [pid], [quantity], [estimatedeffort]) VALUES (?, ?, ?, ?)";
            for (ProductionPlanHeader header : model.getHeaders()) {
                PreparedStatement stm_insert_header = connection.prepareStatement(sql_insert_header);
                stm_insert_header.setInt(1, model.getId());
                stm_insert_header.setInt(2, header.getProduct().getId());
                stm_insert_header.setInt(3, header.getQuantity());
                stm_insert_header.setFloat(4, header.getEstimatedeffort());
                stm_insert_header.executeUpdate();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductionPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductionPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void delete(ProductionPlan model) {
        try {
            String sql_delete_headers = "DELETE FROM [PlanHeaders] WHERE [plid] = ?";
            PreparedStatement stm_delete_headers = connection.prepareStatement(sql_delete_headers);
            stm_delete_headers.setInt(1, model.getId());
            stm_delete_headers.executeUpdate();

            String sql_delete_plan = "DELETE FROM [Plans] WHERE [plid] = ?";
            PreparedStatement stm_delete_plan = connection.prepareStatement(sql_delete_plan);
            stm_delete_plan.setInt(1, model.getId());
            stm_delete_plan.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProductionPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductionPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ArrayList<ProductionPlan> list() {
        ArrayList<ProductionPlan> plans = new ArrayList<>();
        ArrayList<ProductionPlanHeader> headers = new ArrayList<>();

        PreparedStatement stm = null;
        String sql = "select p.plid, plname,startdate,enddate,did,pr.pid,pname,quantity,estimatedeffort\n"
                + "from Plans p join PlanHeaders  h on p.plid=h.plid\n"
                + "join Products pr on pr.pid=h.pid";
        try {

            stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            ProductionPlan cPlan = new ProductionPlan();
            cPlan.setId(-1);
            while (rs.next()) {
                int plid = rs.getInt("plid");
                if (plid != cPlan.getId()) {
                    cPlan = new ProductionPlan();
                    cPlan.setId(plid);
                    cPlan.setName(rs.getString("plname"));
                    cPlan.setStart(rs.getDate("startdate"));
                    cPlan.setEnd(rs.getDate("enddate"));
                    plans.add(cPlan);

                    Department d = new Department();
                    d.setId(rs.getInt("did"));
                    cPlan.setDept(d);
                    headers = new ArrayList<>();

                }

                Product p = new Product();
                p.setId(rs.getInt("pid"));
                p.setName(rs.getString("pname"));

                ProductionPlanHeader h = new ProductionPlanHeader();
                h.setProduct(p);
                h.setQuantity(rs.getInt("quantity"));
                h.setEstimatedeffort(rs.getFloat("estimatedeffort"));
                headers.add(h);
                cPlan.setHeaders(headers);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductionPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductionPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return plans;
    }

    @Override
    public ProductionPlan get(int id) {
        PreparedStatement stm = null;
        String sql = "select p.plid,pname, plname,startdate,enddate,did,h.phid,h.pid,quantity,estimatedeffort\n"
                + "from Plans p join PlanHeaders h on p.plid=h.plid\n"
                + "join Products pr on pr.pid=h.pid\n"
                + "where p.plid=?";
        ArrayList<ProductionPlanHeader> headers = new ArrayList<>();
        ProductionPlan cPlan = new ProductionPlan();
        try {

            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            cPlan.setId(-1);
            while (rs.next()) {
                int plid = rs.getInt("plid");
                if (plid != cPlan.getId()) {
                    cPlan = new ProductionPlan();
                    cPlan.setId(plid);
                    cPlan.setName(rs.getString("plname"));
                    cPlan.setStart(rs.getDate("startdate"));
                    cPlan.setEnd(rs.getDate("enddate"));

                    Department d = new Department();
                    d.setId(rs.getInt("did"));
                    cPlan.setDept(d);
                    headers = new ArrayList<>();

                }

                Product p = new Product();
                p.setId(rs.getInt("pid"));
                p.setName(rs.getString("pname"));

                ProductionPlanHeader h = new ProductionPlanHeader();
                h.setProduct(p);
                h.setId(rs.getInt("phid"));
                h.setQuantity(rs.getInt("quantity"));
                h.setEstimatedeffort(rs.getFloat("estimatedeffort"));
                headers.add(h);
                cPlan.setHeaders(headers);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductionPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cPlan;
    }
    
    public List<ProductionPlanHeader> getHeadersByPlanId(int planId) {
        List<ProductionPlanHeader> headers = new ArrayList<>();
        String sql = "SELECT ph.phid, ph.plid, ph.pid, ph.quantity, ph.estimatedeffort, "
                + "p.pname AS product_name "
                + "FROM PlanHeaders ph "
                + "JOIN Products p ON ph.pid = p.pid "
                + "WHERE ph.plid = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, planId); // Set the planId to the query
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ProductionPlanHeader header = new ProductionPlanHeader();
                header.setId(rs.getInt("phid")); // Set phid
                // header.getPlan().setId(rs.getInt("plid")); // Set plid
                header.setQuantity(rs.getInt("quantity")); // Set quantity
                header.setEstimatedeffort(rs.getFloat("estimatedeffort")); // Set estimatedeffort

                // Set associated product
                Product product = new Product();
                product.setId(rs.getInt("pid")); // Set product id (pid)
                product.setName(rs.getString("product_name")); // Set product name (pname)
                header.setProduct(product); // Link product to the header

                headers.add(header); // Add to headers list
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return headers;
    }

}
