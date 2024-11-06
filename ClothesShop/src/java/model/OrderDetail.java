package model;

public class OrderDetail {
    private int oid; // Order ID
    private int pid; // Product ID
    private int numP; // Number of Products

    // Constructor
    public OrderDetail() {}

    public OrderDetail(int oid, int pid, int numP) {
        this.oid = oid;
        this.pid = pid;
        this.numP = numP;
    }

    // Getters and Setters
    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getNumP() {
        return numP;
    }

    public void setNumP(int numP) {
        this.numP = numP;
    }
}
