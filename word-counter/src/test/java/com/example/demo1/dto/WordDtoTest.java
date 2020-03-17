package com.example.demo1.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordDtoTest {

    private WordDto testObj;

    @BeforeEach
    void setUp() {
        testObj = new WordDto();
        testObj.setWord("test");
    }

    /**
     * Tests the getWord.
     */
    @Test
    void getWord() {
        String result = testObj.getWord();
        assertNotNull(result);
        assertEquals("test", result);
    }
}
