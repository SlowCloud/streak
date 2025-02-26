package com.slowcloud.streak.todo.service;

import com.slowcloud.streak.todo.dto.SimpleTodoDto;
import com.slowcloud.streak.todo.dto.TodoDto;
import com.slowcloud.streak.todo.entity.Todo;
import com.slowcloud.streak.todo.mapper.TodoMapper;
import com.slowcloud.streak.todo.repository.TodoRepository;
import com.slowcloud.streak.util.langchain4j.TodoExtractor;

import com.slowcloud.streak.util.langchain4j.dto.ExtractedTodos;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;
    private final TodoExtractor todoExtractor;

    @Transactional
    public List<TodoDto> getAllTodos() {
        Sort sort = Sort.by("orderIndex");
        return todoRepository.findAll(sort).stream().map(todoMapper::toDto).toList();
    }

    @Transactional
    public TodoDto saveTodo(SimpleTodoDto todoDto) {
        int orderIndex = todoRepository.findMaxOrderIndex();

        Todo todo = Todo.builder().line(todoDto.line()).build();
        todo.setOrderIndex(orderIndex);

        Todo result = todoRepository.save(todo);
        return todoMapper.toDto(result);
    }

    public List<SimpleTodoDto> extractTodoFromText(String text) {
        ExtractedTodos extractedTodos = todoExtractor.extractTodoFromText(text);
        return extractedTodos.todos().stream().map(SimpleTodoDto::new).toList();
    }

}
