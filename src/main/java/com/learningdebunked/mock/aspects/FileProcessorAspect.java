package com.learningdebunked.mock.aspects;

import com.learningdebunked.mock.utils.MockUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Kapil
 * @project mock
 */
@Aspect
@Component
public class FileProcessorAspect {

    @Before(value = "execution(* com.learningdebunked.mock.processor.FileProcessor.process(..)) && args(filePath,fileName)")
    public void beforeAdvice(JoinPoint joinPoint, String filePath, String fileName) throws IOException {
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath + "/" + fileName)));
        boolean isValid = MockUtils.validateJson(fileContent);
        if (!isValid){
            throw new RuntimeException("Errors in the Json being validated");
        }
    }
}
