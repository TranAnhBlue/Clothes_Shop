/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author lenovo
 */
public class Attendance {
    private Employee eid;
    private int actualQuantity;
    private BigDecimal anpha;
    private String note;

    // Getter và Setter cho các thuộc tính

    public Employee getEid() {
        return eid;
    }

    public void setEid(Employee eid) {
        this.eid = eid;
    }
        
    public int getActualQuantity() {
        return actualQuantity;
    }

    public void setActualQuantity(int actualQuantity) {
        this.actualQuantity = actualQuantity;
    }

    public BigDecimal getAnpha() {
        return anpha;
    }

    public void setAnpha(BigDecimal anpha) {
        this.anpha = anpha;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
