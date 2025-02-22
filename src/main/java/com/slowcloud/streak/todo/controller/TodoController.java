package com.slowcloud.streak.todo.controller;

import com.slowcloud.streak.todo.dto.TodoDto;
import com.slowcloud.streak.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        List<TodoDto> todos = todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    @PostMapping
    public ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto todo) {
        TodoDto dto = todoService.saveTodo(todo);
        return ResponseEntity.ok(dto);
    }

}
