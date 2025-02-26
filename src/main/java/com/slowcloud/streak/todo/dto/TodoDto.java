package com.slowcloud.streak.todo.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
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
