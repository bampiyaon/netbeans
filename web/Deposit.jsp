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
        <title>Deposit</title>
    </head>
    <body>
        <h1>Deposit ::</h1><br>
        ${sessionScope.username.balance}<br>
        <form action="Deposit" method="post">
            Deposit : <input type="number" name="money"><br>
            <input type="submit" value="deposit">
            ${wrongdeposit}
        </form>
    </body>
</html>
