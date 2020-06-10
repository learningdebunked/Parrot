package com.learningdebunked.mock.controller;

import exceptions.MissingTemplateException;
import exceptions.MockAppError;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Kapil
 * @created 09/06/2020 - 5:11 PM
 * @project mock
 */
@RestControllerAdvice
public class MockAPIControllerAdvice {

    @Value("${app.sendreport.uri}")
    private String sendReportUri;
    @Value("${app.api.version}")
    private String currentApiVersion;

    @ExceptionHandler(MissingTemplateException.class)
    public ResponseEntity<MockAppError> handleNonExistingHero(MissingTemplateException ex) {
        final MockAppError error = new MockAppError(
                currentApiVersion,
                Integer.toString(HttpStatus.NOT_FOUND.value()),
                "Template is not configured",
                "app-exceptions",
                "No Template configured , try back with record feature enabled.",
                "Template and response templates missing",
                sendReportUri
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
