package com.blaxsior.itemservice.item.service;

import com.blaxsior.itemservice.item.dto.ItemCreateDto;
import com.blaxsior.itemservice.item.entity.ItemEntity;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    void create(ItemEntity dto);
    void edit(ItemEntity item);
    Optional<ItemEntity> findById(Long id);
    List<ItemEntity> findAll();
    void deleteById(Long id);
}
