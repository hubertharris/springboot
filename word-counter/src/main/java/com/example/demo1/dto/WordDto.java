package com.example.demo1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WordDto {
    public WordDto() {
    }

    /**
     * The word to add in the store.
     */
    private String word;
}
