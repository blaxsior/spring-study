<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <title>todo 생성</title>
</head>
<body>
<h1>todo form</h1>
<hr>
<form method="post" action="/views/jsp/todo/create.jsp">
    <label for="content">content</label>
    <input type="text" id="content" name="content"/>
    <button type="submit">submit</button>
</form>
<hr>
<a href="/views/jsp/todo.jsp">메인 페이지로 이동</a>
</body>
</html>