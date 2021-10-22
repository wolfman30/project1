package com.ers.service;

import com.ers.model.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface UserService {

    User authenticate(String email, String password);
    int createRequest(HttpServletRequest request);
    Map viewRequests(HttpServletRequest request);


    List<Employee> getAllEmployees(HttpServletRequest request);

    Employee getEmployee(HttpServletRequest request);

    List<Manager_Reimbursement_Request> getPendingRequests(HttpServletRequest request);

    List<Request> getAllResolved(HttpServletRequest request);
}
