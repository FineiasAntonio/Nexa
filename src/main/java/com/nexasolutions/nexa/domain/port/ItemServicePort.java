package com.nexasolutions.nexa.domain.port;

import com.nexasolutions.nexa.domain.entity.Item;
import com.nexasolutions.nexa.infrastructure.application.dto.request.CreateItemRequestDTO;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ItemServicePort {
    Item getItemById(UUID id);
    Item createItem(CreateItemRequestDTO item);
    Page<Item> getItems(int page, int size);
}
