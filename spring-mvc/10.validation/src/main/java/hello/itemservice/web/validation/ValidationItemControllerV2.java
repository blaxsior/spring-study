package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/validation/v2/items")
@RequiredArgsConstructor // final / @NonNull 자동 주입해줌
public class ValidationItemControllerV2 {

    private final ItemRepository itemRepository;
    private final ItemValidator itemValidator;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation/v2/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "validation/v2/addForm";
    }

//    @PostMapping("/add")
    public String addItem(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        // 검증 결과를 보관

        if(!StringUtils.hasText(item.getItemName())) {
            bindingResult.addError(new FieldError("item", "itemName", "상품 이름은 필수"));
        }
        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            bindingResult.addError(new FieldError("item", "price", "가격은 1000 ~ 1000000 까지 허용합니다"));
        }
        if(item.getQuantity() == null || item.getQuantity() > 9999) {
            bindingResult.addError(new FieldError("item", "quantity", "수량은 최대 9999입니다."));
        }
        // 복합 룰 검증
        if(item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.addError(new ObjectError("item", "가격 * 수량 합은 10000원 이상이어야 합니다. 현재 금액 = " + resultPrice));
            }
        }

        // 검증 실패 시 다시 입력 폼으로
        if(bindingResult.hasErrors()) {
            // 에러 설정
            return "validation/v2/addForm";
        }

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        // 검증 결과를 보관

        if(!StringUtils.hasText(item.getItemName())) {
            bindingResult.addError(new FieldError("item", "itemName", item.getItemName(), false, null, null, "상품 이름은 필수"));
        }
        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, null, null, "가격은 1000 ~ 1000000 까지 허용합니다"));
        }
        if(item.getQuantity() == null || item.getQuantity() > 9999) {
            bindingResult.addError(new FieldError("item", "quantity",  item.getQuantity(), false, null, null, "수량은 최대 9999입니다."));
        }
        // 복합 룰 검증
        if(item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.addError(new ObjectError("item", null, null,"가격 * 수량 합은 10000원 이상이어야 합니다. 현재 금액 = " + resultPrice));
            }
        }

        // 검증 실패 시 다시 입력 폼으로
        if(bindingResult.hasErrors()) {
            // 에러 설정
            return "validation/v2/addForm";
        }

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

//    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        log.info("errors = {}", bindingResult.getFieldErrors());
        log.info("obj name = {}", bindingResult.getObjectName());
        log.info("target = {}", bindingResult.getTarget());


        if(!StringUtils.hasText(item.getItemName())) {
            bindingResult.addError(new FieldError("item", "itemName", item.getItemName(), false, new String[]{"required.item.itemName"}, null, null));
        }

        final var MIN_ITEM_PRICE = 1000;
        final var MAX_ITEM_PRICE = 1000000;
        if(item.getPrice() == null || item.getPrice() < MIN_ITEM_PRICE || item.getPrice() > MAX_ITEM_PRICE) {
            bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, new String[]{"range.item.price"}, new Object[]{MIN_ITEM_PRICE, MAX_ITEM_PRICE}, null));
        }

        final var MAX_QUANTITY= 9999;
        if(item.getQuantity() == null || item.getQuantity() > MAX_QUANTITY) {
            bindingResult.addError(new FieldError("item", "quantity",  item.getQuantity(), false, new String[]{"max.item.quantity"}, new Object[]{MAX_QUANTITY}, null));
        }
        
        // 복합 룰 검증
        if(item.getPrice() != null && item.getQuantity() != null) {
            final var MIN_TOTAL_PRICE = 10000;
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < MIN_TOTAL_PRICE) {
                bindingResult.addError(new ObjectError("item", new String[] {"totalPriceMin"}, new Object[]{MIN_TOTAL_PRICE, resultPrice},null));
            }
        }

        // 검증 실패 시 다시 입력 폼으로
        if(bindingResult.hasErrors()) {
            // 에러 설정
            return "validation/v2/addForm";
        }

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

//    @PostMapping("/add")
    public String addItemV4(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        log.info("errors = {}", bindingResult.getFieldErrors());
        log.info("obj name = {}", bindingResult.getObjectName());
        log.info("target = {}", bindingResult.getTarget());


        if(!StringUtils.hasText(item.getItemName())) {
//            bindingResult.addError(new FieldError("item", "itemName", item.getItemName(), false, new String[]{"required.item.itemName"}, null, null));
            bindingResult.rejectValue("itemName", "required");
        }

        final var MIN_ITEM_PRICE = 1000;
        final var MAX_ITEM_PRICE = 1000000;
        if(item.getPrice() == null || item.getPrice() < MIN_ITEM_PRICE || item.getPrice() > MAX_ITEM_PRICE) {
//            bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, new String[]{"range.item.price"}, new Object[]{MIN_ITEM_PRICE, MAX_ITEM_PRICE}, null));
            bindingResult.rejectValue("price", "range", new Object[]{MIN_ITEM_PRICE, MAX_ITEM_PRICE}, null);
        }

        final var MAX_QUANTITY= 9999;
        if(item.getQuantity() == null || item.getQuantity() > MAX_QUANTITY) {
//            bindingResult.addError(new FieldError("item", "quantity",  item.getQuantity(), false, new String[]{"max.item.quantity"}, new Object[]{MAX_QUANTITY}, null));
            bindingResult.rejectValue("quantity", "max", new Object[]{MAX_QUANTITY}, null);
        }

        // 복합 룰 검증
        if(item.getPrice() != null && item.getQuantity() != null) {
            final var MIN_TOTAL_PRICE = 10000;
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < MIN_TOTAL_PRICE) {
//                bindingResult.addError(new ObjectError("item", new String[] {"totalPriceMin"}, new Object[]{MIN_TOTAL_PRICE, resultPrice},null));
                bindingResult.reject("totalPriceMin", new Object[]{MIN_TOTAL_PRICE, resultPrice},null);
            }
        }

        // 검증 실패 시 다시 입력 폼으로
        if(bindingResult.hasErrors()) {
            // 에러 설정
            return "validation/v2/addForm";
        }

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

//    @PostMapping("/add")
    public String addItemV5(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        log.info("errors = {}", bindingResult.getFieldErrors());
        log.info("obj name = {}", bindingResult.getObjectName());
        log.info("target = {}", bindingResult.getTarget());

        itemValidator.validate(item,bindingResult);

        // 검증 실패 시 다시 입력 폼으로
        if(bindingResult.hasErrors()) {
            // 에러 설정
            return "validation/v2/addForm";
        }

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

    @PostMapping("/add")
    public String addItemV6(@Validated @ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        log.info("model = {}", model);
        log.info("errors = {}", bindingResult.getFieldErrors());
        log.info("obj name = {}", bindingResult.getObjectName());
        log.info("target = {}", bindingResult.getTarget());

        // 검증 실패 시 다시 입력 폼으로
        if(bindingResult.hasErrors()) {
            // 에러 설정
            return "validation/v2/addForm";
        }

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/validation/v2/items/{itemId}";
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(itemValidator);
    }
}

