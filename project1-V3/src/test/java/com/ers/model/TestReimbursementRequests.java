package com.ers.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the Reimbursement_Request that models reimbursement requests made
 * by employees and managed by managers
 */
public class TestReimbursementRequests
{
    private Reimbursement_Request emp_reimbursement_req;
    private Reimbursement_Request mgr_reimbursement_req;

    /**
     * Initialize both employee and manager reimbursement requests
     */
    @Before
    public void init() {
        emp_reimbursement_req = new Employee_Reimbursement_Request();
        mgr_reimbursement_req = new Manager_Reimbursement_Request();
    }

    /**
     * Test the get and setter methods for id for both classes
     */
    @Test
    public void testGetSetId()
    {
        emp_reimbursement_req.setEmployeeId(2);
        mgr_reimbursement_req.setManagerId(3);
        int emp_expected = 2;
        int mgr_expected = 3;
        assertTrue(emp_expected == 2);
        assertTrue(mgr_expected == 3);
    }

    /**
     * Test the getter and setter for the reimbursement amount
     */
    @Test
    public void testGetSetRequestId() {
        emp_reimbursement_req.setAmount(100.05);
        mgr_reimbursement_req.setAmount(101.23);
        assertTrue(emp_reimbursement_req.getAmount() == 100.05);
        assertTrue(mgr_reimbursement_req.getAmount() == 101.23);
    }

    /**
     * Tests getter and setter methods for status of reimbursement
     */
    @Test
    public void testGetSetStatus()
    {
        emp_reimbursement_req.setStatus("resolved");
        mgr_reimbursement_req.setStatus("pending");
        assertEquals("resolved", emp_reimbursement_req.getStatus());
        assertEquals("pending", mgr_reimbursement_req.getStatus());
    }

    /**
     * Tests getter and setter methods approved
     */
    @Test
    public void testGetSetApproved()
    {
        emp_reimbursement_req.setApproved(false);
        mgr_reimbursement_req.setApproved(true);
        assertFalse(emp_reimbursement_req.isApproved());
        assertTrue(mgr_reimbursement_req.isApproved());
    }

    /**
     * Tests getter and setter for manager comments
     */
    @Test
    public void testGetSetMgrComment()
    {
        mgr_reimbursement_req.setManagerComment("Definitely not");
        mgr_reimbursement_req.getManagerComment();
        String expected = "Definitely not";
        assertEquals("Definitely not", mgr_reimbursement_req.getManagerComment());
    }
}
