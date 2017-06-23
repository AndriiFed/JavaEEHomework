<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ${USER}
  Date: ${DATE}
  Time: ${TIME}
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title>Person Service</title>
    <link rel='stylesheet' href='main.css' type='text/css' />
</head>
<body>
<table border="2">
    <tr>
        <td>id</td>
        <td>name</td>
        <td>age</td>
        <td>Actions</td>
    </tr>
    <c:forEach items="${persons}" var="personVar">
        <tr>
            <td>
                <c:out value="${personVar.id}"/>
            </td>
            <td>
                <c:out value="${personVar.name}"/>
            </td>
            <td>
                <c:out value="${personVar.age}"/>
            </td>
            <td>
                <a href="persons?action=delete&id=<c:out value='${personVar.id}'/>">Delete</a>
                <a href="persons?action=update&id=<c:out value='${personVar.id}'/>">Update</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="persons?action=addnew">Add new person</a>
<div name="messageBox" class="error"><c:out value="${message}" /></div>
</body>
</html>
