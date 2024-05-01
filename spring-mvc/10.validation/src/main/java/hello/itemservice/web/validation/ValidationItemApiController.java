package hello.itemservice.web.validation;

import hello.itemservice.web.validation.dto.ItemSaveDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@Slf4j
@RestController
@RequestMapping("/validation/api/items")
public class ValidationItemApiController {

    @PostMapping("/add")
    public ResponseEntity<Object> addItem(
            @RequestBody @Validated ItemSaveDto dto,
            BindingResult bindingResult
    ) {
        log.info("API 컨트롤러 호출");

        if(bindingResult.hasErrors()) {
            log.info("검증 오류 발생");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(bindingResult.getAllErrors());
        }

        log.info("검증 성공");

        return ResponseEntity.ok(dto);
    }
}
