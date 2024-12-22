package com.orchestrator.orchestration.jms;

import com.orchestrator.orchestration.objects.vms.QueueMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageConsumer {

    @JmsListener(destination = "playlistResultQueue")
    public void receiveMessage(QueueMessage message) {
        log.info("receiving message with id {}", message.getId());
    }
}
