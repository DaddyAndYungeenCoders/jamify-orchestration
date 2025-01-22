package com.orchestrator.orchestration.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class EngineWebClient {
    /**
     * API key for Jamify Engine.
     */
    @Value("${config.engine.jamify-engine-api-key}")
    private String jamifyApiKey;

    /**
     * Base URL for UAA.
     */
    @Value("${config.engine.url.base}")
    private String engineUrl;

    @Bean(name = "engineServiceWebClient")
    public WebClient orchestratorWebClient() {
        return WebClient.builder()
                .baseUrl(engineUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("X-API-KEY", jamifyApiKey)
                .build();
    }
}

