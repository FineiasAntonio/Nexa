package com.nexasolutions.nexa.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexasolutions.nexa.infrastructure.controller.dto.request.CreateClientRequestDTO;
import com.nexasolutions.nexa.infrastructure.controller.dto.request.CreateEquipmentRequestDTO;
import com.nexasolutions.nexa.infrastructure.controller.dto.request.CreateServiceOrderRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ServiceOrderIT {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldCreateServiceOrderWithNewClient() throws Exception {
        var clientDto = new CreateClientRequestDTO("João Silva", "123456789", "joao@email.com", "912345678");
        var equipmentDto = new CreateEquipmentRequestDTO("iPhone 13", "Apple", "SN123", "Ecrã partido");
        var request = new CreateServiceOrderRequestDTO(null, clientDto, equipmentDto);

        mockMvc.perform(
                    post("/service-orders")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(jsonPath("$.publicId").isNotEmpty())
                .andExpect(jsonPath("$.status").value("OPEN"));
    }

}
