package org.paasify.cellr.backend.fault;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FaultInterceptor extends HandlerInterceptorAdapter {

    private final static String HEADER_NAME = "X-Fault-Interceptor";

    private final Pattern pattern;
    private final int faultStatusCode;
    private final Random random;
    private final double chance;

    public FaultInterceptor(String path, double chance, int faultStatusCode) {
        this.random = new Random();
        this.pattern = Pattern.compile(path);
        this.chance = chance;
        this.faultStatusCode = faultStatusCode;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Matcher matcher = pattern.matcher(request.getRequestURI());

        if(matcher.find()) {
            if(random.nextDouble() < this.chance) {
                response.sendError(this.faultStatusCode);
                response.setHeader(HEADER_NAME, Boolean.TRUE.toString());

                return false;
            }
        }

        return super.preHandle(request, response, handler);
    }
}
