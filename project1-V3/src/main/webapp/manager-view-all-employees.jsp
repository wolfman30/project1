<%--
  Created by IntelliJ IDEA.
  User: wolfp
  Date: 10/7/2021
  Time: 08:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Manager View of All Pending Requests</title>
  <link rel='stylesheet' type='text/css' href='styles.css'>
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
        integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
        crossorigin="anonymous">
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<body>
  <header class="card">
    <h1>All Employees</h1>
  </header>
  <div class="table-view">
    <table>
      <tr>
        <th>Employee Name</th>
        <th>Email</th>
        <th>Phone Number</th>
      </tr>
      <c:forEach items="${manager_view_pending_requests}" var="request">

        <tr>
        <form method='post' action='/viewRequestsPerEmp.do' id="all-employee-form">
          <td>${request.getFirstName()} ${request.getLastName()}</td>
          <td>${request.getEmail()}</td>
          <td>${String.valueOf(request.getPhoneNumber()).substring(0,3)}-${String.valueOf(request.getPhoneNumber()).substring(3,6)}-${String.valueOf(request.getPhoneNumber()).substring(6,10)}
          </td>
          <td><input type='hidden' name='employee_id' value='${request.getId()}' id='hiddenId'>
            <button class="btn btn-primary" type="submit">View Requests</button>
          </td>
        </form>
      </tr>

      </c:forEach>
    </table>
    <a href='/managerHome.html' class="go-back-link"><button class="btn btn-primary btn-lg">Go Back</button></a>
  </div>
<br/>

</body>
</html>
