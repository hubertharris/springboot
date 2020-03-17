package com.example.demo1.controller;

import com.example.demo1.dto.ResultDto;
import com.example.demo1.dto.WordDto;
import com.example.demo1.service.WordCountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@WebMvcTest(WordCountController.class)
class WordCountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private WordCountService wordCountService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getWordCount() throws Exception {

        // Mock the wordCountService invocation.
        ResultDto resultDto = new ResultDto(10);
        when(wordCountService.getWordCount("hubert")).thenReturn(resultDto);

        // Perform the test
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/words/count/hubert");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();


        // Assert the response.
        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        ResultDto actResultDto = objectMapper.readValue(response.getContentAsString(), ResultDto.class);
        assertNotNull(actResultDto);
        assertEquals(10, actResultDto.getCount());
    }

    @Test
    void getWordCount_with_zero_word() throws Exception {

        // Mock the wordCountService invocation.
        ResultDto resultDto = new ResultDto(0);
        when(wordCountService.getWordCount("hubert")).thenReturn(resultDto);

        // Perform the test
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/words/count/hubert");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();


        // Assert the response.
        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        ResultDto actResultDto = objectMapper.readValue(response.getContentAsString(), ResultDto.class);
        assertNotNull(actResultDto);
        assertEquals(0, actResultDto.getCount());
    }

    @Test
    void getWordCount_when_incorrect_URI() throws Exception {

        // Perform the test
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/word123/count/hubert");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();


        // Assert the response.
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    void getWordCount_when_service_throws_exception() throws Exception {

        // Mock the wordCountService invocation to throw exception.
        when(wordCountService.getWordCount("hubert")).thenThrow(Exception.class);

        // Perform the test
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/words/count/hubert");

        // Assert the response.
        Exception exception = assertThrows(Exception.class, () -> {
            mockMvc.perform(requestBuilder).andReturn();
        });
    }

    @Test
    void getWordCount_when_service_throws_exception_with_message() throws Exception {

        // Mock the wordCountService invocation to throw exception.
        when(wordCountService.getWordCount("hubert")).thenThrow(new Exception("Something went terribly wrong"));

        // Perform the test
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/words/count/hubert");

        // Assert the response.
        Exception exception = assertThrows(Exception.class, () -> {
            mockMvc.perform(requestBuilder).andReturn();
        });
        assertEquals("Something went terribly wrong", exception.getCause().getMessage());
    }

    @Test
    void getTotalWordCount() throws Exception {

        // Mock the wordCountService invocation.
        ResultDto resultDto = new ResultDto(100);
        when(wordCountService.getTotalWordsCount()).thenReturn(resultDto);

        // Perform the test
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/words/count");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();


        // Assert the response.
        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        ResultDto actResultDto = objectMapper.readValue(response.getContentAsString(), ResultDto.class);
        assertNotNull(actResultDto);
        assertEquals(100, actResultDto.getCount());
    }

    @Test
    void getTotalWordCount_with_zero_word() throws Exception {

        // Mock the wordCountService invocation.
        ResultDto resultDto = new ResultDto(0);
        when(wordCountService.getTotalWordsCount()).thenReturn(resultDto);

        // Perform the test
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/words/count");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();


        // Assert the response.
        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        ResultDto actResultDto = objectMapper.readValue(response.getContentAsString(), ResultDto.class);
        assertNotNull(actResultDto);
        assertEquals(0, actResultDto.getCount());
    }

    @Test
    void getTotalWordCount_when_incorrect_URI() throws Exception {

        // Perform the test
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/words123/count");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();


        // Assert the response.
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    void getTotalWordCount_when_service_throws_exception() {

        // Mock the wordCountService invocation to throw exception.
        when(wordCountService.getTotalWordsCount()).thenThrow(RuntimeException.class);

        // Perform the test
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/words/count");

        // Assert the response.
        assertThrows(Exception.class, () -> {
            mockMvc.perform(requestBuilder).andReturn();
        });
    }

    @Test
    void getTotalWordCount_when_service_throws_exception_with_message() {

        // Mock the wordCountService invocation to throw exception.
        when(wordCountService.getTotalWordsCount()).thenThrow(new RuntimeException("Something went terribly wrong"));

        // Perform the test
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/words/count");

        // Assert the response.
        Exception exception = assertThrows(Exception.class, () -> {
            mockMvc.perform(requestBuilder).andReturn();
        });
        assertEquals("Something went terribly wrong", exception.getCause().getMessage());
    }

    @Test
    void addWord() throws Exception {

        // Prepare request body.
        WordDto wordDto = new WordDto("test");
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(wordDto);

        // Perform the test
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/words");
        requestBuilder.contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        // Verify if the mock service object is called exactly once.
        verify(wordCountService, times(1)).addWord("test");

        // Assert the response.
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    void addWord_when_incorrect_URI() throws Exception {

        // Perform the test
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/words123");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        // Assert the response.
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    /**
     * Learn how to use Mockito to throw exception from a method which does not return anything.
     * @throws Exception any exception thrown while invoking the method.
     */
    @Test
    void addWord_when_service_throws_exception() throws Exception {

        // Prepare request body.
        WordDto wordDto = new WordDto("test");
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(wordDto);

        // Mock the wordCountService invocation.
        Mockito.doThrow(Exception.class).when(wordCountService).addWord("test");

        // Perform the test
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/words");
        requestBuilder.contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson);

        // Assert the response.
        assertThrows(Exception.class, () -> {
            mockMvc.perform(requestBuilder).andReturn();
        });
    }

    @Test
    void addWord_when_service_throws_exception_with_message() throws Exception {

        // Prepare request body.
        WordDto wordDto = new WordDto("test");
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(wordDto);

        // Mock the wordCountService invocation.
        Mockito.doThrow(new Exception("Something went terribly wrong")).when(wordCountService).addWord("test");

        // Perform the test
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/words");
        requestBuilder.contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson);

        // Assert the response.
        Exception exception = assertThrows(Exception.class, () -> {
            mockMvc.perform(requestBuilder).andReturn();
        });
        assertEquals("Something went terribly wrong", exception.getCause().getMessage());
    }
}
