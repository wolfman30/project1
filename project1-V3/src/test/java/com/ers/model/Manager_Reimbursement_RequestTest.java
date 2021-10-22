package com.ers.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Date;
import java.sql.Timestamp;

public class Manager_Reimbursement_RequestTest
{
    private Reimbursement_Request req;
    /**
     * Initialize both employee and manager reimbursement requests
     */
    @Before
    public void init() {
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        req = new Manager_Reimbursement_Request(2, "Andrew", "Wolf",
                "August", "Duet", "Needs work", 56.21,
                "Just 'cause", "pending", ts, false);
    }
    @Test
    public void testConstructor()
    {
        assertNotNull(req);
    }
}
