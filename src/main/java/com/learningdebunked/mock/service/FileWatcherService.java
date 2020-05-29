package com.learningdebunked.mock.service;

import com.learningdebunked.mock.processor.FileProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Kapil
 * @project Mock Service
 */
@Component
public class FileWatcherService {

    // on the server this is the path to the directory where templates are added. any changes to the files on this directory are monitored and published
    @Value("${dir.url}")
    String dirUrl;

    @Value("${dev.mode}")
    String mode;

    @Autowired
    FileProcessor fileProcessor;

    /**
     * Method that monitors a given director and flush all valid json templates to the in memory database
     */
    public void monitor() {
        try {
            //TODO checkifDirExists , this can be an aspect
            if (checkIfFilesExist(dirUrl)) {
                //TODO decide if u want to use Executors.newWorkStealingPool(10) instead of fixed thread pool
                ExecutorService pool = Executors.newFixedThreadPool(10);
                fileProcessor.processFolder(pool, dirUrl);
                //TODO to lock the directory on the server such that the templates are not modified , otherwise updated templates are not saved or could be saved twice
                //Once the initial setup is done , the service should start watching the directory for changes
                readStream(dirUrl);
            }
        } catch (IOException | InterruptedException e) {
            //TODO unable to read the directory , may be there are file access issues
            //TODO Logging
            e.printStackTrace();
        }
    }

    /**
     * If there are existing templates , in dev mode we want all this to be flushed to the in memory database
     *
     * @param dirUrl
     * @return
     */
    //TODO make sure there are only files with .json and .template
    private boolean checkIfFilesExist(String dirUrl) {
        return Files.exists(Paths.get(dirUrl));
    }

    /**
     * Method to read the files created, updated or modified in the given directory
     */
    private void readStream(String dirPath) throws IOException, InterruptedException {
        WatchService watchService
                = FileSystems.getDefault().newWatchService();
        Path path = Paths.get(dirPath);
        path.register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);
        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                //if file is .template then write the updates to templates entity
                if (event.context().toString().endsWith(".template")) {
                    fileProcessor.processTemplateFile(dirPath + "/" + event.context().toString());
                }
                //if the file is .res then write the updates to response entity
                if (event.context().toString().endsWith(".res")) {
                    fileProcessor.processResFile(dirPath + "/" + event.context().toString());
                }
            }
            key.reset();
        }
    }
}
