package hello.itemservice.web.validation.annotation;

import hello.itemservice.domain.item.Item;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MinTotalPriceValidator implements ConstraintValidator<MinTotalPrice, Item> {
    private int MIN_TOTAL_PRICE;
    @Override
    public void initialize(MinTotalPrice constraintAnnotation) {
        this.MIN_TOTAL_PRICE = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Item item, ConstraintValidatorContext context) {

        // 복합 룰 검증
        if(item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < MIN_TOTAL_PRICE) {
//                context.buildConstraintViolationWithTemplate("t").addParameterNode()
                return false;
            }
        }
        return true;
    }
}
