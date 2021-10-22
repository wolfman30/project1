package com.ers.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface ReimburseRequestController
{
    Map createRequest(HttpServletRequest request);
    Map viewRequests(HttpServletRequest request);
    Map viewPendingRequests(HttpServletRequest request);
    Map resolveRequestPage(HttpServletRequest request);
    Map resolveRequest(HttpServletRequest request);
    Map getAllResolved(HttpServletRequest request);
    Map getRequestsPerEmployee(HttpServletRequest request);
}
