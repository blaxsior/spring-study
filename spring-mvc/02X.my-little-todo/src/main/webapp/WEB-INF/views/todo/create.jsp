<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <title>todo 생성</title>
</head>
<body>
<h1>todo 생성 성공!</h1>
<ul>
    <li>id: ${todo.id}</li>
    <li>content: ${todo.content}</li>
    <li>date: ${todo.createdAt}</li>
</ul>
<hr>
<a href="../todo">메인 페이지로 이동</a>
</body>
</html>