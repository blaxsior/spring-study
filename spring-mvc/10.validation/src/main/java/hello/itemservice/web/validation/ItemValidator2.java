package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ItemValidator2 implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        // 타입이 대응될 수 있는지 정보를 반환
        return Item.class.isAssignableFrom(clazz);
    }

    // Errors는 BindingResult의 부모 타입.
    @Override
    public void validate(Object target, Errors errors) {
        // 다운캐스팅해서 사용해야 함
        Item item = (Item)target;

        // 복합 룰 검증
        if(item.getPrice() != null && item.getQuantity() != null) {
            final var MIN_TOTAL_PRICE = 10000;
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < MIN_TOTAL_PRICE) {
                errors.reject("totalPriceMin", new Object[]{MIN_TOTAL_PRICE, resultPrice},null);
            }
        }
    }
}
