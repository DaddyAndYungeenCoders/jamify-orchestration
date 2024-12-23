package com.orchestrator.orchestration.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orchestrator.orchestration.objects.dtos.PlaylistDemandJobDTO;
import com.orchestrator.orchestration.objects.dtos.Tag;
import com.orchestrator.orchestration.objects.vms.PlaylistDemandJobPayloadVM;
import com.orchestrator.orchestration.services.interfaces.OrchestratorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WithMockUser
@WebMvcTest(controllers = OrchestrationController.class)
public class OrchestrationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrchestrationController orchestrationController;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrchestratorService mockOrchestratorService;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(orchestrationController, "service", mockOrchestratorService);
    }

    @Test
    public void shouldAskToGenerateAPlaylist() throws Exception {
        // GIVEN
        Long userId = 1L;
        Tag tag = new Tag(1L, "Triste");
        Set<Tag> tags = new HashSet<>();
        tags.add(tag);
        PlaylistDemandJobPayloadVM payload = new PlaylistDemandJobPayloadVM();
        payload.setTags(tags);
        payload.setPreferedNumberOfTitle(20);
        PlaylistDemandJobDTO testDTO = new PlaylistDemandJobDTO(userId, payload);
        UUID testedUUID = new UUID(1L, 1L);

        Mockito.when(mockOrchestratorService.publishMessage(Mockito.eq(testDTO))).thenReturn(testedUUID);

        String testDTOJson = objectMapper.writeValueAsString(testDTO);

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.post("/api/playlist/generate")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testDTOJson))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // THEN
        Mockito.verify(mockOrchestratorService, Mockito.times(1)).publishMessage(Mockito.eq(testDTO));
    }
}
