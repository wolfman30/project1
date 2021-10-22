package com.ers.controller;

import com.ers.model.*;
import com.ers.repository.RepositoryPostgres;
import com.ers.service.UserService;
import com.ers.service.UserServiceAlpha;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.dialect.PostgreSQL9Dialect;
import sun.security.timestamp.HttpTimestamper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for routing the data from reimbursement requests
 */
public class ReimburseRequestControllerAlpha implements ReimburseRequestController
{
    private static ReimburseRequestController reimburseRequestController = new ReimburseRequestControllerAlpha();
    private static Logger logger = LogManager.getLogger(ReimburseRequestControllerAlpha.class.getName());
    private ReimburseRequestControllerAlpha() {};

    public static ReimburseRequestController getInstance()
    {
        return reimburseRequestController;
    }

    /**
     *
     * @param request HTTPServletRequest the http request
     * @return  a map with the key as a readable description of what is happening
     */
    public Map createRequest(HttpServletRequest request)
    {
        logger.debug("Creating a new reimbursement request...");
        int num = UserServiceAlpha.getInstance().createRequest(request);
        Map employee_submitted_reimbursement_request = new HashMap<>();
        employee_submitted_reimbursement_request.put("employee submitted reimbursement request", num);

        return employee_submitted_reimbursement_request;
    }

    /**
     * Allows an employee to view their own requests
     * @param request
     * @return  a list of reimbursement requests
     */
    public Map viewRequests(HttpServletRequest request)
    {
        logger.debug("Request reached viewRequests in ReimburseRequestControllerAlpha");
        return UserServiceAlpha.getInstance().viewRequests(request);

    }

    /**
     * Allows a manager to view all pending requests
     * @param request
     * @return
     */
    public Map viewPendingRequests(HttpServletRequest request) {

        Map get_all_pending_requests_for_manager_to_view = new HashMap<>();
        get_all_pending_requests_for_manager_to_view.put("get all pending requests for manager to view", UserServiceAlpha.getInstance().getPendingRequests(request) );

        return get_all_pending_requests_for_manager_to_view;
    }



    /**
     * Sends the manager to the web page to resolve the request
     * @return Integer the request id to update the right row in the reimbursement request table
     */
    public Map resolveRequestPage(HttpServletRequest request) {
        logger.debug("here is the request id: " + request.getParameter("request_id"));
        Map send_to_resolution_page = new HashMap<>();
        send_to_resolution_page.put("send manager to the resolution page", Integer.parseInt(request.getParameter("request_id")));
        return send_to_resolution_page;
    }

    /**
     * Retrieves the resolved request from dao
     * @return the string of "resolved" for the DispatcherServlet to send the manager to the manager home page
     */
    public Map<String, String> resolveRequest(HttpServletRequest request)
    {
        /* get data from request */
        String resolution = request.getParameter("resolve");
        String comment = request.getParameter("comment");
        int manager_id = ((Manager)request.getSession().getAttribute("loggedUser")).getId();
        int request_id = (Integer)request.getSession().getAttribute("request_id");

        logger.debug("here is the reimbursement request id from submission: " + request_id);
        RepositoryPostgres.getInstance().resolveRequest(resolution, comment, request_id, manager_id);
        Map<String, String> resolved = new HashMap<>();
        resolved.put("manager resolved reimbursement request", "resolved");
        return resolved;
    }



    /**
     * Routes all resolved reimbursement transactions using UserServiceAlpha
     */
    public Map getAllResolved(HttpServletRequest request)
    {
        Map<String, List<Manager_Reimbursement_Request>> map = new HashMap<>();
        map.put("all-resolved-requests-for-manager", RepositoryPostgres.getInstance().getAllResolvedRequests());

        return map;
    }

    /**
     * This passes the request to the DAO to get the reimbursement requests
     * based on the employee id
     */
    public Map getRequestsPerEmployee(HttpServletRequest request)
    {
        int employee_id = Integer.parseInt(request.getParameter("employee_id"));
        Map get_all_requests_from_specific_employee_for_manager = new HashMap<>();

        get_all_requests_from_specific_employee_for_manager.put
                        ("get all requests from specific employee for manager",
                        RepositoryPostgres.getInstance().getAllRequestsPerEmployee(employee_id));

        return get_all_requests_from_specific_employee_for_manager;
    }
}
