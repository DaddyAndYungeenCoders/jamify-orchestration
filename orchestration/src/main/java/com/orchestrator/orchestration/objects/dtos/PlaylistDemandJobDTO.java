package com.orchestrator.orchestration.objects.dtos;


import com.orchestrator.orchestration.objects.vms.PlaylistDemandJobPayloadVM;

public record PlaylistDemandJobDTO(Long userId, PlaylistDemandJobPayloadVM payloadVM) { }
