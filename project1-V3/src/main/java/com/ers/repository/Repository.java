package com.ers.repository;

import com.ers.model.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

public interface Repository extends Repo
{
    List<Reimbursement_Request> getView(User user);
    List<Manager_Reimbursement_Request> get_all_pending_requests();
    void resolveRequest(String resolution, String comment, int requestId, int request_id);
    List<Manager_Reimbursement_Request> getAllResolvedRequests();
    List<Manager_Reimbursement_Request> getAllRequestsPerEmployee(int employee_id);
    int insertRequest(Reimbursement_Request reimbursementRequest);
    List<Employee> getAllEmployees();
}
