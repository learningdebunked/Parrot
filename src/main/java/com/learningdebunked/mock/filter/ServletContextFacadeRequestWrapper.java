package com.learningdebunked.mock.filter;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author Kapil
 * @created 25/05/2020 - 5:37 PM
 * @project mock
 */
public class ServletContextFacadeRequestWrapper extends HttpServletRequestWrapper {

    private String contextPath;
    private String servletPath;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public ServletContextFacadeRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getContextPath() {
        if (StringUtils.isNotBlank(contextPath)) {
            return contextPath;
        }
        return super.getContextPath();
    }

    @Override
    public String getServletPath() {
        if (StringUtils.isNotBlank(servletPath)) {
            return servletPath;
        }
        return super.getServletPath();
    }

    @Override
    public String getRequestURI() {
        String requestURI = super.getRequestURI();
        if (requestURI.equals(contextPath)) {
            return requestURI + "/";

        }
        return requestURI;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public void setServletPath(String servletPath) {
        this.servletPath = servletPath;
    }
}
