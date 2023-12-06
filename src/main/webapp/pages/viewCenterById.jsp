<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>VIEW CITIZEN BY ID</title>
   
</head>
<body>
<%@include file="dashboard.jsp" %>

    <h1 class="head1" align="center">CENTER INFORMATION</h1>
    <table border="1" align="center">
        <!-- Display center details -->
        <tr>
            <td>ID</td>
            <td>${center.id}</td>
        </tr>
        <tr>
            <td>Name</td>
            <td>${center.centerName}</td>
        </tr>
        <tr>
            <td>City</td>
            <td>${center.city}</td>
        </tr>
            </table>
            <h2 align="center">ALL CITIZEN</h2>
            <table border="1" align="center">
        <!-- Iterate over citizens list -->
        <!-- Display citizen details  -->
        <c:forEach items="${citizens}" var="citizen" >
            <c:if test="${center.centerName == citizen.center.centerName}">
                <tr>
                    <td>ID</td>
                    <td>${citizen.id}</td>
                </tr>
                <tr>
                    <td>Name</td>
                    <td>${citizen.name}</td>
                </tr>
                <tr>
                <td>Action</td>
                 <td><a href="<c:url value='/user/citizen/viewCitizenById?id=${citizen.id}'/>">View</a></td>
                </tr>               
            </c:if>
            <br>
        </c:forEach>
</table>
 
    <!-- Error message display -->
<div class="error-message" align="center">
    <c:if test="${not empty message}">
        <span style="color: red;">${message}</span>
    </c:if>
</div>
</body>
</html>
