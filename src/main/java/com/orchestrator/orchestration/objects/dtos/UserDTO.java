package com.orchestrator.orchestration.objects.dtos;

import lombok.Builder;

@Builder
public record UserDTO(Long id, String name, boolean hasJamHostedAndRunning) {
}
