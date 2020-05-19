package com.learningdebunked.mock.dto;

public class Url {

    private String protocol; //https or http
    private String path; // End point that we want to setup
    private Host host; // localhost:8080 or example.com

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }
}
