package com.example.demo.logic.mvcdi.v3.controller;

import com.example.demo.ApplicationContextProvider;
import com.example.demo.logic.MyModelView;
import com.example.demo.todo.repo.TodoNoteRepository;
import jakarta.servlet.ServletException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component("/v3/todo/delete")
public class TodoDeleteControllerV3 implements ControllerV3 {
    private TodoNoteRepository todoRepository;

    @Autowired
    public TodoDeleteControllerV3(TodoNoteRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public MyModelView process(Map<String, String> params) throws ServletException, IOException {
        var deleteId = Long.parseLong(params.get("deleteId"));
        this.todoRepository.deleteById(deleteId);
        // 값 설정

        MyModelView modelView = new MyModelView("/todo/delete");
        modelView.addAttribute("deleteId", deleteId);

        return modelView;
    }
}