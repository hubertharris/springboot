package com.example.demo1.service;

import com.example.demo1.dto.ResultDto;

public interface WordCountService {
    ResultDto getWordCount(String word) throws Exception;
    ResultDto getTotalWordsCount();
    void addWord(String word) throws Exception;
}
