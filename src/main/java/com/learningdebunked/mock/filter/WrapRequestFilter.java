package com.learningdebunked.mock.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class manipulates the path
 * <p>
 * For example: /mock/v1/abc/1 is always manipulated to land at /mock/v1 to not error out
 *
 * @author Kapil
 * @created 25/05/2020 - 5:41 PM
 * @project mock
 */
public class WrapRequestFilter extends OncePerRequestFilter {

    private static final String[] PATHS = new String[]{"/mock/v1"};

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ServletContextFacadeRequestWrapper wrapper = new ServletContextFacadeRequestWrapper(request);
        String path = getMatchingContextPathForRequest(request);
        if (path != null) {
            wrapper.setContextPath(request.getContextPath() + path);
            String newPath = request.getServletPath().substring(path.length());
            request.setAttribute("lookupResourceKey", newPath);
            if (newPath.length() != 0) {
                newPath = "/";
            }
            wrapper.setServletPath("/mock/v1/");
        }
        filterChain.doFilter(wrapper, response);
    }

    public String getMatchingContextPathForRequest(HttpServletRequest request) {
        for (String path : PATHS) {
            if (request.getServletPath().startsWith(path)) {
                return path;
            }
        }
        return null;
    }

}
