package com.orchestrator.orchestration;

import com.orchestrator.orchestration.jms.interfaces.MessageProviders;
import com.orchestrator.orchestration.objects.dtos.PlaylistDemandJobDTO;
import com.orchestrator.orchestration.objects.dtos.Tag;
import com.orchestrator.orchestration.objects.vms.PlaylistDemandJobPayloadVM;
import com.orchestrator.orchestration.objects.vms.PlaylistDemandJobVM;
import com.orchestrator.orchestration.services.interfaces.OrchestratorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@SpringBootTest
class OrchestrationApplicationTests {
	@Mock
	private MessageProviders<PlaylistDemandJobVM> messageProvider;

	@Autowired
	private OrchestratorService orchestratorService;

	private final static String PRODUCE_QUEUE_NAME = "jamify.ai.playlist-gen";

	@BeforeEach
	void setUp() {
		ReflectionTestUtils.setField(orchestratorService, "messageProvider", messageProvider);
		Mockito.doNothing().when(messageProvider).sendMessageToQueue(Mockito.any(String.class), Mockito.any(PlaylistDemandJobVM.class));
	}

	@Test
	void shouldPublishAMessageToQueue() {
		// GIVEN
		PlaylistDemandJobDTO testDTO = buildPlaylistDemandJobDTO();

		// WHEN
		UUID result = orchestratorService.publishMessage(testDTO);

		// THEN
		Assertions.assertNotNull(result);
		Mockito.verify(messageProvider).sendMessageToQueue(Mockito.eq(PRODUCE_QUEUE_NAME), Mockito.any(PlaylistDemandJobVM.class));
	}

	private PlaylistDemandJobDTO buildPlaylistDemandJobDTO() {
		Long userId = 1L;
		Tag tag = new Tag(1L, "Triste");
		Set<Tag> tags = new HashSet<>();
		tags.add(tag);
		PlaylistDemandJobPayloadVM payload = new PlaylistDemandJobPayloadVM();
		payload.setTags(tags);
		payload.setPreferedNumberOfTitle(20);
		return new PlaylistDemandJobDTO(userId, payload);
	}
}
