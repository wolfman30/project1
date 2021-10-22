package com.ers.repository;
import com.ers.model.User;
import javax.servlet.http.HttpServletRequest;


public interface Repo
{
    User getUser(String email);
    Double updateUser(HttpServletRequest request);
    User getUserById(int id);
}
