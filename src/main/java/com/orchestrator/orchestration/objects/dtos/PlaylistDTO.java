package com.orchestrator.orchestration.objects.dtos;

import lombok.Builder;

import java.util.Set;

@Builder
public record PlaylistDTO(Long id, String name, UserDTO author, int likes, Set<MusicDTO> musics, Set<Tag> tags) {
}
