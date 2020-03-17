package com.example.demo1.validator;

import org.apache.commons.lang3.StringUtils;

public class WordValidator {

    public static void validateWord(String word) throws Exception {

        if (StringUtils.isBlank(word)) {
            throw new Exception("Word is null or empty");
        } else if (isNonAlphabet(word)) {
            throw new Exception("Word Invalid");
        }
    }

    private static boolean isNonAlphabet(String word) {
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!Character.isLetter(c)) {
                return true;
            }
        }
        return false;
    }

}
