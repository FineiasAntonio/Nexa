package com.nexasolutions.nexa.infrastructure.application.controller;

import com.nexasolutions.nexa.domain.port.ServiceOrderServicePort;
import com.nexasolutions.nexa.infrastructure.application.dto.request.CreateServiceOrderRequestDTO;
import com.nexasolutions.nexa.infrastructure.application.dto.response.ServiceOrderResponseDTO;
import com.nexasolutions.nexa.infrastructure.exception.StandardError;
import com.nexasolutions.nexa.utils.ApiDefinitions;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.headers.Header;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Service Order")
@RequestMapping(ApiDefinitions.API_VERSION + ApiDefinitions.RESOURCE_SERVICE_ORDERS)
public class ServiceOrderController {

    private final ServiceOrderServicePort serviceOrderServicePort;

    public ServiceOrderController(ServiceOrderServicePort serviceOrderServicePort) {
        this.serviceOrderServicePort = serviceOrderServicePort;
    }

    @GetMapping
    @Operation(summary = "Returns a paginated list of service orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of service orders retrieved successfully"),
    })
    public ResponseEntity<Page<ServiceOrderResponseDTO>> getServiceOrders(int page, int size) {
        return ResponseEntity.ok(serviceOrderServicePort.getServiceOrders(page, size));
    }

    @PostMapping
    @Operation(summary = "Create a new service order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Service order created successfully"),
            @ApiResponse(
                    responseCode = "422",
                    description = "Validation Error",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            )
    })
    public ResponseEntity<ServiceOrderResponseDTO> createServiceOrder(@Valid @RequestBody CreateServiceOrderRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceOrderServicePort.createServiceOrder(request));
    }

}