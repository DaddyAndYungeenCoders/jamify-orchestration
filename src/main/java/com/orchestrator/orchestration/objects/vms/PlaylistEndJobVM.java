package com.orchestrator.orchestration.objects.vms;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlaylistEndJobVM {
    UUID id;
    String userId;
    PlaylistEndJobPayloadVM data;
}
