<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Center</title>
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
    <form action="updateCenter" method="post" class="container">
        <h2 style="text-align: center;"><b>Edit Center</b></h2>

        <input type="hidden" name="id" value="${center.id}">

        <label for="centerName">CENTER NAME:</label>
        <input type="text" name="centerName" value="${center.centerName}" placeholder="ENTER CLINIC NAME">

        <label for="city">SELECT CITY:</label>
        <select name="city" id="city">
            <option value="${center.city}">${center.city}</option>
            <option value="Patna">Patna</option>
            <option value="New Delhi">New Delhi</option>
            <option value="Kolkata">Kolkata</option>
            <option value="Samastipur">Samastipur</option>
            <option value="Darbhanga">Darbhanga</option>
            <option value="Kanpur">Kanpur</option>
            <option value="Lucknow">Lucknow</option>
            <option value="Varanasi">Varanasi</option>
        </select>

        <div class="error-message">
            <c:if test="${not empty message}">
                <span>${message}</span>
            </c:if>
        </div>

        <input type="submit" value="Update"/>
    </form>
</body>
</html>
