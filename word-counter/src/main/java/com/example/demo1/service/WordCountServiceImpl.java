package com.example.demo1.service;

import com.example.demo1.Demo1Application;
import com.example.demo1.dto.ResultDto;
import com.example.demo1.model.WordBucket;
import com.example.demo1.validator.WordValidator;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WordCountServiceImpl implements WordCountService {

    /**
     * Gets the count of the given word in store.
     * @param input the given word.
     * @return count of the given word in store.
     */
    @Override
    public ResultDto getWordCount(String input) throws Exception {
        WordValidator.validateWord(input);
        int count = 0;
        String word = input.toLowerCase();
        Map<String, WordBucket> wordStore = Demo1Application.wordStore;
        if (wordStore.containsKey(word)) {
            count =  wordStore.get(word).getCount();
        }
        return  new ResultDto(count);
    }

    /**
     * Gets the total count of the words in store.
     * @return total count of the words in store.
     */
    @Override
    public ResultDto getTotalWordsCount() {
        return  new ResultDto(Demo1Application.wordStore.size());
    }

    /**
     * Adds the given word to store.
     * @param input the given word to add.
     * @throws Exception any exception occurs during the validation of input or processing.
     */
    @Override
    public void addWord(String input) throws Exception {
        Map<String, WordBucket> wordStore = Demo1Application.wordStore;
        WordValidator.validateWord(input);
        String word = input.toLowerCase();
        WordBucket wordBucket = new WordBucket(word);
        if (wordStore.containsKey(word)) {
            WordBucket existingWB =  wordStore.get(word);
            existingWB.incrementCount();
        } else {
            wordStore.put(word, wordBucket);
        }
    }
}
