package com.slowcloud.streak.util.langchain4j;

import com.slowcloud.streak.util.langchain4j.dto.ExtractedTodos;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TodoExtractorTest {

    @Autowired
    TodoExtractor todoExtractor;
    
    @Test
    void extractFromTodoTest() {
        String text = "장보기, 게임하기";
        ExtractedTodos result = todoExtractor.extractTodoFromText(text);
        assertNotNull(result);
        System.out.println(result);
    }
    
}
