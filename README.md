# Parrot 
Parrot is a mock service which allows us to mock any server or service.

This is useful in the following scenarios:

* With fast, local setup of microservices APIs 
* Automated testing of FE components 
* Automated testing in isolation of the  system-under-test to ensure tests run reliably and only fail when there is a genuine bug.

The service works the following way:

* Find matching template  and return the json repsonse
* If no matching template is found , based on template configuration proxy the request and get response
* If no proxy header attribute is set return a error code with error message
* Reply with static or dynamic JSON or XML Payload


# Prerequisites
Knowledge of the following technologies and frameworks

* Java8
* SprintBoot
* Drools Rules Engine
* Spring Data JPA REST


## About / Synopsis

* Spin up a light weight spring boot based mock service




## Installation

* From command line ./gradlew bootRun



## Build

*  gradlew clean build



## How to Setup

*  Create a folder named templates
*  Create a template file with extension .temp
*  Create a json file , a sample response file to be served by the rest end points with .json extension
*  Move the json file and template file to the folder templates
