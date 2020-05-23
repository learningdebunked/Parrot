package com.learningdebunked.mock.config;

import com.learningdebunked.mock.interceptor.EndPointInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Kapil
 * @created 22/05/2020 - 7:20 PM
 * @project mock
 */
@Component
public class MockServiceConfig extends WebMvcConfigurerAdapter {
    @Autowired
    EndPointInterceptor endPointInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(endPointInterceptor);
    }
}
