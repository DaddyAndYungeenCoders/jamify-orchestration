package com.orchestrator.orchestration.objects.vms;

import com.orchestrator.orchestration.objects.dtos.Tag;
import lombok.*;

import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlaylistDemandJobPayloadVM {
    Set<Tag> tags;
    int preferedNumberOfTitle;
}
