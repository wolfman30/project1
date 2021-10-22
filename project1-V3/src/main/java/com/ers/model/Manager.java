package com.ers.model;

public class Manager extends User
{

    public Manager() {}

    public Manager(int id, String first_name, String last_name, String email, String pass_word, String user_type, String phone_number)
    {
        super(id, first_name, last_name, email, pass_word, user_type, phone_number);
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + super.getId() +
                ", firstName='" + super.getFirstName() + '\'' +
                ", lastName='" + super.getLastName() + '\'' +
                ", email='" + super.getEmail() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", userType='" + super.getUserType() + '\'' +
                '}';
    }
}
