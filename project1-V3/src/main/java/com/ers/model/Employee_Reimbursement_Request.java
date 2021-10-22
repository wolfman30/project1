package com.ers.model;

/**
 * Reimbursement requests meant for employees to see
 */
public class Employee_Reimbursement_Request extends Reimbursement_Request
{

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

}
