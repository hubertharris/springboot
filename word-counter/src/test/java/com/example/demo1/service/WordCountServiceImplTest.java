package com.example.demo1.service;

import com.example.demo1.Demo1Application;
import com.example.demo1.dto.ResultDto;
import com.example.demo1.model.WordBucket;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WordCountServiceImplTest {

    WordCountServiceImpl testObj;

    @BeforeEach
    void setUp() {
        testObj = new WordCountServiceImpl();
    }

    /**
     * Clearing the contents of global word store is important before every tests as word store will have the contents from
     * previous tests otherwise. This is also a very good example where you need an @AfterEach annotated method.
     */
    @AfterEach
    void tearDown() {
        Demo1Application.wordStore.clear();
    }

    /**
     * Tests the getWordCount method for happy path.
     * @throws Exception any exceptions happen during execution.
     */
    @Test
    void getWordCount() throws Exception {

        testObj.addWord("hello");
        testObj.addWord("HELLO");
        testObj.addWord("hello");
        testObj.addWord("hello");
        testObj.addWord("hello");

        ResultDto resultDto = testObj.getWordCount("hello");
        assertNotNull(resultDto);
        assertEquals(5, resultDto.getCount());

        resultDto = testObj.getWordCount("HELLO");
        assertNotNull(resultDto);
        assertEquals(5, resultDto.getCount());
    }

    @Test
    void getWordCount_when_invalid_input() {
        assertThrows(Exception.class, () -> testObj.getWordCount("HELLO1"));
        assertThrows(Exception.class, () -> testObj.getWordCount("  "));
        assertThrows(Exception.class, () -> testObj.getWordCount(""));
        assertThrows(Exception.class, () -> testObj.getWordCount(null));
        assertThrows(Exception.class, () -> testObj.getWordCount("1234"));
        assertThrows(Exception.class, () -> testObj.getWordCount("@£$%"));
        assertThrows(Exception.class, () -> testObj.getWordCount("test test"));
        assertThrows(Exception.class, () -> testObj.getWordCount(" test "));
    }

    @Test
    void addWord() throws Exception {
        testObj.addWord("hubert");
        Map<String, WordBucket> wordStore =  Demo1Application.wordStore;
        assertEquals(1, wordStore.size());

        testObj.addWord("harris");
        assertEquals(2, wordStore.size());

        testObj.addWord("hubert");
        assertEquals(2, wordStore.size());
        WordBucket wordBucket = wordStore.get("hubert");
        assertNotNull(wordBucket);
        assertEquals(2, wordBucket.getCount());

        testObj.addWord("hello");
        assertEquals(3, wordStore.size());

        testObj.addWord("HELLO");
        assertEquals(3, wordStore.size());

        testObj.addWord("Hello");
        assertEquals(3, wordStore.size());
        wordBucket = wordStore.get("hello");
        assertNotNull(wordBucket);
        assertEquals(3, wordBucket.getCount());
    }

    @Test
    void addWord_when_invalid_input() {
        assertThrows(Exception.class, () -> testObj.addWord("hubert1"));

        // Test when the input contains space
        assertThrows(Exception.class, () -> testObj.addWord("hubert harris"));
        assertThrows(Exception.class, () -> testObj.addWord("hubert "));
        assertThrows(Exception.class, () -> testObj.addWord(" hubert "));
        assertThrows(Exception.class, () -> testObj.addWord(" hubert"));

        // Test when the input has special characters
        assertThrows(Exception.class, () -> testObj.addWord("hubert@"));

        // Test when the input is numeric only
        assertThrows(Exception.class, () -> testObj.addWord("123"));

        // Test the exception messages
        Exception exception = assertThrows(Exception.class, () -> testObj.addWord("123"));
        assertEquals("Word Invalid", exception.getMessage());

    }

    @Test
    void addWord_when_empty_input() {
        Exception exception = assertThrows(Exception.class, () -> testObj.addWord(""));
        assertEquals("Word is null or empty", exception.getMessage());

        exception = assertThrows(Exception.class, () -> testObj.addWord("  "));
        assertEquals("Word is null or empty", exception.getMessage());
    }

    @Test
    void addWord_when_null_input() {
        Exception exception = assertThrows(Exception.class, () -> testObj.addWord(null));
        assertEquals("Word is null or empty", exception.getMessage());
    }

    @Test
    void getTotalWordsCount() throws Exception {
        assertEquals(0, testObj.getTotalWordsCount().getCount());

        testObj.addWord("hello");
        testObj.addWord("Hello");
        testObj.addWord("HELLO");
        testObj.addWord("hello");
        assertEquals(1, testObj.getTotalWordsCount().getCount());


        testObj.addWord("test");
        testObj.addWord("hubert");
        assertEquals(3, testObj.getTotalWordsCount().getCount());
    }
}
