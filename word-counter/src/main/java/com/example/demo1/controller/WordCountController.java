package com.example.demo1.controller;

import com.example.demo1.dto.ResultDto;
import com.example.demo1.dto.WordDto;
import com.example.demo1.service.WordCountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/words")
@RequiredArgsConstructor
public class WordCountController implements WordCountApi {

    /**
     * The WordCountService.
     */
    private final WordCountService wordCountService;

    /**
     * Gets the count of the given words added to the words store.
     * @param word the given word.
     * @return count of the given word in store.
     */
    @Override
    @GetMapping("/count/{word}")
    public ResponseEntity<ResultDto> getWordCount(@PathVariable("word") String word) throws Exception {
        return new ResponseEntity<>(wordCountService.getWordCount(word), HttpStatus.OK);
    }

    /**
     * Gets the total word count in store.
     * @return count of the total words in store.
     */
    @Override
    @GetMapping("/count")
    public ResponseEntity<ResultDto> getTotalWordCount() {
        return new ResponseEntity<>(wordCountService.getTotalWordsCount(), HttpStatus.OK);
    }

    /**
     * Adds the given word to the store.
     * @param wordDto the given word.
     * @return ResponseEntity
     * @throws Exception any exceptions while processing the input or response.
     */
    @Override
    @PostMapping
    public ResponseEntity<Void> addWord(@RequestBody WordDto wordDto) throws Exception {
        wordCountService.addWord(wordDto.getWord());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
