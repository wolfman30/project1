package com.ers.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface UserController
{
    Object getAllUsers(HttpServletRequest request);

    Map getAllEmployees(HttpServletRequest request);

    Map getEmployee(HttpServletRequest request);

    Map updateUser(HttpServletRequest request);
}
