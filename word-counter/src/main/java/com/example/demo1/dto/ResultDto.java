package com.example.demo1.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ResultDto {

    /**
     * The count of the words in store.
     */
    private final int count;

    @JsonCreator
    public ResultDto(int count) {
        this.count = count;
    }
}
