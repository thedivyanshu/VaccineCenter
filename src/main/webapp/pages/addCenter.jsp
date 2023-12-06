<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>ADD VACCINATION CENTER</title>
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
    <form action="insertCenter">
        <h2 style="text-align: center;"><b>ADD NEW VACCINATION CENTER</b></h2>

        <div class="container">
            <label for="centerName">CENTER NAME:</label>
            <input type="text" name="centerName" placeholder="ENTER CLINIC NAME">

            <label for="city">SELECT CITY:</label>
            <select name="city" class="box">
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

            <div class="error-message">
                <c:if test="${not empty message}">
                    <span>${message}</span>
                </c:if>
            </div>

            <input type="submit" value="Submit"/>
        </div>
    </form>
</body>
</html>
