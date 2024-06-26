package com.blaxsior.itemservice.item.controller;

import com.blaxsior.itemservice.item.dto.ItemCreateDto;
import com.blaxsior.itemservice.item.dto.ItemEditDto;
import com.blaxsior.itemservice.item.entity.ItemEntity;
import com.blaxsior.itemservice.item.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public String getIndexListPage(Model model) {
        List<ItemEntity> items = this.itemService.findAll();
        model.addAttribute("items", items);

        return "items/index";
    }

    @GetMapping("/add")
    public String getAddFormPage() {
        return "items/add-form";
    }

    @PostMapping("/add")
    public String addNewItem(@Valid @ModelAttribute ItemCreateDto dto, RedirectAttributes redirectAttributes) {
        ItemEntity item = ItemEntity.builder()
                .price(dto.getPrice())
                .amount(dto.getAmount())
                .name(dto.getName())
                .build();

        itemService.create(item);

        redirectAttributes.addAttribute("itemId", item.getId());
        // 남는 것들은 쿼리 파라미터 형식으로 들어감
        redirectAttributes.addAttribute("status", true);

        return "redirect:/items/{itemId}";
    }

    @GetMapping("/{itemId}")
    public String getItemDetailPage(@PathVariable("itemId") Long itemId, Model model) {
        Optional<ItemEntity> itemOptional = this.itemService.findById(itemId);
        // 나중에 404 페이지로 연결.
        ItemEntity item = itemOptional.orElseThrow(() -> new RuntimeException("대상 유저가 없습니다"));

        model.addAttribute("item", item);

        return "items/detail";
    }

    @GetMapping("/{itemId}/edit")
    public String getItemEditPage(@PathVariable("itemId") Long itemId, Model model) {
        Optional<ItemEntity> itemOptional = this.itemService.findById(itemId);
        // 나중에 404 페이지로 연결.
        ItemEntity item = itemOptional.orElseThrow(() -> new RuntimeException("대상 유저가 없습니다"));

        model.addAttribute("item", item);

        return "items/edit";
    }

    @PostMapping("/{itemId}/edit")
    public String editItem(@PathVariable("itemId") Long itemId, @Valid @ModelAttribute ItemEditDto dto) {
        if(dto.getId() != itemId) {
            throw new IllegalArgumentException("item id not match");
        }

        ItemEntity item = ItemEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .price(dto.getPrice())
                .amount(dto.getAmount())
                .build();

        this.itemService.edit(item);
        return "redirect:/items";
    }
}
