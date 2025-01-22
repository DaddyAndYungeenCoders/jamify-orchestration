package com.orchestrator.orchestration.objects.dtos;

import lombok.Data;

@Data
public class CustomMetadata {
    private String objectId;

    @Override
    public String toString() {
        return "CustomMetadata{" +
                "objectId='" + objectId + '\'' +
                '}';
    }
}
