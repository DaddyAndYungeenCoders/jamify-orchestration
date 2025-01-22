package com.orchestrator.orchestration.objects.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.orchestrator.orchestration.objects.vms.PlaylistDemandJobPayloadVM;
import lombok.NonNull;

public record PlaylistDemandJobDTO(
        @NonNull Long userId,
        @JsonProperty("data")
        @NonNull PlaylistDemandJobPayloadVM payloadVM) { }
