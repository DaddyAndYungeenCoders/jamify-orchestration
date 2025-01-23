package com.orchestrator.orchestration.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orchestrator.orchestration.jms.interfaces.MessageConsumers;
import com.orchestrator.orchestration.objects.vms.PlaylistEndJobVM;
import com.orchestrator.orchestration.services.interfaces.OrchestratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageConsumer implements MessageConsumers<PlaylistEndJobVM> {
    private final OrchestratorService orchestratorService;


    @JmsListener(destination = "jamify.orchestrator.playlist-done")
    @Override
    public void onMessageReceived(String messagePayload) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        PlaylistEndJobVM message = mapper.readValue(messagePayload, PlaylistEndJobVM.class);
        log.info("Received playlist end job with id {}", message.getId());
        // Process message
        orchestratorService.onReceiveMessage(message);
    }
}
