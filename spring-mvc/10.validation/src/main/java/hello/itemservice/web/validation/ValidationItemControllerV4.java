package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import hello.itemservice.domain.item.SaveCheck;
import hello.itemservice.domain.item.UpdateCheck;
import hello.itemservice.web.validation.dto.ItemSaveDto;
import hello.itemservice.web.validation.dto.ItemUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/validation/v4/items")
@RequiredArgsConstructor // final / @NonNull 자동 주입해줌
public class ValidationItemControllerV4 {

    private final ItemRepository itemRepository;
    private final ItemValidator2 itemValidator2;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation/v4/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v4/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "validation/v4/addForm";
    }

    @PostMapping("/add")
    public String addItem(
            @Validated(SaveCheck.class) @ModelAttribute("item") ItemSaveDto dto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        // 검증 실패 시 다시 입력 폼으로
        if (bindingResult.hasErrors()) {
            return "validation/v4/addForm";
        }
        Item item = new Item(dto.itemName(), dto.price(), dto.quantity());

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v4/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v4/editForm";
    }

    // json 파싱에 실패하면 컨트롤러가 호출되지 않는다.
    @PostMapping("/{itemId}/edit")
    public String edit(
            @PathVariable Long itemId,
            @Validated(UpdateCheck.class) @ModelAttribute("item") ItemUpdateDto dto,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "validation/v4/editForm";
        }

        Item item = new Item(
                dto.Id(),
                dto.itemName(),
                dto.price(),
                dto.quantity()
        );

        itemRepository.update(itemId, item);
        return "redirect:/validation/v4/items/{itemId}";
    }

    @InitBinder
    void initBinder(WebDataBinder binder) {
        binder.addValidators(itemValidator2);
    }
}

