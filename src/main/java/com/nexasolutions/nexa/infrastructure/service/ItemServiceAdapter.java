package com.nexasolutions.nexa.infrastructure.service;

import com.nexasolutions.nexa.domain.entity.Item;
import com.nexasolutions.nexa.domain.port.ItemServicePort;
import com.nexasolutions.nexa.infrastructure.application.dto.request.CreateItemRequestDTO;
import com.nexasolutions.nexa.infrastructure.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class ItemServiceAdapter implements ItemServicePort {

    private final ItemRepository itemRepository;

    public ItemServiceAdapter(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item getItemById(UUID id) {
        return itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
    }

    @Override
    public Item createItem(CreateItemRequestDTO itemRequest) {
        Item item = Item.builder()
                .name(itemRequest.name())
                .SKU(itemRequest.SKU())
                .storedQuantity(itemRequest.storedQuantity())
                .price(itemRequest.price())
                .build();
        return itemRepository.save(item);
    }

    @Override
    public Page<Item> getItems(int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return itemRepository.findAll(pageable);
    }
}
