package com.orchestrator.orchestration.services.implementations;

import com.orchestrator.orchestration.objects.dtos.PlaylistDemandJobDTO;
import com.orchestrator.orchestration.objects.vms.PlaylistDemandJobVM;
import com.orchestrator.orchestration.objects.vms.PlaylistEndJobPayloadVM;
import com.orchestrator.orchestration.services.interfaces.OrchestratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrchestratorServiceImpl implements OrchestratorService {
    //private final JmsService jmsService;
    //private final PlaylistDemandMapper mapper;

    @Override
    public UUID publishMessage(PlaylistDemandJobDTO demand) {
        PlaylistDemandJobVM demandVM = new PlaylistDemandJobVM();
        //jmsService.publishPlaylistDemand(demandVM);
        return demandVM.getId();
    }

    @Override
    public void onReceiveMessage(PlaylistEndJobPayloadVM response) {
        // TODO
        handle(response);
    }

    private void handle(PlaylistEndJobPayloadVM response) {
        log.debug("Sending REST request to save the newly created playlist!");
        // TODO
    }

}