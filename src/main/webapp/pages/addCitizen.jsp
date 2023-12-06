<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Add citizen</title>
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
    <form action="insertCitizen" method="POST" class="container">
        <h2 style="text-align: center;"><b>ADD NEW CITIZEN</b></h2>

        <label for="name">Citizen Name:</label>
        <input type="text" name="name" placeholder="ENTER NAME">

        <label for="city">CITIZEN CITY:</label>
        <select name="city" id="city">
            <option value="">--Please choose a city--</option>
            <option value="Patna">Patna</option>
            <option value="New Delhi">New Delhi</option>
            <option value="Kolkata">Kolkata</option>
            <option value="Samastipur">Samastipur</option>
            <option value="Darbhanga">Darbhanga</option>
            <option value="Kanpur">Kanpur</option>
            <option value="Lucknow">Lucknow</option>
            <option value="Varanasi">Varanasi</option>
        </select>
		<label for="doses">No. Of Doses</label>
        <select name="doses" id="doses">
            <option value="0">0</option>
            <option value="1">1</option>
            <option value="2">2</option>
        </select>
        <label for="centerName">CITIZEN CLINIC:</label>
        <select name="centerName" id="centerName">
            <option value="">--Please choose a clinic--</option>
            <c:forEach items="${centers}" var="center">
                <option value="${center.centerName}">${center.centerName}</option>
            </c:forEach>
        </select>

        <div class="error-message">
            <c:if test="${not empty message}">
                <span>${message}</span>
            </c:if>
        </div>

        <input type="submit" value="Submit" />
    </form>
</body>
</html>
