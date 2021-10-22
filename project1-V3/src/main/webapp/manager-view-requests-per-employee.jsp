<%--
  Created by IntelliJ IDEA.
  User: wolfp
  Date: 10/7/2021
  Time: 07:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <link rel='stylesheet' type='text/css' href='styles.css'>
    <title>All Requests Per Employee for Manager</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
          crossorigin="anonymous">
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  </head>
  <body>
    <header class="card">
      <h1>All Requests</h1>
    </header>
    <div class="table-view">


    <c:if test = "${manager_view_requests_per_employee.size() > 0}">
    <h2>${manager_view_requests_per_employee.get(0).getEmployeeFirstName()} ${manager_view_requests_per_employee.get(0).getEmployeeLastName()}</h2>
    <table>
      <c:if test = "${manager_view_requests_per_employee.get(0).getStatus() == 'pending'}">
      <tr>
        <th>Time Created</th><th>$ Dollar Amount $</th><th>Reason for Request</th>
        <th>Status of Request</th><th>Approved</th><th>Resolve</th><th>Manager</th><th>Manager Comment</th>
      </tr>
      </c:if>
      <c:if test = "${manager_view_requests_per_employee.get(0).getStatus() == 'resolved'}">
        <tr>
          <th>Time Created</th><th>$ Dollar Amount $</th><th>Reason for Request</th>
          <th>Status of Request</th><th>Approved</th><th>Manager</th><th>Manager Comment</th>
        </tr>
      </c:if>
      <c:forEach items="${manager_view_requests_per_employee}" var="request">
        <c:if test = "${request.getStatus() == 'pending'}">
        <tr>
          <form method='post' action='/resolveRequest.do'>
            <td>${String.valueOf(request.getTimeCreated()).substring(0, 16)}</td>
            <td>${request.getAmount()}</td>
            <td>${request.getReason()}</td>
            <td>${request.getStatus()}</td>
            <td>${request.isApproved()}</td>
            <input type='number' name='request_id' class='request_id' value='${request.getId()}'>
            <td><button type="submit" class="btn btn-primary">Resolve</button></td>
          </form>
        </tr>
        </c:if>
        <c:if test = "${request.getStatus() == 'resolved'}">
          <tr>
            <td>${String.valueOf(request.getTimeCreated()).substring(0, 16)}</td>
            <td>${request.getAmount()}</td>
            <td>${request.getReason()}</td>
            <td>${request.getStatus()}</td>
            <td>${request.isApproved()}</td>
            <td>${request.getManagerFirstName()} ${request.getManagerLastName()}</td>
            <td>${request.getManagerComment()}</td>
          </tr>
        </c:if>
      </c:forEach>
    </table>
    </c:if>
    <br/>
    <c:if test = "${manager_view_requests_per_employee.size() == 0}">
      <h2>This employee has made no reimbursement requests.</h2>
    </c:if>
    <br/>
      <a href='/managerHome.html'><button class="btn btn-primary btn-lg">Go Back</button></a>
    </div>
  </body>
</html>
