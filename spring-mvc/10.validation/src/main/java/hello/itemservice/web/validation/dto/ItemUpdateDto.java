package hello.itemservice.web.validation.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public record ItemUpdateDto(
        @NotNull
        Long Id,
        @NotBlank
        String itemName,
        @NotNull
        @Range(min = 1000, max = 1000000)
        Integer price,
        // 수정 시 수량은 자유롭게 변경 가능
        @NotNull
        Integer quantity
) { }