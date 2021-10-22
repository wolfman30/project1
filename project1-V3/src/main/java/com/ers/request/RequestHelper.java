package com.ers.request;

import com.ers.controller.LoginControllerAlpha;
import com.ers.controller.ReimburseRequestControllerAlpha;

import javax.servlet.http.HttpServletRequest;

import com.ers.controller.UserControllerAlpha;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Map;

/**
 * This is the helper class that helps the DispatcherServlet to process requests
 */
public class RequestHelper {

    private static Logger logger = LogManager.getLogger(RequestHelper.class.getName());

    /**
     * Processes the http request from the front controller servlet
     * @param request http request
     * @return  map with a human-readable key to see what is happening in the frontcontroller
     * @throws IOException
     */
    public Map process(HttpServletRequest request) throws IOException {

        logger.debug("Here is the request uri: " + request.getRequestURI());
        logger.debug("Here is the request url: " + request.getRequestURL());
        logger.debug("here is the deny radio value: " + request.getParameter("resolve"));

        switch (request.getRequestURI())
        {
            case "/logout.do":
                return LoginControllerAlpha.getInstance().logout(request);
            case "/login.do":
                return LoginControllerAlpha.getInstance().login(request);
            case "/createRequest.do":
                return ReimburseRequestControllerAlpha.getInstance().createRequest(request);
            case "/viewRequests.do":
                return ReimburseRequestControllerAlpha.getInstance().viewRequests(request);
            case "/viewInformation.do":
                return UserControllerAlpha.getInstance().getEmployee(request);
            case "/selectAllEmployees.do":
                return UserControllerAlpha.getInstance().getAllEmployees(request);
            case "/managerViewAllPendingRequests.do":
                return ReimburseRequestControllerAlpha.getInstance().viewPendingRequests(request);
            case "/updateInfo.do":
                return UserControllerAlpha.getInstance().updateUser(request);
            case "/resolveRequest.do":
                return ReimburseRequestControllerAlpha.getInstance().resolveRequestPage(request);
            case "/approveDenyRequest.do":
                return ReimburseRequestControllerAlpha.getInstance().resolveRequest(request);
            case "/viewAllResolved.do":
                return ReimburseRequestControllerAlpha.getInstance().getAllResolved(request);
            case "/viewRequestsPerEmp.do":
                return ReimburseRequestControllerAlpha.getInstance().getRequestsPerEmployee(request);
            default:
                return null;
        }
    }
}
