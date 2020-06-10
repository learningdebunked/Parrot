package com.learningdebunked.mock.dto;

/**
 * This class represents the http response sent back by the end point
 */
public class Response {

    Error error;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
