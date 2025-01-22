package com.orchestrator.orchestration.objects.vms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlaylistEndJobPayloadVM {
    Set<Long> musics;
    @JsonProperty(value = "description")
    String playlistDescription;
    @JsonProperty(value = "name")
    String playlistName;
}
