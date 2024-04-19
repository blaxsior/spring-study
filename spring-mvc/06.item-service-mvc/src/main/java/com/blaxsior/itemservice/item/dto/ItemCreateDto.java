package com.blaxsior.itemservice.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCreateDto {
    @NotBlank(message = "이름이 필요합니다.")
    private String name;

    @NotNull
//    @Pattern(regexp = "\\d+", message = "가격은 숫자 형식이어야 합니다.")
    private Integer price;

    @NotNull
//    @Pattern(regexp = "\\d+", message = "개수는 숫자 형식이어야 합니다.")
    private Integer amount;
}
