<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Login Page</title>
    <style>
        body {
            text-align: center;
            margin: 20px;
        }

        header {
            margin-bottom: 20px;
        }

        form {
            max-width: 300px;
            margin: 0 auto;
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

        button {
            background-color: #008CBA;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #004666;
        }
    </style>
</head>
<body>
    <header>
        <h1>VACCINATION CENTER</h1>
        <hr>
    </header>
    
    <form action="dashboard" method="post">
        <h2><b>Login</b></h2>

        <label for="email">Email:</label>
        <input type="email" name="email" placeholder="Email">

        <label for="password">Enter Password:</label>
        <input type="password" name="password" placeholder="Enter Password">

        <div class="error-message">
            <c:if test="${not empty message}">
                <span>${message}</span>
            </c:if>
        </div>

        <input type="submit" value="LOGIN" />
    </form>

    <div>
        <form action="register">
            <button>REGISTER</button>
        </form>
    </div>
</body>
</html>
