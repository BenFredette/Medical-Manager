<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String message = "";
    String messageType = "";
    if(request.getParameter("message") != null && request.getParameter("messageType") != null) {
        message = request.getParameter("message");
        messageType = request.getParameter("messageType");
    }
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <style>
        body {
            background-color: #E6E6FA;
        }
        .center {
            max-width: 800px;
            margin: 0 auto;
            text-align: center;
        }
        img {
            max-width: 100%;
            height: auto;
        }
        .message {
            color: <%= messageType.equals("error") ? "red" : "green" %>;
            font-weight: bold;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="center">
        <h2>Login to see your account</h2>
        <img src="resources/lib2.png">
        <% if(!message.equals("")) { %>
            <div class="message">
                <%= message %>
            </div>
        <% } %>
        <form action="Login" method="post">
            <table border="2" align="center" cellpadding="5" cellspacing="5">
                <tr>
                    <td>Select account type:</td>
                    <td>
                        <select name="account_type">
                            <option value="Patient">Patient</option>
                            <option value="Doctor">Doctor</option>
                            <option value="Admin">Admin</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Enter username:</td>
                    <td><input type="text" name="username" size="30"></td>
                </tr>
                <tr>
                    <td>Enter password:</td>
                    <td><input type="password" name="password" size="30"></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" value="Login">
                    </td>
                </tr>
            </table>
        </form>
        <img src="resources/lib1.png" >
    </div>
</body>
</html>
