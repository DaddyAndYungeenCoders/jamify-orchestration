package com.orchestrator.orchestration.objects.dtos;


import com.orchestrator.orchestration.objects.vms.PlaylistDemandJobPayloadVM;
import lombok.NonNull;

public record PlaylistDemandJobDTO(@NonNull Long userId, @NonNull PlaylistDemandJobPayloadVM payloadVM) { }
