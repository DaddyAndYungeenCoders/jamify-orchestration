package com.orchestrator.orchestration.jms;

import com.orchestrator.orchestration.jms.interfaces.MessageConsumers;
import com.orchestrator.orchestration.objects.vms.PlaylistEndJobConverter;
import com.orchestrator.orchestration.objects.vms.PlaylistEndJobVM;
import com.orchestrator.orchestration.services.interfaces.OrchestratorService;
import jakarta.jms.JMSException;
import jakarta.jms.TextMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageConsumer implements MessageConsumers<TextMessage> {
    private final OrchestratorService orchestratorService;

    @Override
    @JmsListener(destination = "jamify.orchestrator.playlist-done")
    public void onMessageReceived(TextMessage message) {
        try {
            String json = message.getText();
            PlaylistEndJobVM playlistEndJobVM = PlaylistEndJobConverter.fromJson(json);
            System.out.println("Received playlist: " + playlistEndJobVM);
            orchestratorService.onReceiveMessage(playlistEndJobVM);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}