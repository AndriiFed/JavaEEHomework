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
    <title><c:out value="${title}" /></title>
    <link rel='stylesheet' href='main.css' type='text/css' />
</head>
<body  onload='document.userForm.name.focus()'>
<form action="<%=request.getContextPath()%>/persons" method="post" onsubmit="return validateForm()" name="userForm">
    <table>
        <tr>
            <td><label>id</label></td>
            <td><input type="text" name="id" value="<c:out value='${person.id}'/>" readonly /></td>
        </tr>
        <tr>
            <td><label>name</label></td>
            <td><input type="text" name="name" value="<c:out value='${person.name}'/>" oninput="validateForm()" /></td>
        </tr>
        <tr>
            <td><label>age</label></td>
            <td><input type="text" name="age" value="<c:out value='${person.age}' />" oninput="validateForm()" /></td>
        </tr>
    </table>
    <button type="submit">Add user</button>
</form>

<script src="main.js"> </script>
</body>
</html>
