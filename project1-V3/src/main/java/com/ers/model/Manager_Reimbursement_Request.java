package com.ers.model;

import java.sql.Timestamp;

/**
 * Reimbursement requests meant for managers to see
 */
public class Manager_Reimbursement_Request extends Reimbursement_Request
{

    public Manager_Reimbursement_Request() {};

    public Manager_Reimbursement_Request(int id, String employeeFirstName, String employeeLastName, String managerFirstName,
                                         String managerLastName, String managerComment, double amount, String reason,
                                         String status, Timestamp timeCreated, boolean isApproved)
    {
        super(id, employeeFirstName, employeeLastName, managerFirstName, managerLastName, managerComment, amount, reason, status, timeCreated, isApproved);
    }


}
