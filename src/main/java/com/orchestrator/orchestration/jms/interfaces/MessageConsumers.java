package com.orchestrator.orchestration.jms.interfaces;

public interface MessageConsumers<T> {
    void onMessageReceived(T message);
}
