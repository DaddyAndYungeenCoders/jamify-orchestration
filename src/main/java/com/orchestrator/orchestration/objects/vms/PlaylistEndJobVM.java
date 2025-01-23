package com.orchestrator.orchestration.objects.vms;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonCreator
    public PlaylistEndJobVM(
            @JsonProperty("id") String id,
            @JsonProperty("userId") String userId,
            @JsonProperty("data") PlaylistEndJobPayloadVM data
    ) {
        this.id = UUID.fromString(id);
        this.userId = userId;
        this.data = data;
    }
}
