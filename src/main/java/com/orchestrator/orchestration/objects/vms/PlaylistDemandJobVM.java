package com.orchestrator.orchestration.objects.vms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDemandJobVM implements Serializable {
    @Serial
    private static final long serialVersionUID = 121354532164L;

    private UUID id;
    private Long userId;
    @JsonProperty("data")
    private PlaylistDemandJobPayloadVM payload;
}