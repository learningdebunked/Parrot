package com.learningdebunked.mock.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;

/**
 * @author Kapil
 * @project Mock Service
 */
@Component
public class FileWatcherService {

    // on the server this is the path to the directory where templates are added. any changes to the files on this directory are monitored and published
    @Value("${dir.url}")
    String dirUrl;

    /**
     * Method that monitors a given director and flushe all valid json templates to the inmemory database
     */
    public void monitor() {
        try {
            System.out.println("***** Directory being monitored is:********" + dirUrl);
            if (checkIfFilesExist(dirUrl)) {

                //TODO also need to check if its first time setup , if yes and if the production mode is true we can push these templates to production db
                //TODO read the existing templates and publish them into the database.
                //need to lock the directory on the server such that the templates are not modified , otherwise updated templates are not saved or could be saved twice
                //tODO instantiate multiple threads to read all the files and update them into database
                //TODO this should happen only if the profile in which the server is running is a dev profile
                //TODO add profile prod.mode = false

            } else {
                //This gets executed in the profile "prod.mode = true"
                readStream(dirUrl);
            }

        } catch (IOException | InterruptedException e) {
            //TODO unable to read the directory , may be there are file access issues
            e.printStackTrace();
        }
    }

    private boolean checkIfFilesExist(String dirUrl) {
        return false;
    }

    /**
     * Method to read the files created, updated or modified in the given directory
     */
    private void readStream(String filePath) throws IOException, InterruptedException {
        WatchService watchService
                = FileSystems.getDefault().newWatchService();
        Path path = Paths.get(filePath);
        path.register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);
        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                if (event.context().toString().endsWith(".template")) {
                    //print the file contents
                    validateJson(new String(Files.readAllBytes(Paths.get(path + "/" + event.context().toString()))));
                }
            }
            key.reset();
        }
    }

    private boolean validateJson(String json) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
