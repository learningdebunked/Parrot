package com.learningdebunked.mock.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Class to enable logging at the method level
 *
 * @author Kapil
 * @project mock
 */
@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.learningdebunked.mock.service.FileWatcherService.*(..))")
    public void logBeforeAllMethods(JoinPoint joinPoint) {
        //TODO this has to be debug logs
        System.out.println("Entering method:" + joinPoint.getSignature().getName());
    }

    @After("execution(* com.learningdebunked.mock.service.FileWatcherService.*(..))")
    public void afterAdvice(JoinPoint joinPoint) {
        //TODO this has to be debug logs
        System.out.println("Exiting method:" + joinPoint.getSignature().getName());
    }
}
