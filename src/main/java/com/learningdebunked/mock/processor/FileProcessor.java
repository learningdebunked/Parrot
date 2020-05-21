package com.learningdebunked.mock.processor;

import com.learningdebunked.mock.utils.MockUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;

/**
 * @author Kapil
 */
@Component
public class FileProcessor {

    public void processFolder(ExecutorService pool, String inputPath) {
        File inputFolder = new File(inputPath);
        for (String filename : inputFolder.list()) {
            String filePath = inputFolder.toPath().resolve(filename).toString();
            pool.execute(() -> {
                //DocumentWriter.write(filePath, writeTo);
                try {
                    if (MockUtils.validateJson(new String(Files.readAllBytes(Paths.get(filePath))))) {
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
     * @param filePath
     * @param fileName
     * @throws IOException
     */
    public void process(String filePath, String fileName) throws IOException {
            Path path = Paths.get(filePath);
            System.out.println("Modified file is a valid json");
            System.out.println(new String(Files.readAllBytes(Paths.get(path + "/" + fileName))));
    }
}
