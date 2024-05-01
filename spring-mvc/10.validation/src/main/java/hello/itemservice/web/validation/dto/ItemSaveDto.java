package hello.itemservice.web.validation.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public record ItemSaveDto(
        @NotBlank
        String itemName,
        @NotNull
        @Range(min = 1000, max = 1000000)
        Integer price,
        @NotNull
        @Max(value = 9999)
        Integer quantity
) { }
