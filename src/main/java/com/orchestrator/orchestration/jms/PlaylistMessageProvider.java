package com.orchestrator.orchestration.jms;

import com.orchestrator.orchestration.objects.vms.PlaylistDemandJobVM;
import com.orchestrator.orchestration.objects.vms.PlaylistEndJobVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PlaylistMessageProvider extends AbstractMessageProvider<PlaylistDemandJobVM> {
    public PlaylistMessageProvider(JmsTemplate jmsTemplate) {
        super(jmsTemplate);
    }

    @Override
    void specificHandle(PlaylistDemandJobVM message) {
        log.info("Sent playlist demand job with id {}", message.getId());
    }

    /**
     * @deprecated
     * @param queueName
     * @param message
     * TODO remove
     */
    @Deprecated
    @Override
    public void sendMessageToQueue(String queueName, PlaylistEndJobVM message) {
        this.jmsTemplate.convertAndSend(queueName, message);
    }
}
