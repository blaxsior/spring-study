<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="com.example.demo.todo.repo.TodoNoteRepository" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%
    WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(application);
    TodoNoteRepository repository = ctx.getBean(TodoNoteRepository.class);

    Long deleteId = Long.parseLong(request.getParameter("deleteId"));
    repository.deleteById(deleteId);
%>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <title>todo 삭제</title>
</head>
<body>
<h1>todo 삭제 성공!</h1>
<p>id = <%=deleteId%></p>
<hr>
<a href="/views/jsp/todo.jsp">메인 페이지로 이동</a>
</body>
</html>