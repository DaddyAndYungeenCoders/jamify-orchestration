package com.orchestrator.orchestration.services.interfaces;

import com.orchestrator.orchestration.objects.dtos.PlaylistDemandJobDTO;
import com.orchestrator.orchestration.objects.vms.PlaylistEndJobPayloadVM;

import java.util.UUID;

public interface OrchestratorService {
    UUID publishMessage(PlaylistDemandJobDTO demand);

    void onReceiveMessage(PlaylistEndJobPayloadVM response);
}
