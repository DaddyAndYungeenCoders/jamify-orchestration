package com.orchestrator.orchestration.jms;

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

    @Override
    @JmsListener(destination = "playlistResultQueue")
    public void onMessageReceived(PlaylistEndJobVM message) {
        log.info("Received playlist end job with id {}", message.getId());
        orchestratorService.onReceiveMessage(message);
    }
}
