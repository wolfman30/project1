package com.ers.repository;

import com.ers.connection.DatabaseConnector;
import com.ers.model.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Using a Singleton design pattern to only initialize an object from the dao
 * only once
 */
public class RepositoryPostgres implements Repository {

    private static Logger logger = LogManager.getLogger(RepositoryPostgres.class.getName());

    private static Repository postgresRepo;

    private RepositoryPostgres()
    {
    }

    ;

    /**
     * Good ole singleton pattern that will allow me to access Repository objects within
     * other classes such as the UserServiceAlpha and imagine if we had millions of requests:
     * we will not have millions of instantiations of RepositoryPostgres objects: just one.
     *
     * @return returns a Repository object, which is part of the Data Access Object
     */
    public static Repository getInstance()
    {
            if (postgresRepo == null)
                postgresRepo = new RepositoryPostgres();
            return postgresRepo;
    }

    /**
     * Gets user based on id
     * @param id    manager or employee's id
     * @return      returns a User: either a manger or employee
     */
    public User getUser(int id)
    {

        try (Connection c = DatabaseConnector.getConnection()) {
            String sqlStatement = "SELECT id, first_name, last_name, pass_word, email, user_type from ers_user where id = ?";

            PreparedStatement statement = c.prepareStatement(sqlStatement);
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            while (result.next())
            {
                // Need to refactor this into one private helper method
                if (result.getString("user_type").equals("employee")) {
                    return new Employee(
                            result.getInt("id"),
                            result.getString("first_name"),
                            result.getString("last_name"),
                            result.getString("email"),
                            result.getString("pass_word"),
                            result.getString("user_type"),
                            result.getString("phone_number"));
                } else {
                    return new Manager(
                            result.getInt("id"),
                            result.getString("first_name"),
                            result.getString("last_name"),
                            result.getString("email"),
                            result.getString("pass_word"),
                            result.getString("user_type"),
                            result.getString("phone_number"));
                }
            }
        } catch (SQLException throwables) {
            logger.debug("Could not get User object based on email");
            throwables.printStackTrace();
        }
        return null;
    }
    /**
     * Gets the User based on email
     *
     * @param email the email of the user
     * @return returns a User object, either Manager or Employee
     */
    public User getUser(String email) {

        try (Connection c = DatabaseConnector.getConnection()) {
            String sqlStatement = "SELECT id, first_name, last_name, phone_number, pass_word, email, user_type from ers_user where email = ?";

            PreparedStatement statement = c.prepareStatement(sqlStatement);
            statement.setString(1, email);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                // Need to refactor this into one private helper method
                if (result.getString("user_type").equals("employee")) {
                    return new Employee(
                            result.getInt("id"),
                            result.getString("first_name"),
                            result.getString("last_name"),
                            result.getString("email"),
                            result.getString("pass_word"),
                            result.getString("user_type"),
                            result.getString("phone_number"));
                } else {
                    return new Manager(
                            result.getInt("id"),
                            result.getString("first_name"),
                            result.getString("last_name"),
                            result.getString("email"),
                            result.getString("pass_word"),
                            result.getString("user_type"),
                            result.getString("phone_number"));
                }
            }
        } catch (SQLException throwables) {
            logger.debug("Could not get User object based on email");
            throwables.printStackTrace();
        }
        return null;
    }


    /**
     * This inserts the employee's submitted reimbursement request into the
     * reimbursement_history table and returns the new reimbursement request id
     *
     * @param request the reimbursement request object created from the employee
     * @return int     the id of the new reimbursement request
     */
    @Override
    public int insertRequest(Reimbursement_Request request)
    {
        try (Connection c = DatabaseConnector.getConnection()) {
            String sql = "insert into reimbursement_request_history (amount, employee_id, reason, status) values (?, ?, ?, ?)";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setDouble(1, request.getAmount());
            ps.setInt(2, request.getEmployeeId());
            ps.setString(3, request.getReason());
            ps.setString(4, request.getStatus());

            ps.execute();
            logger.debug("Inserted new request");
        } catch (SQLException throwables) {
            logger.debug("Could not insert the ReimbursementRequests object");
            System.out.println("Did not insert new reimbursement request");
            throwables.printStackTrace();
        }
        return 1;
    }

    /**
     * Get reimbursement requests based on the user
     * @return list of reimbursement requests for an single employee
     */
    @Override
    public List<Reimbursement_Request> getView(User user)
    {
        try (Connection c = DatabaseConnector.getConnection()) {
            logger.debug("getting the list of reimbursement requests");
            logger.debug("user is this type when getting reimbursement quests: " + user.getUserType());
            System.out.println("getting list of reimbursement requests");
            System.out.println("user is this type when getting reimbursement quests: " + user.getUserType());

            String sql = "select time_created, amount, reason, status, approved from reimbursement_request_history where employee_id = ? order by time_created desc";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, user.getId());

            ResultSet results = ps.executeQuery();

            List<Reimbursement_Request> reimbursementList = new ArrayList<>();

            while (results.next()) {
                Reimbursement_Request reimbursementRequest = new Employee_Reimbursement_Request();
                reimbursementRequest.setTimeCreated(results.getTimestamp("time_created"));
                reimbursementRequest.setAmount(results.getDouble("amount"));
                reimbursementRequest.setReason(results.getString("reason"));
                reimbursementRequest.setStatus(results.getString("status"));
                reimbursementRequest.setApproved(results.getBoolean("approved"));
                System.out.println(reimbursementRequest.toString());
                reimbursementList.add(reimbursementRequest);
            }

            results.close();
            ps.close();

            return reimbursementList;

        } catch (SQLException throwables) {
            throw new RuntimeException("Could not retrieve the reimbursement requests", throwables);
        }
    }

    public List<Manager_Reimbursement_Request> get_all_pending_requests()
    {
        try (Connection c = DatabaseConnector.getConnection())
        {
            String sql = "select * from pending_requests order by time_created desc";
            Statement statement = c.createStatement();
            ResultSet results = statement.executeQuery(sql);
            List<Manager_Reimbursement_Request> requests = new ArrayList<>();
            while (results.next())
            {
                Manager_Reimbursement_Request request = new Manager_Reimbursement_Request();
                request.setId(results.getInt("request_id"));
                request.setTimeCreated(results.getTimestamp("time_created"));
                request.setEmployeeFirstName(results.getString("first_name"));
                request.setEmployeeLastName(results.getString("last_name"));
                request.setAmount(results.getDouble("amount"));
                request.setReason(results.getString("reason"));

                requests.add(request);

            }
            results.close();
            statement.close();
            return requests;
        }
        catch (SQLException throwables)
        {
            throw new RuntimeException("Could not retrieve all reimbursement requests", throwables);
        }
    }

    /**
     * Gets all employees and manager but is used to get employees for manager
     * @return  list of users: managers and employees
     */
    @Override
    public List<Employee> getAllEmployees()
    {

        try (Connection conn = DatabaseConnector.getConnection())
        {

            List<Employee> employeeList = new ArrayList<>();
            Employee employee;
            logger.debug("getting the list of all employees");


            String sql = "select id, first_name, last_name, phone_number, email from ers_user where user_type = 'employee'";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                employee = new Employee(resultSet.getInt("id"),
                                        resultSet.getString("first_name"),
                                        resultSet.getString("last_name"),
                                        resultSet.getString("email"),
                                        null,
                                        null,
                                        resultSet.getString("phone_number"));

                employeeList.add(employee);
            }

            logger.debug("list of employees: " + employeeList.toString());
            resultSet.close();
            preparedStatement.close();

            return employeeList;
        }
        catch (SQLException throwables)
        {
            throw new RuntimeException("Could not retrieve the reimbursement requests", throwables);
        }
    }

    /**
     * Gets user by id
     */
    public Double updateUser(HttpServletRequest request)
    {
        try (Connection c = DatabaseConnector.getConnection())
        {
            String sql = "update ers_user set first_name= ?, last_name = ?, phone_number = ?, email = ?, pass_word = ? where id = ?";
            User user = (User)request.getSession().getAttribute("loggedUser");
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, request.getParameter("first_name"));
            ps.setString(2, request.getParameter("last_name"));
            ps.setString(3, request.getParameter("phone_number"));
            ps.setString(4, request.getParameter("email"));
            ps.setString(5, request.getParameter("password"));
            ps.setInt(6, user.getId());

            ps.execute();
            logger.debug("Updated employee information");
            return new Double(1.0);
        }
        catch (SQLException throwables)
        {
            logger.debug("Could not update employee information");
            throwables.printStackTrace();
        }

        return null;
    }

    public User getUserById(int id)
    {
        try (Connection c = DatabaseConnector.getConnection())
        {
            String sqlStatement = "SELECT * from ers_user where id = ?";

            PreparedStatement statement = c.prepareStatement(sqlStatement);
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            while (result.next())
            {

                return new Employee(
                        result.getInt("id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("email"),
                        result.getString("pass_word"),
                        result.getString("user_type"),
                        result.getString("phone_number"));

            }
        }
        catch (SQLException throwables)
        {
            logger.debug("Could not get User object based on email");
            throwables.printStackTrace();
        }
        return null;
    }

    public void resolveRequest(String resolution, String comment, int request_id, int manager_id)
    {
        try (Connection c = DatabaseConnector.getConnection())
        {
            String sql = "update reimbursement_request_history set status = ?, approved = ?, manager_comment = ?, manager_id = ? where request_id = ?";

            PreparedStatement ps = c.prepareStatement(sql);
            if (resolution.equals("Approve"))
            {
                ps.setBoolean(2, true);
            }
            else
            {
                ps.setBoolean(2, false);
            }
            ps.setString(1, "resolved");
            ps.setString(3, comment);
            ps.setInt(4, manager_id);
            ps.setInt(5, request_id);

            ps.execute();
            logger.debug("Manager resolved reimbursement request");

        }
        catch (SQLException throwables)
        {
            logger.debug("Could not update employee information");
            throwables.printStackTrace();
        }


    }

    public List<Manager_Reimbursement_Request> getAllResolvedRequests()
    {
        try (Connection conn = DatabaseConnector.getConnection())
        {

            List<Manager_Reimbursement_Request> requestList = new ArrayList<>();
            Manager_Reimbursement_Request request = new Manager_Reimbursement_Request();
            logger.debug("getting the list of all reimbursement requests");


            String sql = "select * from resolution order by time_created desc";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {

                request = new Manager_Reimbursement_Request(resultSet.getInt("request_id"),
                                                            resultSet.getString("emp_first_name"),
                                                            resultSet.getString("emp_last_name"),
                                                            resultSet.getString("first_name"),
                                                            resultSet.getString("last_name"),
                                                            resultSet.getString("manager_comment"),
                                                            resultSet.getDouble("amount"),
                                                            resultSet.getString("reason"),
                                                            resultSet.getString("status"),
                                                            resultSet.getTimestamp("time_created"),
                                                            resultSet.getBoolean("approved"));

                requestList.add(request);
            }

            logger.debug("list of employees: " + requestList.toString());
            resultSet.close();
            preparedStatement.close();

            return requestList;
        }
        catch (SQLException throwables)
        {
            throw new RuntimeException("Could not retrieve the reimbursement requests", throwables);
        }
    }

    /**
     * Gets all reimbursment requests based on the passed in employee id
     * @return  list of reimbursement requests for the manager to review per employee
     */
    public List<Manager_Reimbursement_Request> getAllRequestsPerEmployee(int id)
    {
        try (Connection c = DatabaseConnector.getConnection()) {

            String sql = "select request_id, time_created, emp_first_name, emp_last_name, mgr_first_name, mgr_last_name, amount, reason, status, approved, manager_comment from all_view_requests2 where employee_id = ? order by time_created desc";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet results = ps.executeQuery();

            List<Manager_Reimbursement_Request> reimbursementList = new ArrayList<>();

            while (results.next()) {
                Manager_Reimbursement_Request reimbursementRequest = new Manager_Reimbursement_Request();
                reimbursementRequest.setId(results.getInt("request_id"));
                reimbursementRequest.setTimeCreated(results.getTimestamp("time_created"));
                reimbursementRequest.setEmployeeFirstName(results.getString("emp_first_name"));
                reimbursementRequest.setEmployeeLastName(results.getString("emp_last_name"));
                reimbursementRequest.setAmount(results.getDouble("amount"));
                reimbursementRequest.setReason(results.getString("reason"));
                reimbursementRequest.setStatus(results.getString("status"));
                reimbursementRequest.setApproved(results.getBoolean("approved"));
                reimbursementRequest.setManagerComment(results.getString("manager_comment"));
                reimbursementRequest.setManagerLastName(results.getString("mgr_last_name"));
                reimbursementRequest.setManagerLastName(results.getString("mgr_last_name"));
                reimbursementList.add(reimbursementRequest);
            }

            results.close();
            ps.close();

            return reimbursementList;

        } catch (SQLException throwables) {
            throw new RuntimeException("Could not retrieve the reimbursement requests", throwables);
        }
    }
}
