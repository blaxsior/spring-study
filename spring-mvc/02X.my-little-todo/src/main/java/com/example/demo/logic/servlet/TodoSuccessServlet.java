package com.example.demo.logic.servlet;

import com.example.demo.todo.entity.TodoNote;
import com.example.demo.todo.repo.TodoNoteRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/servlet/todo/create")
public class TodoSuccessServlet extends HttpServlet {
    private TodoNoteRepository todoNoteRepository;

    @Autowired
    public void setTodoNoteRepository(TodoNoteRepository todoNoteRepository) {
        this.todoNoteRepository = todoNoteRepository;
    }

    @Override
    @Transactional
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        var content = req.getParameter("content");
        var todoNote = new TodoNote(content);
        var todo = this.todoNoteRepository.save(todoNote);

        res.setCharacterEncoding("utf-8");
        res.setContentType("text/html");

        PrintWriter w = res.getWriter();
        w.write("<!DOCTYPE html>\n" +
                "<html lang=\"kr\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>todo 생성</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>todo 생성 성공!</h1>\n" +
                "<ul>\n" +
                "    <li>id: "+todo.getId()+"</li>\n" +
                "    <li>content: "+todo.getContent()+"</li>\n" +
                "    <li>date: "+todo.getCreatedAt()+"</li>\n" +
                "</ul>\n" +
                "<hr>\n" +
                "<a href=\"/todo\">메인 페이지로 이동</a>\n" +
                "</body>\n" +
                "</html>");
    }
}
