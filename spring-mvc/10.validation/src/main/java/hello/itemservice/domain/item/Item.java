package hello.itemservice.domain.item;

import hello.itemservice.web.validation.annotation.MinTotalPrice;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
//@MinTotalPrice(groups = {SaveCheck.class}, value = 30000)
//@ScriptAssert(lang = "jexl", script = "_this.price * _this.quantity >= 10000") // JDK 15 이상에서는 동작 X. Nashorn 추가하든지
public class Item {
//    @NotNull(groups = UpdateCheck.class)
    private Long id;

//    @NotBlank(groups = {UpdateCheck.class, SaveCheck.class }, message = "이름 공백 X")
    private String itemName;

//    @NotNull(groups = {UpdateCheck.class, SaveCheck.class })
//    @Range(groups = {UpdateCheck.class, SaveCheck.class }, min=1000, max=1000000) // hibernate에서만 적용됨
    private Integer price;

//    @NotNull(groups = {UpdateCheck.class, SaveCheck.class })
//    @Max(groups = { SaveCheck.class }, value=9999)
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
