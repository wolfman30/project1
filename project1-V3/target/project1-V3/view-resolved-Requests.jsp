<%--
  Created by IntelliJ IDEA.
  User: Andrew Wolf
  Date: 10/7/2021
  Time: 06:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <link rel='stylesheet' type='text/css' href='styles.css'>
    <title>Resolved Requests</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
          crossorigin="anonymous">
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  </head>
  <body>
    <header class="card">
      <h1>All Resolved Requests</h1>
    </header>
<div class="table-view">


    <table>
      <tr>
        <th>Time Created</th><th>Employee</th><th>Dollar Amount</th><th>Reason for request</th>
        <th>Status of request</th><th>Approved</th><th>Manager</th><th>Manager Comment</th>
      </tr>
  <c:forEach items="${resolvedRequests}" var="request">
    <tr>
      <td>${String.valueOf(request.getTimeCreated()).substring(0, 16)}</td>
      <td>${request.getEmployeeFirstName()} ${request.getEmployeeLastName()}</td>
      <td>${request.getAmount()}</td>
      <td>${request.getReason()}</td>
      <td>${request.getStatus()}</td>
      <td>${request.isApproved()}</td>
      <td>${request.getManagerFirstName()} ${request.getManagerLastName()}</td>
      <td>${request.getManagerComment()}</td>
    </tr>
  </c:forEach>
    </table>
  <a href='/managerHome.html'><button class="btn btn-primary btn-lg">Go Back</button></a>
</div>
  </body>
</html>
