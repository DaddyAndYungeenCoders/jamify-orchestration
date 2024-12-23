package com.orchestrator.orchestration.jms.interfaces;

import com.orchestrator.orchestration.objects.vms.PlaylistEndJobVM;

public interface MessageProviders<T> {
    void sendMessageToQueue(String queueName, T message);

    /**
     * @deprecated
     * @param queueName
     * @param message
     * TODO remove
     */
    @Deprecated
    void sendMessageToQueue(String queueName, PlaylistEndJobVM message);
}
