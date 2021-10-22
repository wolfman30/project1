package com.ers.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests methods for both User objects of Employee and Manager
 */
public class TestUser {

    User employee;
    User manager;

    /**
     * Sets up objects I'm going to use in every test to minimize lines
     * of code for tests that I really don't need to do, but want to do
     * because I want to pass the test coverage...yay!!!
     */
    @Before
    public void init()
    {
        employee = new Employee();
        manager = new Manager();

        employee.setId(1);
        employee.setEmail("wolfpassion20@gmail.com");
        employee.setFirstName("Andrew");
        employee.setLastName("Wolf");
        employee.setPassword("2022@AmazingCareer");
        employee.setUserType("employee");


        manager.setId(10);
        manager.setFirstName("August");
        manager.setLastName("Duet");
        manager.setEmail("duet@revature.net");
        manager.setPassword("RevatureTrainer4Ever");
        manager.setUserType("manager");
    }
    /**
     * Tests getter and setter methods for the id of the User
     */
    @Test
    public void testGetSetID()
    {
        int actual = employee.getId();
        int expected = 1;
        assertTrue(expected == actual);

        int actualId = manager.getId();
        int expectedId = 10;
        assertTrue(expectedId == actualId);

    }


    /**
     * Tests getter and setter method of the first name of User
     */
    @Test
    public void testGetSetFirstName()
    {
        String actual = employee.getFirstName();
        String expected = "Andrew";
        assertEquals(expected, actual);

        String actualFName = manager.getFirstName();
        String expectedFName = "August";
        assertEquals(expectedFName, actualFName);

    }

    /**
     * Tests the getter and setter method of the last name of User
     */
    @Test
    public void testGetSetLastName()
    {
        String actual = employee.getLastName();
        String expected = "Wolf";
        assertEquals(expected, actual);

        String actualLName = manager.getLastName();
        String expectedLName = "Duet";
        assertEquals(expectedLName, actualLName);
    }

    /**
     * Tests getter and setter methods for the User username which is an email
     */
    @Test
    public void testGetSetEmail()
    {
        String actual = employee.getEmail();
        String expected = "wolfpassion20@gmail.com";
        assertEquals(expected, actual);

        String actualEmail = manager.getEmail();
        String expectedEmail = "duet@revature.net";
        assertEquals(expectedEmail, actualEmail);
    }

    /**
     * Tests the getter and setter methods for the password of the User
     */
    @Test
    public void testGetSetPassword()
    {
        String actual = employee.getPassword();
        String expected = "2022@AmazingCareer";
        assertEquals(expected, actual);


        String actualPassword = manager.getPassword();
        String expectedPassword = "RevatureTrainer4Ever";
        assertEquals(expectedPassword, actualPassword);
    }

    /**
     * Tests the getter and setter methods for the type of user
     * which is either an Employee or Manager string
     */
    @Test
    public void testGetSetUserType()
    {
        String actual = employee.getUserType();
        String expected = "employee";
        assertEquals(actual, expected);

        String actualUserType = manager.getUserType();
        String expectedUserType = "manager";
        assertEquals(actualUserType, expectedUserType);
    }

    /**
     * Tests the getter and setter method of the toString in the User objects
     */
    @Test
    public void testToString()
    {
        String expected =  "Employee{id=1, firstName='Andrew', lastName='Wolf', email='wolfpassion20@gmail.com', password='2022@AmazingCareer', userType='employee'}";
        String actual = employee.toString();
        assertEquals(expected, actual);

        String expectedMgrToString = "Manager{id=10, firstName='August', lastName='Duet', email='duet@revature.net', password='RevatureTrainer4Ever', userType='manager'}";
        String actualMgrToString = manager.toString();
        assertEquals(expectedMgrToString, actualMgrToString);
    }

}
