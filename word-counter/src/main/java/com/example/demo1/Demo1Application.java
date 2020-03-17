package com.example.demo1;

import com.example.demo1.model.WordBucket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Demo1Application {

	/**
	 * An in-memory word store to demonstrate this functionality.
	 */
    public final static Map<String, WordBucket> wordStore = new HashMap<>();

	/**
	 * The spring boot main program.
	 * @param args any user arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
	}

}
