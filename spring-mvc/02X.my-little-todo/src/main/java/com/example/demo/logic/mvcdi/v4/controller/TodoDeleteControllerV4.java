package com.example.demo.logic.mvcdi.v4.controller;

import com.example.demo.ApplicationContextProvider;
import com.example.demo.logic.MyModel;
import com.example.demo.todo.repo.TodoNoteRepository;
import jakarta.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
@Component("/v4/todo/delete")
public class TodoDeleteControllerV4 implements ControllerV4 {
    private TodoNoteRepository todoRepository;

    @Autowired
    public TodoDeleteControllerV4(TodoNoteRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public String process(Map<String, String> params, MyModel model) throws ServletException, IOException {
        var deleteId = Long.parseLong(params.get("deleteId"));
        this.todoRepository.deleteById(deleteId);
        // 값 설정

        model.addAttribute("deleteId", deleteId);

        return "todo/delete";
    }
}