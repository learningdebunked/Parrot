package com.learningdebunked.mock.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author Kapil
 * @created 20/05/2020 - 10:45 PM
 * @project mock
 */
public class MockUtils {

    /**
     * Method to validate if the json in the template file is a valid json
     *
     * @param json as string in the template file
     * @return true if the string is a valid json else false
     */
    public static boolean validateJson(String json) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
