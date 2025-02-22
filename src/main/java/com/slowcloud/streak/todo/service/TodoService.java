package com.slowcloud.streak.todo.service;

import com.slowcloud.streak.todo.dto.TodoDto;
import com.slowcloud.streak.todo.entity.Todo;
import com.slowcloud.streak.todo.mapper.TodoMapper;
import com.slowcloud.streak.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    public List<TodoDto> getAllTodos() {
        Sort sort = Sort.by("orderIndex");
        return todoRepository.findAll(sort).stream().map(todoMapper::toDto).toList();
    }

    public TodoDto saveTodo(TodoDto todoDto) {
        int orderIndex = todoRepository.findMaxOrderIndex();

        Todo todo = todoMapper.toEntity(todoDto);
        todo.setOrderIndex(orderIndex);

        Todo result = todoRepository.save(todo);
        return todoMapper.toDto(result);
    }

}
