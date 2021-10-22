package com.ers.controller;

import com.ers.model.Employee;
import com.ers.model.Manager;
import com.ers.repository.RepositoryPostgres;
import com.ers.service.UserServiceAlpha;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Routes data relevant to the users: the employees or managers
 */
public class UserControllerAlpha implements UserController
{
    private static UserController userController = new UserControllerAlpha();
    private UserControllerAlpha() {}
    public static UserController getInstance()
    {
        return userController;
    }

    public Object getAllUsers(HttpServletRequest request) {

        return null;
    }

    public Map getAllEmployees(HttpServletRequest request)
    {
        Map get_list_of_employees_for_manager_to_view = new HashMap<>();
        get_list_of_employees_for_manager_to_view.put("get list of employees for manager to view",
                                            UserServiceAlpha.getInstance().getAllEmployees(request));
        return get_list_of_employees_for_manager_to_view;
    }

    public Map getEmployee(HttpServletRequest request)
    {
        Map<String, Employee> get_personal_personal_info_for_employee = new HashMap<>();
        get_personal_personal_info_for_employee.put("get personal information for employee",
                                        UserServiceAlpha.getInstance().getEmployee(request));

        return  get_personal_personal_info_for_employee;
    }

    public Map updateUser(HttpServletRequest request)
    {
        Map employee_updated_their_information = new HashMap<>();
        employee_updated_their_information.put("employee updated their information",
                                RepositoryPostgres.getInstance().updateUser(request));
        return employee_updated_their_information;
    }
}
