<%-- 
    Document   : History
    Created on : Oct 25, 2018, 5:12:20 PM
    Author     : piyao
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>History ::</h1>
        <table>
            <tr>
                <td>#</td>
                <td>AccountID</td>
                <td>Method</td>
                <td>Amount</td>
                <td>Time</td>
                <td>Balance</td>
            </tr>
            <c:forEach var="historyList" items="${historyList}" varStatus="vs"> 
                <tr>
                    <td>${vs.count}</td>
                    <td>${historyList.accountid.accountid}</td>
                    <td>${historyList.method}</td>
                    <td>${historyList.amount}</td>
                    <td>${historyList.createdate}</td>
                    <td>${historyList.balance}</td>
                </tr>
            </c:forEach>
        </table>




    </body>
</html>
