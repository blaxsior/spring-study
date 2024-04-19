package com.blaxsior.itemservice.item.service;

import com.blaxsior.itemservice.item.dto.ItemCreateDto;
import com.blaxsior.itemservice.item.entity.ItemEntity;
import com.blaxsior.itemservice.item.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepo;
    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepo = itemRepository;
    }

    @Override
    @Transactional
    public void create(ItemEntity item) {
        itemRepo.save(item);
    }

    @Override
    @Transactional
    public void edit(ItemEntity item) {
        itemRepo.save(item);
    }

    @Override
    public Optional<ItemEntity> findById(Long id) {
        return itemRepo.findById(id);
    }

    @Override
    public List<ItemEntity> findAll() {
        return itemRepo.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        itemRepo.deleteById(id);
    }
}
