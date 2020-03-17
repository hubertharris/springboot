package com.example.demo1.it;

import com.example.demo1.Demo1Application;
import com.example.demo1.controller.WordCountController;
import com.example.demo1.dto.ResultDto;
import com.example.demo1.dto.WordDto;
import com.example.demo1.service.WordCountService;
import com.example.demo1.service.WordCountServiceImpl;
import com.example.demo1.util.AppConstant;
import com.example.demo1.util.AppUrlUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Finally Got it. Specify the classes , not the interfaces to create the bean and all its dependencies. This will create the real bean and load the application context.
 * Use the bean loaded in application context to autowire in the test class if required.
 * This setup can be used successfully to write Integration tests end to end effectively. As we are not loading all the beans in the application , but selectively
 * loading the beans required for that test this will be faster and effective.
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = {WordCountController.class, WordCountServiceImpl.class})
@EnableAutoConfiguration
class HttpRequestIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String wordsUrl;

    private String wordsCountUrl;

    @Autowired
    private WordCountService wordCountService;

    @Autowired
    private WordCountController customerController;

    @BeforeEach
    void setUp() {
        wordsUrl = AppUrlUtil.getHttpLocalUrl(port, AppConstant.RESOURCE_PATH_WORDS);
        wordsCountUrl = AppUrlUtil.getHttpLocalUrl(port, AppConstant.RESOURCE_PATH_WORDS_COUNT);
    }

    /**
     * Clearing the contents of global word store is important before every tests as word store will have the contents from
     * previous tests otherwise. This is also a very good example where you need an @AfterEach annotated method.
     */
    @AfterEach
    void tearDown() {
        Demo1Application.wordStore.clear();
    }

    @Test
    void postWord() {

        WordDto wordDto = new WordDto("hubert");

        ResponseEntity<Void> responseEntity = this.restTemplate.postForEntity(wordsUrl, wordDto, Void.class);
        assertNotNull(responseEntity);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        assertEquals(201, httpStatus.value());
    }

    @Test
    void postWord_invalid_input() {

        WordDto wordDto = new WordDto("hubert1");

        ResponseEntity<Void> responseEntity = this.restTemplate.postForEntity(wordsUrl, wordDto, Void.class);
        assertNotNull(responseEntity);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        assertEquals(500, httpStatus.value());
    }

    @Test
    void getWordCount() {

        WordDto wordDto = new WordDto("hubert");
        this.restTemplate.postForEntity(wordsUrl, wordDto, Void.class);

        ResponseEntity<ResultDto> responseEntity = this.restTemplate.getForEntity(wordsCountUrl + "/hubert", ResultDto.class);
        assertNotNull(responseEntity);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        assertEquals(200, httpStatus.value());

        ResultDto resultDto = responseEntity.getBody();
        assertNotNull(resultDto);
        assertEquals(1, resultDto.getCount());

        this.restTemplate.postForEntity(wordsUrl, wordDto, Void.class);
        this.restTemplate.postForEntity(wordsUrl, wordDto, Void.class);
        responseEntity = this.restTemplate.getForEntity(wordsCountUrl + "/hubert", ResultDto.class);
        assertNotNull(responseEntity);
        resultDto = responseEntity.getBody();
        assertNotNull(resultDto);
        assertEquals(3, resultDto.getCount());
    }

    @Test
    void getTotalWordCount_same_word() {

        WordDto wordDto = new WordDto("hubert");
        this.restTemplate.postForEntity(wordsUrl, wordDto, Void.class);

        ResponseEntity<ResultDto> responseEntity = this.restTemplate.getForEntity(wordsCountUrl, ResultDto.class);
        assertNotNull(responseEntity);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        assertEquals(200, httpStatus.value());

        ResultDto resultDto = responseEntity.getBody();
        assertNotNull(resultDto);
        assertEquals(1, resultDto.getCount());

        this.restTemplate.postForEntity(wordsUrl, wordDto, Void.class);
        this.restTemplate.postForEntity(wordsUrl, wordDto, Void.class);
        responseEntity = this.restTemplate.getForEntity(wordsCountUrl, ResultDto.class);
        assertNotNull(responseEntity);
        resultDto = responseEntity.getBody();
        assertNotNull(resultDto);
        assertEquals(1, resultDto.getCount());
    }
    @Test
    void getTotalWordCount_different_word() {

        WordDto wordDto = new WordDto("hubert");
        this.restTemplate.postForEntity(wordsUrl, wordDto, Void.class);

        ResponseEntity<ResultDto> responseEntity = this.restTemplate.getForEntity(wordsCountUrl, ResultDto.class);
        assertNotNull(responseEntity);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        assertEquals(200, httpStatus.value());

        ResultDto resultDto = responseEntity.getBody();
        assertNotNull(resultDto);
        assertEquals(1, resultDto.getCount());

        wordDto = new WordDto("harris");
        this.restTemplate.postForEntity(wordsUrl, wordDto, Void.class);
        wordDto = new WordDto("hello");
        this.restTemplate.postForEntity(wordsUrl, wordDto, Void.class);
        responseEntity = this.restTemplate.getForEntity(wordsCountUrl, ResultDto.class);
        assertNotNull(responseEntity);
        resultDto = responseEntity.getBody();
        assertNotNull(resultDto);
        assertEquals(3, resultDto.getCount());
    }
}
