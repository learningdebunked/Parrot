package com.learningdebunked.mock.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.logging.Logger;

/**
 * @author Kapil
 */
@Component
public class FileProcessor {
    private static final Logger log = Logger.getGlobal();

    public void processFolder(ExecutorService pool, String inputPath) {
        File inputFolder = new File(inputPath);
        for (String filename : inputFolder.list()) {
            System.out.println("Multiple threads are processing the files......");
            String filePath = inputFolder.toPath().resolve(filename).toString();
            pool.execute(() -> {
                log.info("Start processing " + filePath);
                //DocumentWriter.write(filePath, writeTo);
                try {
                    System.out.println("***********individual thread processing file.....***************" + filename);
                    if (validateJson(new String(Files.readAllBytes(Paths.get(filePath))))) {
                        System.out.println(new String(Files.readAllBytes(Paths.get(filePath))));
                    }
                } catch (IOException e) {
                    //Files were not found so throw an exception or exit gracefully
                    e.printStackTrace();
                }
            });
        }
    }

    /**
     * Method to validate if the json in the template file is a valid json
     *
     * @param json as string in the template file
     * @return true if the string is a valid json else false
     */
    public boolean validateJson(String json) {
        try {
            System.out.println("json is being validated");
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
