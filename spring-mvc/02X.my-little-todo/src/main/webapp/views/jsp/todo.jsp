<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="com.example.demo.todo.repo.TodoNoteRepository" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.todo.entity.TodoNote" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%
    WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(application);
    TodoNoteRepository repository = ctx.getBean(TodoNoteRepository.class);

    List<TodoNote> todos = repository.findAll();
%>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <title>Todo List</title>
</head>
<body>
<h1>My Simple Todo List</h1>
<hr>
<a href="/views/jsp/todo/new-form.jsp">Todo 생성</a>
<hr>
<ul>
    <% for (var todo : todos) { %>
    <li>
        <span><%= todo.getId() %></span> | <span><%= todo.getContent() %></span> | <span><%= todo.getCreatedAt() %></span> |
        <form method="post" action="/views/jsp/todo/delete.jsp">
            <input type="hidden" id="deleteId" name="deleteId" value="<%= todo.getId() %>"/>
            <button type="submit">delete</button>
        </form>
    </li>
    <% } %>
</ul>
</body>
</html>