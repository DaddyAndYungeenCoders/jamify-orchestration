package com.orchestrator.orchestration.jms;

import com.orchestrator.orchestration.objects.vms.PlaylistDemandJobVM;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jms.core.JmsTemplate;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PlaylistMessageProviderTest {
    @Mock
    private JmsTemplate jmsTemplate;

    @Spy
    @InjectMocks
    private PlaylistMessageProvider provider;

    @Captor
    private ArgumentCaptor<String> queueCaptor;

    @Captor
    private ArgumentCaptor<PlaylistDemandJobVM> messageCaptor;

    @Test
    void shouldSendMessageToQueue() {
        // Given
        String queueName = "test-queue";
        PlaylistDemandJobVM message = new PlaylistDemandJobVM();
        message.setId(UUID.randomUUID());

        // When
        provider.sendMessageToQueue(queueName, message);

        // Then
        verify(jmsTemplate).convertAndSend(queueCaptor.capture(), messageCaptor.capture());
        assertEquals(queueName, queueCaptor.getValue());
        assertEquals(message, messageCaptor.getValue());
    }

    @Test
    void shouldCallSpecificHandleBeforeSending() {
        // Given
        String queueName = "test-queue";
        PlaylistDemandJobVM message = new PlaylistDemandJobVM();
        message.setId(UUID.randomUUID());

        // When
        provider.sendMessageToQueue(queueName, message);

        // Then
        InOrder inOrder = inOrder(provider, jmsTemplate);
        inOrder.verify(provider).specificHandle(message);
        inOrder.verify(jmsTemplate).convertAndSend(anyString(), Optional.ofNullable(any()));
    }

    /*
    @Test
    void shouldLogMessageIdInSpecificHandle() {
        // Given
        PlaylistDemandJobVM message = new PlaylistDemandJobVM();
        UUID id = UUID.randomUUID();
        message.setId(id);

        // When
        provider.specificHandle(message);

        // Then
        // add log4j test dependency for more detailed assertions
    }
    */
}