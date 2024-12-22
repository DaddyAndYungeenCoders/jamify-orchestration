package com.orchestrator.orchestration.objects.vms;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDemandJobVM extends QueueMessage {
    private Long userId;
    private PlaylistDemandJobPayloadVM payload;
}