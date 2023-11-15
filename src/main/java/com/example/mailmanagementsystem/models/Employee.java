package com.example.mailmanagementsystem.models;

public class Employee extends Person  {
    private int employeeID;
    private String role;


    public Employee(String firstName, String lastName, String phoneNumber, Address address, int employeeID, String role) {
        super(firstName, lastName, phoneNumber, address);
        this.employeeID = employeeID;
        this.role = role;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
