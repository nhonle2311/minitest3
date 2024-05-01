package org.example.minitest3.model;

import java.time.LocalDate;

public class Employee {
    private String id;
    private String fullname;
    private String address;
    private LocalDate dob;
    private double salary;

    public Employee() {
    }

    public Employee(String id, String fullname, String address, LocalDate dob, double salary) {
        this.id = id;
        this.fullname = fullname;
        this.address = address;
        this.dob = dob;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
