package com.orchestrator.orchestration.objects.vms;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDemandJobVM {
    private UUID id;
    private Long userId;
    private PlaylistDemandJobPayloadVM payload;
}