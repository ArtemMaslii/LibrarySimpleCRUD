<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: manda
  Date: 7/5/2023
  Time: 6:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Books</title>
</head>
<body>
    <table>
        <c:forEach items="${bookList}" var="book">
            <tr>
                <td><a href="/books/${book.bookId}">${book.name}, ${book.yearOfPublish}</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
