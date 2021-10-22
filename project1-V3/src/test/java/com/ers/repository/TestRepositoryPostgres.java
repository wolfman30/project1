package com.ers.repository;

import com.ers.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestRepositoryPostgres {

    private String emp_email;
    private String mgr_email;
    private Repository repo;

    @Before
    public void init() {

        emp_email = "wolfpassion20@gmail.com";
        mgr_email = "johnWick@revature.net";
    }

    @Test
    public void testSingleTon()
    {
        repo = RepositoryPostgres.getInstance();
        assertNotNull(repo);
        Repository repo1 = RepositoryPostgres.getInstance();
        assertTrue(repo == repo1);
    }
    @Test
    public void testGetView()
    {
        User user = RepositoryPostgres.getInstance().getUser("wolfpassion20@gmail.com");
        String actual = RepositoryPostgres.getInstance().getView(user).toString().substring(0,26);
        String expected = "[com.ers.model.Employee_Re";
        assertEquals(expected, actual);
    }

    /**
     * Tests the getUser() method in the concrete dao implementing class
     * to see if it successfully gets the user based on the email
     */
    @Test
    public void testGetUser()
    {
        User user = RepositoryPostgres.getInstance().getUser(emp_email);
        String actual = user.toString();
        String expected = "Employee{id=1, firstName='Janet', lastName='Doe', email='wolfpassion20@gmail.com', password='jane20password', userType='employee'}";
        assertEquals(actual, expected);

        User mgr = RepositoryPostgres.getInstance().getUser(mgr_email);
        String actual2 = mgr.toString();
        String expected2 = "Manager{id=4, firstName='John', lastName='Wick', email='johnWick@revature.net', password='jWick2022!enigma', userType='manager'}";
        assertEquals(actual2, expected2);
    }

    @Test
    public void testMgrUpdateOfRequest()
    {

    }
}
