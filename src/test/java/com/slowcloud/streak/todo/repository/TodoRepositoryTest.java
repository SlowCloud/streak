package com.slowcloud.streak.todo.repository;

import com.slowcloud.streak.todo.entity.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    Todo getTodoFixture() {
        return Todo.builder().line("test line").score(1).completed(false).orderIndex(1).build();
    }

    @BeforeEach
    void setup() {
        todoRepository.deleteAll();
    }

    @Test
    void findMaxOrderIndex_emptyRepository_returnsZero() {
        int orderIndex = todoRepository.findMaxOrderIndex();

        assertEquals(orderIndex, 0);
    }

    @Test
    void findMaxOrderIndex_oneTodo_returnsOne() {
        Todo todo = getTodoFixture();
        todoRepository.save(todo);

        int orderIndex = todoRepository.findMaxOrderIndex();

        assertEquals(orderIndex, 1);
    }

    @Test
    void save_newTodo_addsOneTodoToRepository() {
        Todo todo = getTodoFixture();
        todoRepository.save(todo);

        List<Todo> todos = todoRepository.findAll();

        assertEquals(todos.size(), 1);
    }

}