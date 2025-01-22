package com.orchestrator.orchestration.objects.vms;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.orchestrator.orchestration.objects.dtos.Tag;
import lombok.*;

import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlaylistDemandJobPayloadVM {
    //Set<Tag> tags;
    Set<String> tags;
    @JsonProperty("numberOfTitle")
    int preferedNumberOfTitle;
    @JsonProperty("name")
    String playlistName;
    @JsonProperty("description")
    String playlistDescription;
}
