<%-- 
    Document   : MyAccount
    Created on : Oct 25, 2018, 6:58:55 PM
    Author     : piyao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Account</title>
    </head>
    <body>
        <h2>My Account</h2>
        <br>
        ${sessionScope.username.name}
        <br>
        ${sessionScope.username.balance}
        <br>
        <a href="Withdraw">Withdraw</a>
        <br>
        <a href="Deposit">Deposit</a>
        <br>
        <a href="History">History</a>
        <br>
        <a href="Logout">Logout</a>
    </body>
</html>
