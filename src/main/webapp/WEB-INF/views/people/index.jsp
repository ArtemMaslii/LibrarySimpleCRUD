<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>People</title>
</head>
<body>
<table>
    <c:forEach items="${personList}" var="person">
        <tr>
            <td><a href="/people/${person.personId}">${person.fullName}, ${person.yearOfBirth}</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>