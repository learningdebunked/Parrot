package com.learningdebunked.mock.service;

import com.learningdebunked.mock.model.Templates;
import com.learningdebunked.mock.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private TemplateRepository templateRepository;

    /**
     * Find the template from the repository based on the search key
     * @param searchKey ex: /abc/1/
     * @return
     */
    public Templates findTemplate(String searchKey){
        return templateRepository.findByEndpoint(searchKey).get(0);
    }
}
