package org.paasify.cellr.backend;

import org.paasify.cellr.backend.fault.FaultInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BackendApplication implements WebMvcConfigurer {

    @Autowired
    private FaultInterceptor faultInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(faultInterceptor);
    }

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

}
