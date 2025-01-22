package com.orchestrator.orchestration.services.implementations;

import com.orchestrator.orchestration.jms.interfaces.MessageProviders;
import com.orchestrator.orchestration.objects.dtos.PlaylistDemandJobDTO;
import com.orchestrator.orchestration.objects.mappers.PlaylistDemandMapper;
import com.orchestrator.orchestration.objects.vms.NotificationVM;
import com.orchestrator.orchestration.objects.vms.PlaylistDemandJobVM;
import com.orchestrator.orchestration.objects.vms.PlaylistEndJobPayloadVM;
import com.orchestrator.orchestration.objects.vms.PlaylistEndJobVM;
import com.orchestrator.orchestration.services.interfaces.OrchestratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class OrchestratorServiceImpl implements OrchestratorService {

    // TODO remove consume queue name from here since there will be no need to send anything in this queue
    @Value("${spring.activemq.queues.playlist.consume}")
    private String consumeQueueName;

    @Value("${spring.activemq.queues.playlist.produce}")
    private String produceQueueName;

    @Value("${spring.activemq.queues.notification.produce}")
    private String notificationQueueName;

    private final MessageProviders<PlaylistDemandJobVM> messageProvider;

    private final MessageProviders<NotificationVM> notificationProvider;

    private final PlaylistDemandMapper playlistDemandMapper;

    @Override
    public UUID publishMessage(PlaylistDemandJobDTO demand) {
        PlaylistDemandJobVM demandVM = playlistDemandMapper.toVM(demand);

        messageProvider.sendMessageToQueue(produceQueueName, demandVM);

        return demandVM.getId();
    }

    @Override
    public void publishNotification(NotificationVM notif) {
        notificationProvider.sendMessageToQueue(notificationQueueName, notif);
    }

    /**
     * @deprecated
     * @param endJobVM
     * @return
     * TODO remove
     */
    @Deprecated
    public UUID publishMessage(PlaylistEndJobVM endJobVM) {
        messageProvider.sendMessageToQueue(consumeQueueName, endJobVM);

        return endJobVM.getId();
    }

    @Override
    public void onReceiveMessage(PlaylistEndJobVM response) {
        handle(response);
    }

    private void handle(PlaylistEndJobVM response) {
        log.debug("Sending REST request to save the newly created playlist with job id {}!", response.getId());
        // FIXME handle it with a feign call to playlist endpoint
    }

}