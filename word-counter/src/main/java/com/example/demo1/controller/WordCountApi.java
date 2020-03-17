package com.example.demo1.controller;

import com.example.demo1.dto.ResultDto;
import com.example.demo1.dto.WordDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface WordCountApi {
    ResponseEntity<ResultDto> getWordCount(String word) throws Exception;

    ResponseEntity<Void> addWord(@RequestBody WordDto wordDto) throws Exception;

    ResponseEntity<ResultDto> getTotalWordCount();
}
