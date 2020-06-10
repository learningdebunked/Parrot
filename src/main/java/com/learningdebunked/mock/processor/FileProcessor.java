package com.learningdebunked.mock.processor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learningdebunked.mock.model.Templates;
import com.learningdebunked.mock.repository.TemplateRepository;
import com.learningdebunked.mock.utils.MockUtils;
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
                    //This is not needed as of now because we are using file system as a datastore
                   /* if (FilenameUtils.getExtension(filename).equals("res")) {
                        processResFile(filePath);
                    }*/

                    //if the extension is .template
                    if (FilenameUtils.getExtension(filename).equals("template")) {
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
        endpoint = node.get("path").textValue();
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
        }

    }

    /**
     * Method to read a file at a give path
     *
     * @param file
     * @return
     */
    public String extractTemplate(String file) {
        try {
            return MockUtils.readFile(file);
        } catch (IOException e) {
            e.printStackTrace();
            //TODO if the record mode is on , hit the actual endpoint and get the response and save it with file name
            //TODO Log and handle the exception
        }
        return null;
    }
}
