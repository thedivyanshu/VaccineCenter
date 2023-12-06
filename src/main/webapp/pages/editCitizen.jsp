<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>EDIT CITIZEN</title>
    <style>
        .container {
            max-width: 400px;
            margin: 0 auto;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        select, input {
            width: 100%;
            padding: 5px;
            margin-bottom: 10px;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        .error-message {
            text-align: center;
            margin-top: 10px;
        }

        .error-message span {
            color: red;
        }
    </style>
</head>
<body>
    <h1 style="text-align: center;">EDIT CITIZEN</h1>
    <form action="updateCitizen" method="post" class="container">
        <input type="hidden" name="id" value="${citizen.id}">

        <label for="name">Citizen Name:</label>
        <input type="text" name="name" value="${citizen.name}" placeholder="ENTER NAME">

        <label for="city">CITIZEN CITY:</label>
        <select name="city" id="city">
            <option value="${citizen.city}">${citizen.city}</option>
            <option value="Bengalore">Bangalore</option>
            <option value="Chennai">Chennai</option>
            <option value="Hyderabad">Hyderabad</option>
            <option value="Pune">Pune</option>
            <option value="Madurai">Madurai</option>
            <option value="Punjab">Punjab</option>
            <option value="Ladakh">Ladakh</option>
            <option value="Coimbatore">Coimbatore</option>
        </select>

        <label for="doses">No. Of Doses</label>
        <select name="doses" id="doses">
            <option value="${citizen.doses}">${citizen.doses}</option>
            <option value="1">1</option>
            <option value="2">2</option>
        </select>

        <label for="centerName">CLINIC:</label>
        <select name="centerName" id="centerName">
            <option value="${citizen.center.centerName}">${citizen.center.centerName}</option>
            <c:forEach items="${centers}" var="center">
                <option value="${center.centerName}">${center.centerName}</option>
            </c:forEach>
        </select>

        <div class="error-message">
            <c:if test="${not empty message}">
                <span>${message}</span>
            </c:if>
        </div>

        <input type="submit" value="Submit"/>
    </form>
</body>
</html>
