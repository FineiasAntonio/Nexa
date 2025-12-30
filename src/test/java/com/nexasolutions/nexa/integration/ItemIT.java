package com.nexasolutions.nexa.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexasolutions.nexa.infrastructure.application.dto.request.CreateItemRequestDTO;
import com.nexasolutions.nexa.utils.ApiDefinitions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ItemIT {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String BASE_URL = ApiDefinitions.API_VERSION + ApiDefinitions.RESOURCE_ITEMS;

    @Test
    void shouldCreateAndRetrieveItem() throws Exception {
        var request = new CreateItemRequestDTO("Test Item", "SKU123", 10, new BigDecimal("99.99"));

        mockMvc.perform(
                        post(BASE_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value("Test Item"))
                .andExpect(jsonPath("$.SKU").value("SKU123"))
                .andExpect(jsonPath("$.storedQuantity").value(10))
                .andExpect(jsonPath("$.price").value(99.99));

        mockMvc.perform(get(BASE_URL + "?page=0&size=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].name").value("Test Item"));
    }
}
