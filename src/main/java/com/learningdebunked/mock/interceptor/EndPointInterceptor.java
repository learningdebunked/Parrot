package com.learningdebunked.mock.interceptor;

import com.learningdebunked.mock.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Kapil
 * @created 22/05/2020 - 7:17 PM
 * @project mock
 */
@Component
public class EndPointInterceptor implements HandlerInterceptor {

    @Value("${dir.url}")
    String dirUrl;

    @Autowired
    TemplateRepository templateRepository;

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

/*        //path that errored out
        String uri = modelAndView.getModelMap().getAttribute("path").toString();

        //TODO real bad way of coding...imo
        String newURI = uri.substring(5); //count / + app name mock
        List<Templates> templatesList = templateRepository.findByEndpoint(newURI);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(templatesList.get(0).getJsonTemplate());
        out.flush();*/
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception exception) throws Exception {

    }
}

