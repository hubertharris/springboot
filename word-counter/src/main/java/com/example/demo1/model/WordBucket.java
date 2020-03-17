package com.example.demo1.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class WordBucket {

    /**
     * The word to be stored.
     */
    private String word;

    /**
     * The count of the given word in store.
     */
    private int count;

    /**
     * The constructor which instantiates with the given word.
     * @param word the word to add in store.
     */
    public WordBucket(String word) {
        this.word = word;
        this.count++;
    }

    /**
     * Increment the value of count every time the method is accessed.
     */
    public void incrementCount() {
        count++;
    }

    /**
     * Equals method to compare the equality of WordBucket object.
     * @param o the given object to be compared with this object.
     * @return boolean.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordBucket wordBucket = (WordBucket) o;
        return getWord().equals(wordBucket.getWord());
    }

    /**
     * Gets the hashcode.
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(getWord());
    }
}
