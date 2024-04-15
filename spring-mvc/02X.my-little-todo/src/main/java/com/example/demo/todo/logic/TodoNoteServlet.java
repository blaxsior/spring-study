package com.example.demo.todo.logic;

import com.example.demo.todo.repo.TodoNoteRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/todo-list")
public class TodoNoteServlet extends HttpServlet {
    private TodoNoteRepository todoNoteRepository;

    @Autowired
    public void setTodoNoteRepository(TodoNoteRepository todoNoteRepository) {
        this.todoNoteRepository = todoNoteRepository;
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        var todoList = this.todoNoteRepository.findAll();

        PrintWriter writer = res.getWriter();
        writer.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Todo List</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>My Simple Todo List</h1>\n" +
                "<hr>\n" +
                "<form method=\"post\" action=\"/todo/create\">\n" +
                "  <label for=\"content\">content</label>\n" +
                "  <input type=\"text\" id=\"content\" name=\"content\" />\n" +
                "  <button type=\"submit\">submit</button>\n" +
                "</form>\n" +
                "<hr>\n" +
                "<ul>\n");

        for (var todo : todoList) {
            writer.println("    <span>" + todo.getContent() + "</span> | <span>" + todo.getCreatedAt() + "</span> |\n" +
                    "    <form method=\"post\" action=\"/todo/delete\">\n" +
                    "      <input type=\"hidden\" id=\"deleteId\" name=\"deleteId\" value='" + todo.getId() + "'/>\n" +
                    "      <button type=\"submit\">delete</button>\n" +
                    "  </form>");
        }

        writer.println("</ul>\n" +
                "</body>\n" +
                "</html>");
    }
}
