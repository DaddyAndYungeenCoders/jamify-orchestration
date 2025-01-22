package com.orchestrator.orchestration.objects.vms;

import com.orchestrator.orchestration.annotations.EitherDestIdOrRoomId;
import com.orchestrator.orchestration.objects.dtos.CustomMetadata;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema
@EitherDestIdOrRoomId
public class NotificationVM {

    @Schema(description = "Destination ID, the ID of the user for the notif", example = "1165152")
    // TODO: needs to decide wich id to use -> userProviderId ?
    private String destId;

    @Schema(description = "Room ID, the ID of the room for the notif", example = "jam_118665-ezafze351...")
    private String roomId;

    @Schema(description = "Title of the notification", example = "Génération de playlist terminée !")
    @NotBlank
    private String title;

    @Schema(description = "Content of the notification", example = "La génération de la playlist a été effectuée avec succès.")
    @NotBlank
    private String content;

    @Schema(description = "Custom metadata for the notification. Useful if frontend needs to navigate or get the object of the notif : new playlist, new event...", $schema = "CustomMetadata")
    private CustomMetadata metadata;
}
