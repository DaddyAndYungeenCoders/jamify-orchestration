package com.orchestrator.orchestration.jms.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.jms.annotation.JmsListener;

public interface MessageConsumers<T> {

    @JmsListener(destination = "jamify.orchestrator.playlist-done")
    void onMessageReceived(String messagePayload) throws JsonProcessingException;
}
