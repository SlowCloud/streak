package com.slowcloud.streak.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public final class TodoDto {
    private final long id;
    private final String line;
    private final LocalDateTime createdAt;
    private final LocalDateTime completedAt;
    private final boolean completed;
    private final int orderIndex;
    private final int score;
}
