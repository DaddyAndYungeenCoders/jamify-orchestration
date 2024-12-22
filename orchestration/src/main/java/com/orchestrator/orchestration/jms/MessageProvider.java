package com.orchestrator.orchestration.jms;

import com.orchestrator.orchestration.objects.vms.QueueMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageProvider {
    private final JmsTemplate jmsTemplate;

    public void sendMessage(QueueMessage message) {
        jmsTemplate.convertAndSend("playlistDemandQueue", message);
    }
}
