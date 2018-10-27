<%-- 
    Document   : Deposit
    Created on : Oct 25, 2018, 6:57:54 PM
    Author     : piyao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Withdraw</title>
    </head>
    <body>
        <h1>Withdraw</h1>
        ${sessionScope.username.balance}
        <br>
        <form action="Withdraw" method="post">
            Withdraw : <input type="number" name="money"><br>
            <input type="submit">
        </form>
    </body>
</html>
