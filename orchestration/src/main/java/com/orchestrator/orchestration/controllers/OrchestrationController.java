package com.orchestrator.orchestration.controllers;

import com.orchestrator.orchestration.objects.dtos.PlaylistDemandJobDTO;
import com.orchestrator.orchestration.objects.vms.PlaylistEndJobVM;
import com.orchestrator.orchestration.services.interfaces.OrchestratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrchestrationController {
    private final OrchestratorService service;

    @PostMapping("/playlist/generate")
    public ResponseEntity<UUID> generatePlaylist(@RequestBody PlaylistDemandJobDTO dto) {
        return ResponseEntity.ok(service.publishMessage(dto));
    }

    /**
     * @deprecated
     * JUST FOR TESTING
     * TODO remove
     * @param vm
     * @return
     */
    @PostMapping("/playlist/end")
    public ResponseEntity<UUID> generatePlaylist(@RequestBody PlaylistEndJobVM vm) {
        return ResponseEntity.ok(service.publishMessage(vm));
    }
}