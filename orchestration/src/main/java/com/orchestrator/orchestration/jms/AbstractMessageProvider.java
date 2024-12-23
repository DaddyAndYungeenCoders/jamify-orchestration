package com.orchestrator.orchestration.jms;

import com.orchestrator.orchestration.jms.interfaces.MessageProviders;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public abstract class AbstractMessageProvider<T> implements MessageProviders<T> {
    protected final JmsTemplate jmsTemplate;

    @Override
    public void sendMessageToQueue(String queueName, T message) {
        specificHandle(message);
        jmsTemplate.convertAndSend(queueName, message);
    }

    abstract void specificHandle(T message);
}
