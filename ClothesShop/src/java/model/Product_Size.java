package model;

/**
 * Represents the relationship between a product and its sizes.
 */
public class Product_Size {
    private int sid; // Size ID
    private int pid; // Product ID

    // Constructor
    public Product_Size(int sid, int pid) {
        this.sid = sid;
        this.pid = pid;
    }

    // Getters and Setters
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "Product_Size{" +
                "sid=" + sid +
                ", pid=" + pid +
                '}';
    }
}
