<%@ page contentType="text/html;charset=UTF-8" language="java" import="Login.User" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
    <div class="root">
        <h3>You are logged succesfully! </h3><a href="/logout">Log Out!</a>
        <h6>Welcome <%= request.getAttribute( "FirstName" ) %></h6>
    </div>
</body>
</html>