<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="com.example.demo.todo.repo.TodoNoteRepository" %>
<%@ page import="com.example.demo.todo.entity.TodoNote" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%
    WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(application);
    TodoNoteRepository repository = ctx.getBean(TodoNoteRepository.class);

    String content = request.getParameter("content");

    TodoNote todo = new TodoNote(content);
    repository.save(todo);
%>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <title>todo 생성</title>
</head>
<body>
<h1>todo 생성 성공!</h1>
<ul>
    <li>id: <%=todo.getId()%></li>
    <li>content: <%=todo.getContent()%></li>
    <li>date: <%=todo.getCreatedAt()%></li>
</ul>
<hr>
<a href="/views/jsp/todo.jsp">메인 페이지로 이동</a>
</body>
</html>