package com.nexasolutions.nexa.infrastructure.application.controller;

import com.nexasolutions.nexa.domain.entity.Item;
import com.nexasolutions.nexa.domain.port.ItemServicePort;
import com.nexasolutions.nexa.infrastructure.application.dto.request.CreateItemRequestDTO;
import com.nexasolutions.nexa.infrastructure.application.dto.response.ItemResponseDTO;
import com.nexasolutions.nexa.utils.ApiDefinitions;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Item")
@RequestMapping(ApiDefinitions.API_VERSION + ApiDefinitions.RESOURCE_ITEMS)
public class ItemController {

    private final ItemServicePort itemServicePort;

    public ItemController(ItemServicePort itemServicePort) {
        this.itemServicePort = itemServicePort;
    }

    @GetMapping
    @Operation(summary = "Returns a paginated list of items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of items retrieved successfully"),
    })
    public ResponseEntity<Page<ItemResponseDTO>> getItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Item> items = itemServicePort.getItems(page, size);
        return ResponseEntity.ok(items.map(this::toResponseDTO));
    }

    @PostMapping
    @Operation(summary = "Create a new item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item created successfully"),
    })
    public ResponseEntity<ItemResponseDTO> createItem(@Valid @RequestBody CreateItemRequestDTO request) {
        Item createdItem = itemServicePort.createItem(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponseDTO(createdItem));
    }

    private ItemResponseDTO toResponseDTO(Item item) {
        return new ItemResponseDTO(
                item.getId(),
                item.getName(),
                item.getSKU(),
                item.getStoredQuantity(),
                item.getPrice()
        );
    }
}
