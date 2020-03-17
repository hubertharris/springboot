package com.example.demo1.validator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordValidatorTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void validateWord_when_null() {
        Exception exception = assertThrows(Exception.class, () -> {
            WordValidator.validateWord(null);
        });
        assertEquals("Word is null or empty", exception.getMessage());
    }

    @Test
    void validateWord_when_empty() {
        Exception exception = assertThrows(Exception.class, () -> {
            WordValidator.validateWord("");
        });
        assertEquals("Word is null or empty", exception.getMessage());
    }

    @Test
    void validateWord_when_blanks_spaces() {
        Exception exception = assertThrows(Exception.class, () -> {
            WordValidator.validateWord("   ");
        });
        assertEquals("Word is null or empty", exception.getMessage());
    }

    @Test
    void validateWord_when_numeric() {
        Exception exception = assertThrows(Exception.class, () -> {
            WordValidator.validateWord("12345");
        });
        assertEquals("Word Invalid", exception.getMessage());
    }

    @Test
    void validateWord_when_alphanumeric() {
        Exception exception = assertThrows(Exception.class, () -> {
            WordValidator.validateWord("hello1");
        });
        assertEquals("Word Invalid", exception.getMessage());
    }

    @Test
    void validateWord_when_special_chars() {
        Exception exception = assertThrows(Exception.class, () -> {
            WordValidator.validateWord("@£$&*(");
        });
        assertEquals("Word Invalid", exception.getMessage());
    }

    @Test
    void validateWord_when_special_alphaSpecialChars() {
        Exception exception = assertThrows(Exception.class, () -> {
            WordValidator.validateWord("hello£");
        });
        assertEquals("Word Invalid", exception.getMessage());
    }

    @Test
    void validateWord_when_multiple_words() {
        Exception exception = assertThrows(Exception.class, () -> {
            WordValidator.validateWord("nice book");
        });
        assertEquals("Word Invalid", exception.getMessage());
    }

    @Test
    void validateWord_when_leading_trailing_spaces() {
        Exception exception = assertThrows(Exception.class, () -> {
            WordValidator.validateWord(" book ");
        });
        assertEquals("Word Invalid", exception.getMessage());
    }
}
