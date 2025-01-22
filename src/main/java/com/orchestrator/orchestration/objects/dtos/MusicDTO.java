package com.orchestrator.orchestration.objects.dtos;

import lombok.Builder;

import java.util.Set;

@Builder
public record MusicDTO(Long id, String isrc, String author, String title, String imgUrl, int tempo, double energy, Set<Tag> tags) {
}
