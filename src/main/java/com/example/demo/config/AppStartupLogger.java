package com.example.demo.config;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AppStartupLogger {

    private static final Logger logger = LoggerFactory.getLogger(AppStartupLogger.class);

    @PostConstruct
    public void logStartup() {
        logger.info(" API Pokémon iniciada com sucesso!");
    }
}
