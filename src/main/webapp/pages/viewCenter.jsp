<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>VIEW ALL CENTER</title>
<style>
        table {
            margin: 0 auto; /* This centers the table horizontally */
        }
    </style>

</head>
<body>
                    <%@include file="dashboard.jsp" %>

             <h2 align="center">VACCINATION CENTER</h2>

    <table border="1"style="width: 80%;">
        <thead>
        <tr>
        <td colspan="4">
        <form id="form-1" action="addCenter">
            <input type="submit" value="ADD NEW CENTER" id="button-1" />
        </form>
        </td>
    </tr>
        </thead>
        <tbody>
        <tr>
            <th>ID</th>
            <th>NAME</th>
            <th>City</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${centers}" var="center">
            <tr>
                <td>${center.id}</td>
                <td>${center.centerName}</td>
                <td>${center.city}</td>
                <td class="view-edit-delete">              
                <a href="<c:url value='viewCenterById' />?id=${center.id}&centerName=${ center.centerName}">View</a>
                <a href="<c:url value='editCenter?id=${center.id}'/>">Edit</a>
                <a href="<c:url value='deleteCenter?id=${center.id}'/>">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
       <tfoot>
        <tr>    
            <td colspan="4">Total ${centerCount} Vaccination Centers found.</td>
       </tr>
       </tfoot>
    </table>

    <div class="error-message" align="center">
    <c:if test="${not empty message}">
        <span style="color: red;">${message}</span>
    </c:if>
</div>
</body>
</html>
                