package com.example.demo.logic.mvcdi.v3.controller;

import com.example.demo.ApplicationContextProvider;
import com.example.demo.logic.MyModelView;
import com.example.demo.todo.repo.TodoNoteRepository;
import jakarta.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
@Component("/v3/todo")
public class TodoListControllerV3 implements ControllerV3 {
    private TodoNoteRepository todoRepository;

    @Autowired
    public TodoListControllerV3(TodoNoteRepository todoRepository) {
        this.todoRepository = todoRepository;
    }
    @Override
    public MyModelView process(Map<String, String> params) throws ServletException, IOException {
        var todoList = this.todoRepository.findAll();

        MyModelView modelView = new MyModelView("/todo");
        modelView.addAttribute("todos", todoList);

        return modelView;
    }
}
