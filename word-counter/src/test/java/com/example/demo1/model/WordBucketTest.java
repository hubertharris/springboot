package com.example.demo1.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordBucketTest {

    WordBucket testObj;

    @BeforeEach
    void setUp() {
        testObj = new WordBucket("hello");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testEquals() {
        WordBucket wordBucket1 = new WordBucket("hello");
        assertEquals(testObj, wordBucket1);

        WordBucket wordBucket2 = new WordBucket("hello1");
        assertNotEquals(testObj, wordBucket2);

        WordBucket wordBucket3 = new WordBucket("some");
        assertNotEquals(testObj, wordBucket3);
    }

    @Test
    void testHashCode() {

        WordBucket wordBucket1 = new WordBucket("hello");
        assertEquals(wordBucket1.hashCode(), testObj.hashCode());

        WordBucket wordBucket2 = new WordBucket("hello");
        assertEquals(wordBucket2.hashCode(), testObj.hashCode());

        WordBucket wordBucket3 = new WordBucket("some");
        assertNotEquals(wordBucket3.hashCode(), testObj.hashCode());
    }

    @Test
    void getWord() {
        assertEquals("hello", testObj.getWord());
    }

    @Test
    void getCount() {
        assertEquals(1, testObj.getCount());
    }

    @Test
    void incrementCount() {
        assertEquals(1, testObj.getCount());
        testObj.incrementCount();;
        assertEquals(2, testObj.getCount());
        testObj.incrementCount();;
        assertEquals(3, testObj.getCount());
        testObj.incrementCount();;
        assertEquals(4, testObj.getCount());
    }
}
