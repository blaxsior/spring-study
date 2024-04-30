package hello.itemservice.validation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.FieldError;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.ObjectError;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageCodesResolverTest {
    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    void messageCodesResolverObject() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item");
        Arrays.stream(messageCodes).forEach(code -> System.out.println("code = " + code));

//        new ObjectError("item", new String[]{"required.item", "required"}, null, "default message");
        assertThat(messageCodes).containsExactly("required.item", "required");
    }

    @Test
    void messageCodesResolverField() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item", "itemName", String.class);
        Arrays.stream(messageCodes).forEach(code -> System.out.println("code = " + code));
        // bindingResult.rejectValue("itemName", "required");
        new FieldError("item", "itemName", null, false, messageCodes, null, null);

//        assertThat(messageCodes).containsExactly("required.item.itemName", "required.item", "required");
    }
}
