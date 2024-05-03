package hello.login.web.login;

import jakarta.validation.constraints.NotBlank;

public record LoginForm(
        @NotBlank
        String loginId,
        @NotBlank
        String password
) {}
