package com.learningdebunked.mock.service;

import com.learningdebunked.mock.model.Templates;
import com.learningdebunked.mock.processor.FileProcessor;
import com.learningdebunked.mock.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author Kapil
 * @created 26/05/2020 - 9:12 PM
 * @project mock
 * <p>
 * This class does a look up in redis and if found in redis goes and queries database
 * <p>
 * If the key is not found in redis , based on the header parameters passed in the request we can redirect the call to a real service
 * <p>
 * TODO Temporarily this service does a look up in the repo directly without validating the key in redis.
 */
@Service
public class SearchService {

    private Enumeration<String> headerNames;
    private String queryString;
    private String resourceId;


    @Autowired
    private TemplateRepository templateRepository;

    @Value("${dir.url}")
    private String dirPath;

    @Autowired
    private FileProcessor fileProcessor;

    /**
     * Find the template from the repository based on the search key
     *
     * @param searchKey
     * @return
     */
    private Templates findTemplate(String searchKey) {
        return templateRepository.findByUri(searchKey).get(0);
    }

    /**
     * Method to extract the temp
     * @param request
     * @return
     */
    public String extractTemplate(HttpServletRequest request) {
        preprocessRequest(request);
        String templateFileName = findTemplate(this.resourceId).getFile();
        return fileProcessor.extractTemplate(dirPath + "/" + templateFileName);
    }

    /**
     * This can be used to carry out any additional logic if needed
     *
     * @param request
     */
    private void preprocessRequest(HttpServletRequest request) {
        this.headerNames = request.getHeaderNames();
        this.queryString = request.getQueryString();
        this.resourceId = request.getAttribute("lookupResourceKey").toString();
    }
}
