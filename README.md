# Parrot MockService
Spins up a light weight spring boot based mock service. For each request the following happens

* Find matching template  and return the json repsonse
* If no matching template is found , based on template configuration proxy the request and get response
* If no proxy header attribute is set return a error code with error message

# Prerequisites
Knowledge of the following technologies and frameworks

* Java8
* SprintBoot
* Drools Rules Engine
* Spring Data JPA REST


## About / Synopsis

* Spin up a light weight spring boot based mock service
* Project status: working/prototype
* Nuxeo Support



## Installation

* From command line ./gradlew bootRun



## Build

*  gradlew clean build



## How to Setup

*  Create a folder /templates
*  Create a template file with extension .temp
*  Create a json file , a sample response file to be served by the rest end points with .json extension
*  Move the json file and template file to the folder. /templates
