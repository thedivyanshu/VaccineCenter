<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Registration</title>
    <style>
        body {
            text-align: center;
            margin: 20px;
        }

        form {
            max-width: 300px;
            margin: 0 auto;
        }

        h2 {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input {
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
            margin-top: 10px;
            color: red;
        }
    </style>
</head>
<body>
 <header>
        <h1>VACCINATION CENTER</h1>
        <hr>
    </header>
    <form action="insert" method="post">
        <h2>REGISTRATION</h2>

        <label for="name">NAME:</label>
        <input name="name" placeholder="Your Name" type="text">

        <label for="email">EMAIL:</label>
        <input type="email" name="email" placeholder="Email">

        <label for="password">PASSWORD:</label>
        <input type="password" name="password" placeholder="Enter Password">

        <div class="error-message">
            <c:if test="${not empty message}">
                <span>${message}</span>
            </c:if>
        </div>

        <input type="submit" value="Register">

    </form>
</body>
</html>
