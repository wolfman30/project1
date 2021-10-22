package com.ers.request;

import com.ers.model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ers.repository.RepositoryPostgres;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class acts as the front controller: the servlet that takes in all the requests and passes them along to the helper class
 * Then takes the response and sends those responses to their respective JSP or HTML pages
 */
public class DispatcherServlet extends HttpServlet {

    private static Logger logger = LogManager.getLogger(DispatcherServlet.class.getName());

    private boolean is_list_of_all_employees(List<User> userList)
    {
        for (User user: userList)
        {
            return (user instanceof Employee);
        }
        return false;
    }

    /**
     * I only use the doPost method in this project
     * @param request   the http request
     * @param response  the http response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        logger.debug("Dispatcher Servlet processing request...");

        /* Processes request */
        RequestHelper requestHelper = new RequestHelper();
        Map data = requestHelper.process(request);

        logger.debug("data is: " + data);

        String employee_uri = "/employeeHome.html";
        String manager_uri = "/managerHome.html";

        switch((String)data.keySet().toArray()[0])
        {
            case "employee logged in successfully":
                response.setStatus(302);
                request.getRequestDispatcher(employee_uri).forward(request, response);
                break;

            case "manager logged in successfully":
                response.setStatus(303);
                request.getRequestDispatcher(manager_uri).forward(request, response);
                break;

            case "incorrect email or password":
            response.setStatus(320);
            request.getRequestDispatcher("/").forward(request, response);
                break;

            case "all-resolved-requests-for-manager":
                List<Manager_Reimbursement_Request> resolvedRequests = (ArrayList)data.get("all-resolved-requests-for-manager");
                request.getSession().setAttribute("resolvedRequests", resolvedRequests);
                request.getRequestDispatcher("view-resolved-Requests.jsp").forward(request, response);
                break;

            case "send manager to the resolution page":
                request.getSession().setAttribute("request_id", data.get("send manager to the resolution page"));
                request.getRequestDispatcher("/resolveRequest.html").forward(request, response); break;

            case "manager resolved reimbursement request":
                response.sendRedirect(manager_uri); break;

            case "get personal information for employee":
                Employee employee = (Employee)data.get("get personal information for employee");
                employee.setId( (Integer)request.getSession().getAttribute("id") );
                Employee emp = (Employee) RepositoryPostgres.getInstance().getUserById((Integer) request.getSession().getAttribute("id"));
                request.getSession().setAttribute("emp", emp);
                request.getRequestDispatcher("/employee-personal-information.jsp").forward(request, response);
                break;

            case "get all requests for employee":
                List<Reimbursement_Request> singleEmpRequests = ( List<Reimbursement_Request>)data.get("get all requests for employee");
                request.getSession().setAttribute("singleEmpRequests", singleEmpRequests);
                request.getRequestDispatcher("individual-employee-requests.jsp").forward(request, response);
                break;

            case "get all requests from specific employee for manager":
                List<Manager_Reimbursement_Request> manager_view_requests_per_employee = ( List<Manager_Reimbursement_Request>)data.get("get all requests from specific employee for manager");
                request.getSession().setAttribute("manager_view_requests_per_employee", manager_view_requests_per_employee);
                request.getRequestDispatcher("manager-view-requests-per-employee.jsp").forward(request, response);
                break;

            case "get all pending requests for manager to view":
                List<Manager_Reimbursement_Request> manager_view_pending_requests = ( List<Manager_Reimbursement_Request>)data.get("get all pending requests for manager to view");
                request.getSession().setAttribute("manager_view_pending_requests", manager_view_pending_requests);
                request.getRequestDispatcher("manager-view-pending-requests.jsp").forward(request, response);
                break;

            case "get list of employees for manager to view":
                List<Employee> manager_view_all_employees = ( List<Employee>)data.get("get list of employees for manager to view");
                request.getSession().setAttribute("manager_view_pending_requests", manager_view_all_employees);
                request.getRequestDispatcher("manager-view-all-employees.jsp").forward(request, response);
                break;

            case "employee submitted reimbursement request":
                response.sendRedirect(employee_uri); break;

            case "employee updated their information":
                response.sendRedirect(employee_uri); break;

            case "logged out":
                response.sendRedirect("/"); break;
        }
    }
}
