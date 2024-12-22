package com.orchestrator.orchestration.services.interfaces;

import com.orchestrator.orchestration.objects.dtos.PlaylistDemandJobDTO;
import com.orchestrator.orchestration.objects.vms.PlaylistEndJobPayloadVM;
import com.orchestrator.orchestration.objects.vms.PlaylistEndJobVM;

import java.util.UUID;

public interface OrchestratorService {
    UUID publishMessage(PlaylistDemandJobDTO demand);

    /**
     * @deprecated
     * @param endJobVM
     * @return
     * TODO remove
     */
    UUID publishMessage(PlaylistEndJobVM endJobVM);

    void onReceiveMessage(PlaylistEndJobVM response);
}
