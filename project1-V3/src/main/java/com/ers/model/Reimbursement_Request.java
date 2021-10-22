package com.ers.model;

import java.sql.Timestamp;

/**
 * This is the base class for either the manager's view of the reimbursement request or
 * the employee's view of the reimbursement request: the manager's version of a
 * reimbursement request will have more details such as the employee names,
 * the manager who resolved them, and manager comments
 */
public abstract class Reimbursement_Request
{
    private int id;
    private String employeeFirstName;
    private String employeeLastName;
    private String managerFirstName;
    private String managerLastName;
    private int employeeId;
    private int managerId;
    private String managerComment;
    private double amount;
    private String reason;
    private String status;
    private Timestamp timeCreated;
    private boolean isApproved;

    public Reimbursement_Request() {}

    public Reimbursement_Request(int id, String empFirstName, String employeeLastName,
                                 String managerFirstName, String managerLastName,
                                 String managerComment, double amount, String reason,
                                 String status, Timestamp timeCreated, boolean isApproved)
    {
        this.id = id;
        this.employeeFirstName = empFirstName;
        this.employeeLastName = employeeLastName;
        this.managerFirstName = managerFirstName;
        this.managerLastName = managerLastName;
        this.managerComment = managerComment;
        this.amount = amount;
        this.reason = reason;
        this.status = status;
        this.timeCreated = timeCreated;
        this.isApproved = isApproved;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String empFirstName) {
        this.employeeFirstName = empFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public String getManagerFirstName() {
        return managerFirstName;
    }

    public void setManagerFirstName(String managerFirstName) {
        this.managerFirstName = managerFirstName;
    }

    public String getManagerLastName() {
        return managerLastName;
    }

    public void setManagerLastName(String managerLastName) {
        this.managerLastName = managerLastName;
    }

    public String getManagerComment() {
        return managerComment;
    }

    public void setManagerComment(String managerComment) {
        this.managerComment = managerComment;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public Timestamp getTimeCreated()
    {
        return this.timeCreated;
    }

    public void setTimeCreated(Timestamp timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public int getEmployeeId()
    {
        return employeeId;
    }

    public void setEmployeeId(int employeeId)
    {
        this.employeeId = employeeId;
    }

    public int getManagerId()
    {
        return managerId;
    }

    public void setManagerId(int managerId)
    {
        this.managerId = managerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }



}
