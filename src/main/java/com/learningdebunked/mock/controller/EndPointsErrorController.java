package com.learningdebunked.mock.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Kapil
 * @created 23/05/2020 - 1:04 PM
 * @project mock
 */
@Controller
public class EndPointsErrorController implements ErrorController {

    @RequestMapping("/error")
    @ResponseBody
    public String handleError(HttpServletRequest request, HttpServletResponse response) throws  Exception{
        //TODO if at all we ever landup here , return something graceful
        return  "abc";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
