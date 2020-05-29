package com.learningdebunked.mock.processor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learningdebunked.mock.model.Templates;
import com.learningdebunked.mock.repository.TemplateRepository;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @author Kapil
 */
@Component
public class FileProcessor {

    @Autowired
    TemplateRepository templateRepository;

    public void processFolder(ExecutorService pool, String inputPath) {
        File inputFolder = new File(inputPath);
        for (String filename : inputFolder.list()) {
            String filePath = inputFolder.toPath().resolve(filename).toString();
            pool.execute(() -> {
                try {
                    //if the extension is .res
                    if (FilenameUtils.getExtension(filename).equals("res")) {
                        processResFile(filePath);
                    }
                    //if the extension is .template
                    else if (FilenameUtils.getExtension(filename).equals("template")) {
                        processTemplateFile(filePath);
                    } else {
                        //log an error about junk files in the template
                    }

                } catch (IOException e) {
                    //Files were not found so throw an exception or exit gracefully or ignore TBD
                    e.printStackTrace();
                }
            });
        }
    }

    /**
     * Method to process the .template  files
     * From the file extract the verb and the end point
     * check if the end point and verb exists in the db
     * if not write to db
     *
     * @param filePath
     */
    public void processTemplateFile(String filePath) throws IOException {
        String endpoint = StringUtils.EMPTY;
        String verb = StringUtils.EMPTY;
        String filename = StringUtils.EMPTY;

        Path path = Paths.get(filePath);
        String content = new String(Files.readAllBytes(path));
        final ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(content);
        endpoint = node.get("endpoint").textValue();
        verb = node.get("verb").textValue();
        filename = node.get("filename").textValue();
        List<Templates> templatesList = templateRepository.findByUriAndVerb(endpoint, verb);
        //validate entry in db
        if (templatesList.size() != 0) {
            Templates template = templatesList.get(0);
            template.setFile(filename);
            templateRepository.save(template);
            //update the res in res entity
        } else {
            Templates template = new Templates();
            template.setFile(filename);
            template.setUri(endpoint);
            template.setVerb(verb);
            templateRepository.save(template);

            //process the .res file as in the filename , it is expected to be available in the same directory , if not available , then capture the error in the response entity
            //create the template in templates entity and res in response entity

        }

    }

    /**
     * Method to process the files ending with .res
     *
     * @param filePath
     */
    public void processResFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        String content = new String(Files.readAllBytes(path));
        final ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(content);

    }
}
