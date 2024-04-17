<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <title>Todo List</title>
</head>
<body>
<h1>My Simple Todo List</h1>
<hr>
<a href="todo/new-form">Todo 생성</a>
<hr>
<ul>
    <c:forEach var="todo" items="${todos}">
        <li>
            <span>${todo.id}</span> | <span>${todo.content}</span> | <span>${todo.createdAt}</span> |
            <form method="post" action="todo/delete">
                <input type="hidden" id="deleteId" name="deleteId" value="${todo.id}"/>
                <button type="submit">delete</button>
            </form>
        </li>
    </c:forEach>
</ul>
</body>
</html>