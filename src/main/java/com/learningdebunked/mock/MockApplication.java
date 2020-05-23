package com.learningdebunked.mock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MockApplication {

    public static void main(String[] args) {
        SpringApplication.run(MockApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean dispatcherRegistration()
    {
        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet());
        registration.addUrlMappings("/");
        System.out.println("*************** I landed in the dispatcher servlet****************");
        return registration;
    }

    @Bean(name = DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
    public DispatcherServlet dispatcherServlet()
    {
        return new DispatcherServlet();
    }
}
