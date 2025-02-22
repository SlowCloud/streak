package com.slowcloud.streak.todo.repository;

import com.slowcloud.streak.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Query("SELECT COALESCE(MAX(t.orderIndex), 0) FROM Todo t")
    int findMaxOrderIndex();
}
