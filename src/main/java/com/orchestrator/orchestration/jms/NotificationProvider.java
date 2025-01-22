package com.orchestrator.orchestration.jms;

import com.orchestrator.orchestration.objects.vms.NotificationVM;
import com.orchestrator.orchestration.objects.vms.PlaylistDemandJobVM;
import com.orchestrator.orchestration.objects.vms.PlaylistEndJobVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationProvider extends AbstractMessageProvider<NotificationVM> {
    public NotificationProvider(JmsTemplate jmsTemplate) {
        super(jmsTemplate);
    }

    @Override
    void specificHandle(NotificationVM notif) {
        if (notif.getRoomId() != null && !notif.getRoomId().isEmpty()) {
            log.info("Sent notification to room: {}", notif.getRoomId());
        }
        if (notif.getDestId() != null && !notif.getDestId().isEmpty()) {
            log.info("Sent notification to user: {}", notif.getDestId());
        }
        if (notif.getMetadata() != null) {
            log.info("Sent notification with object id: {}", notif.getMetadata().getObjectId());
        }
        log.info("Sent notification: {} - {}", notif.getTitle(), notif.getContent());
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
        return;
    }
}
