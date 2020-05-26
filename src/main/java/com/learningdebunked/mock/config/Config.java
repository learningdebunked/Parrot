package com.learningdebunked.mock.config;

import com.learningdebunked.mock.filter.WrapRequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Kapil
 * @created 25/05/2020 - 5:46 PM
 * @project mock
 */
@Configuration
public class Config {

    @Bean
    public WrapRequestFilter wrapRequestFilter() {
        return new WrapRequestFilter();
    }

    @Bean
    public FilterRegistrationBean wrapRequestFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(wrapRequestFilter());
        registrationBean.setName("wrapRequestFilter");
        registrationBean.setOrder(-1000001);
        return registrationBean;
    }
}
