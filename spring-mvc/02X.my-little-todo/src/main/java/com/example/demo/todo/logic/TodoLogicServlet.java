package com.example.demo.todo.logic;

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

@WebServlet(urlPatterns = "/todo/*")
public class TodoLogicServlet extends HttpServlet {
    private TodoNoteRepository todoNoteRepository;

    @Autowired
    public void setTodoNoteRepository(TodoNoteRepository todoNoteRepository) {
        this.todoNoteRepository = todoNoteRepository;
    }

    @Override
    @Transactional
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        var contextPath = req.getPathInfo().split("/");
        for(var path: contextPath) {
            System.out.println("path = " + path);
        }
        if(contextPath.length < 1) return;

        var action = contextPath[1];
        System.out.println("path " + action);


        if(action.equals("create")) {
            var content = req.getParameter("content");
            var todoNote = new TodoNote(content);

            System.out.println(content);

            this.todoNoteRepository.save(todoNote);
        } else if (action.equals("delete")) {
            var dataId = req.getParameter("deleteId");
            var id = Long.parseLong(dataId);

            this.todoNoteRepository.deleteById(id);
        }

        res.sendRedirect("/todo-list");
    }
}
