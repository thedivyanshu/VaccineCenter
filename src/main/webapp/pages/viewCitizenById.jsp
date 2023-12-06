<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>VIEW CITIZEN BY ID</title>
<style>
        table {
            margin: 0 auto; /* This centers the table horizontally */
        }
    </style>
</head>
<body>

<%@include file="dashboard.jsp" %>

    <h1 align="center">Citizen Information</h1>
    <table border="1" style="width: 30%;" >
        <tr>
            <td>ID</td>
            <td>${citizen.id}</td>
        </tr>
        <tr>
            <td>NAME</td>
            <td>${citizen.name}</td>
        </tr>
        <tr>
            <td>CITY</td>
            <td>${citizen.city}</td>
        </tr>
        <tr>
            <td>NO. OF DOSES</td>
            <td>${citizen.doses}</td>
        </tr>
        <tr>
            <td>VACCINATION STATUS</td>
            <td>${citizen.vaccinationStatus}</td>
        </tr>
        <tr>
            <td>VACCINATION CENTER</td>       
             <td >          
             <a href="<c:url value='/user/vaccination/viewCenterById' />?id=${citizen.center.id}&centerName=${ citizen.center.centerName}">${citizen.center.centerName}</a>
             </td>
        </tr>
    </table>


  <div class="error-message" align="center">
    <c:if test="${not empty message}">
        <span style="color: red;">${message}</span>
    </c:if>
</div>
</body>
</html>
