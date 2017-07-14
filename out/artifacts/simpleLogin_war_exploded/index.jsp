<%@ page import="Login.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) session.getAttribute( "currentUser" );
    boolean userIsExist = user != null;
%>
<html>
<head>
    <title>Deneme</title>
</head>
<body>
<div class="root">
    <h3>KÜBRA STAJ YAPIYOR</h3>
    <% if(userIsExist) {%>
        Hoşgeldin <%= user.getFirstName() %>
        <a href="/logout">Log Out!</a>
    <%} else {%>
        <a href="/login">Login</a>
    <%}%>
</div>


</body>
</html>