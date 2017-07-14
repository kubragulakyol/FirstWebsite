<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String errorMessage = (String) session.getAttribute( "error" );
    session.removeAttribute("error");
    boolean hasError = errorMessage != null && errorMessage.length() > 0;

    String infoMessage = (String) session.getAttribute( "info" );
    session.removeAttribute("info");
    boolean hasInfo = infoMessage != null && infoMessage.length() > 0;
%>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form method="post">

    <% if ( hasError ) { %>
    <h3 class="color: red;"><%= errorMessage %></h3>
    <% } %>

    <% if ( hasInfo ) { %>
    <h3 class="color: blue;"><%= infoMessage %></h3>
    <% } %>

    <table>
        <tr>
            <td>Username :</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>Password :</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Login" name="login"></td>
        </tr>
    </table>
</form>
</body>
</html>
