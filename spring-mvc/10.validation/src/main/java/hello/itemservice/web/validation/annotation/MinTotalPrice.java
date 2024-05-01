package hello.itemservice.web.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MinTotalPriceValidator.class)
@Documented
public @interface MinTotalPrice {
    int zv() default 100000;
    int max() default 20000;
    int value();

    String message() default "총 금액이 최소 가격 {value}을 만족하지 않습니다.";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
