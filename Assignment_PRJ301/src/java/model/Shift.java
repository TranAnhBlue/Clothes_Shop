/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.*;


/**
 *
 * @author lenovo
 */
public class Shift {
    private int id;
    private String name;
    private Time start;
    private Time end;
    private Salaries salary;  // Thêm thuộc tính salary

    public Salaries getSalary() {
        return salary;
    }

    public void setSalary(Salaries salary) {
        this.salary = salary;
    }

    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    

        
}
