package com.orchestrator.orchestration.objects.vms;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlaylistEndJobPayloadVM {
    Set<Long> musics;
}
