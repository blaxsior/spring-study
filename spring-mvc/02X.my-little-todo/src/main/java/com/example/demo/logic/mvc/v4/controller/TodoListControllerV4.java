package com.example.demo.logic.mvc.v4.controller;

import com.example.demo.ApplicationContextProvider;
import com.example.demo.logic.mvc.MyModel;
import com.example.demo.logic.mvc.MyModelView;
import com.example.demo.todo.repo.TodoNoteRepository;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.util.Map;

public class TodoListControllerV4 implements ControllerV4 {
    private TodoNoteRepository todoRepository;
    public TodoListControllerV4() {
        this.todoRepository = ApplicationContextProvider.getBean(TodoNoteRepository.class);
    }
    @Override
    public String process(Map<String, String> params, MyModel model) throws ServletException, IOException {
        var todoList = this.todoRepository.findAll();

        model.addAttribute("todos", todoList);

        return "todo";
    }
}
