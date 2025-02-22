package com.slowcloud.streak.todo.mapper;

import com.slowcloud.streak.todo.dto.TodoDto;
import com.slowcloud.streak.todo.entity.Todo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    TodoDto toDto(final Todo todo);
    Todo toEntity(final TodoDto todoDto);
}
