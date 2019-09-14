package org.paasify.cellr.backend.config;

import org.paasify.cellr.backend.fault.FaultInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FaultInjectionConfig {
    @Value("${fault.pattern}")
    private String faultPattern;

    @Value("${fault.chance:0}")
    private double faultChance;

    @Value("${fault.statusCode:500}")
    private int faultStatusCode;

    @Bean
    public FaultInterceptor faultInterceptor() {
        return new FaultInterceptor(faultPattern, faultChance, faultStatusCode);
    }
}
