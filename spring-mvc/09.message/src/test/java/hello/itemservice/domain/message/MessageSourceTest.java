package hello.itemservice.domain.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class MessageSourceTest {
    @Autowired
    MessageSource msg;

    @Test
    void helloMessage() {
        String result = msg.getMessage("hello", null, null);
        assertThat(result).isEqualTo("안녕");
    }

    @Test
    void helloMessageEn() {
        String result = msg.getMessage("hello", null, Locale.ENGLISH);
        assertThat(result).isEqualTo("hello");
    }

    @Test
    void noCode() {
        assertThatThrownBy(() -> {
            msg.getMessage("no-code", null, null);
        }).isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void noCodeDefault() {
        String result = msg.getMessage("no-code", null, "default",null);
        assertThat(result).isEqualTo("default");
    }


    @Test
    void arguments() {
        String result = msg.getMessage("hello.name", new Object[] {"test"},null);
        assertThat(result).isEqualTo("안녕 test");
    }
}
