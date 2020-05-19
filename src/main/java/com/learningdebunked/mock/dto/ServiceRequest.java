package com.learningdebunked.mock.dto;

import java.util.List;

/**
 * This class has to be made OpenAPI3.0 compliant so it supports importing and exporting from post man
 * This class represents the basic http request used by the user to setup the mock end point
 */
public class ServiceRequest {

    List<Item> item;


}
