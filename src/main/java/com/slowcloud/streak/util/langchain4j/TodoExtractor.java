package com.slowcloud.streak.util.langchain4j;

import com.slowcloud.streak.util.langchain4j.dto.ExtractedTodos;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

import java.util.List;

@AiService
public interface TodoExtractor {
    @SystemMessage("extract todo from text")
    ExtractedTodos extractTodoFromText(String text);
}
