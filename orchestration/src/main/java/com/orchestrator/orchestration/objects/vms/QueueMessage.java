package com.orchestrator.orchestration.objects.vms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QueueMessage implements Serializable {
    @Serial
    private static final long serialVersionUID = 121354532164L;

    private UUID id;
}
