package com.ers.model;

public class Employee extends User
{
    public Employee() {}

    public Employee(int id, String firstName, String lastName, String email, String password, String userType, String phoneNumber)
    {
        super(id, firstName, lastName, email, password, userType, phoneNumber);
    }

    @Override
    public String toString()
    {
        return "Employee{" +
                "id=" + super.getId() +
                ", firstName='" + super.getFirstName() + '\'' +
                ", lastName='" + super.getLastName() + '\'' +
                ", email='" + super.getEmail() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", userType='" + super.getUserType() + '\'' +
                '}';
    }
}
