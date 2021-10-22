package com.ers.service;

import com.ers.model.*;
import com.ers.repository.RepositoryPostgres;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceAlpha implements UserService
{
    private Employee_Reimbursement_Request reimbursement_request;

    private static final Logger logger = LogManager.getLogger(UserServiceAlpha.class.getName());

    private static final UserService userService = new UserServiceAlpha();

    private UserServiceAlpha() {
        reimbursement_request = new Employee_Reimbursement_Request();
    }

    public static UserService getInstance() {
        return userService;
    }

    /**
     * Here I authenticate the user based upon the email and password retrieved from the request object
     * via request.getParameter("email") and request.getParameter("password")
     * Notice that I use a while: this ensures the dao get the user. If I did not have the while loop,
     * dao could end up not getting the user, and authenticate returning null which leads to a 500 code
     * error on the web page (internal server error) and a nullpointerexception. I use a limit of the count
     * to ensure that it does get stuck in an infinite loop when redploying the tomcat server
     * @param email
     * @param password
     * @return
     */
    @Override
    public User authenticate(String email, String password)
    {

        logger.debug("Email is: " + email);
        User user = RepositoryPostgres.getInstance().getUser(email);
        logger.debug("password is: " + password);

        int count = 0;
        while (user == null)
        {
            user = RepositoryPostgres.getInstance().getUser(email);
            count++;
            if (count == 6)
                break;
        }

        if (user != null && user.getPassword().equals(password))

            return user;

        logger.debug("authenticate() returned null");
        logger.debug(user);

        return null;
    }

    /**
     * Helps the employee create a request
     * @param request
     * @return
     */
    @Override
    public int createRequest(HttpServletRequest request)
    {

        Employee employee = (Employee) request.getSession().getAttribute("loggedUser");
        reimbursement_request.setEmployeeId(employee.getId());
        reimbursement_request.setAmount(Double.parseDouble(request.getParameter("amount")));
        reimbursement_request.setReason(request.getParameter("reason"));
        reimbursement_request.setStatus("pending");

        int recordCount = RepositoryPostgres.getInstance().insertRequest(reimbursement_request);

        logger.debug("inserted one record");
        System.out.println("insert one reimbursement request");
        return recordCount;
    }


    public Map viewRequests(HttpServletRequest request)
    {
        User user = (User) request.getSession().getAttribute("loggedUser");
        logger.debug("Request reached viewRequests in UserServiceAlpha");
        logger.debug("This should not be null: " + request.getSession().getAttribute("loggedUser"));
        Map get_all_requests_for_employee = new HashMap<>();
        get_all_requests_for_employee.put("get all requests for employee",
                            RepositoryPostgres.getInstance().getView(user));
        return get_all_requests_for_employee;

    }

    /**
     * Gets a list of all employees for the manager
     * @param request HTTPServletRequest    the request sent from submitting from an html form
     * @return  list of all Employees from the database
     */
    public List<Employee> getAllEmployees(HttpServletRequest request) {
        return RepositoryPostgres.getInstance().getAllEmployees();
    }

    public Employee getEmployee(HttpServletRequest request)
    {
        Employee employee = (Employee)request.getSession().getAttribute("loggedUser");
        request.getSession().setAttribute("id", employee.getId());
        employee.setId(-1);
        return employee;

    }

    /**
     * Passes along the all pending requests from the DAO
     * @param request  http request
     * @return   list of all pending requests for manager
     */
    public List<Manager_Reimbursement_Request> getPendingRequests(HttpServletRequest request)
    {
        List<Manager_Reimbursement_Request> all_pending_requests = RepositoryPostgres.getInstance().get_all_pending_requests();
        logger.debug("Here is the list of all pending requests " + all_pending_requests.toString());
        return all_pending_requests;
    }

    /**
     * Filters out all the resolved reimbursement requests from all the reimbursement requests
     * @param request   the HTTP request from the browser
     * @return  a list of all resolve reimbursement requests
     */
    public List getAllResolved(HttpServletRequest request)
    {
        return new ArrayList();
    }

}
