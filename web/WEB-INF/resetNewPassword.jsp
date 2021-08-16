<%-- 
    Document   : resetNewPassword
    Created on : Jul 31, 2021, 2:32:19 AM
    Author     : emily
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Password</title>
    </head>
    <body>
        <form action="reset" method="post">
            <h1>Enter a new password</h1>
            <input type="text" name="password">
            <br>
            <input type="submit" value="Submit">
            <input type="hidden" name="action" value="newPassword">
        </form>
    </body>
</html>
