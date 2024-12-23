package com.orchestrator.orchestration.objects.mappers;

import com.orchestrator.orchestration.objects.dtos.PlaylistDemandJobDTO;
import com.orchestrator.orchestration.objects.vms.PlaylistDemandJobVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PlaylistDemandMapper {

    @Mapping(source = "payloadVM", target = "payload")
    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    PlaylistDemandJobVM toVM(PlaylistDemandJobDTO dto);

    @Mapping(source = "payload", target = "payloadVM")
    PlaylistDemandJobDTO toDTO(PlaylistDemandJobVM vm);
}