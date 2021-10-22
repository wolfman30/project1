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
    <h1>All Pending Requests</h1>
  </header>
  <div class = "table-view">

  <table>
    <tr>
      <th>Time Created</th><th>Employee Name</th>
      <th>Amount</th><th>Reason</th><th>Resolve</th>
    </tr>
    <c:forEach items="${manager_view_pending_requests}" var="request">
      <tr>
        <form method='post' action='/resolveRequest.do'>
          <td>${String.valueOf(request.getTimeCreated()).substring(0, 16)}</td>
          <td>${request.getEmployeeFirstName()} ${request.getEmployeeLastName()}</td>
          <td>${request.getAmount()}</td>
          <td>${request.getReason()}</td>
          <input type='number' name='request_id' class='request_id' value='${request.getId()}'>
          <td><button type="submit" class="btn btn-primary">Resolve</button></td>
        </form>
      </tr>
    </c:forEach>
  </table>
    <a href='/managerHome.html'><button class="btn btn-primary btn-lg">Go Back</button></a>
  </div>
  </body>
</html>
