package com.blaxsior.exhandle.api;

import com.blaxsior.exhandle.resolver.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class ApiExceptionV2Controller {
    @GetMapping("/apiv2/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id) {
        if(id.equals("ex")) {
            throw new RuntimeException("잘못된 사용자");
        } else if (id.equals("bad")) {
            throw new IllegalArgumentException("입력 값이 잘못됨");
        } else if(id.equals("user-ex")) {
            throw new UserException("유저 예외 발생");
        }
        // 반환값도 적절한 json이어야 함.

        return new MemberDto(id, "hello" + id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult handleIllegalArgsEx(IllegalArgumentException e) {
        var errorResult = new ErrorResult(400, e.getMessage());
        return errorResult;
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResult> handleUserEx(UserException e) {
        var errorResult = new ErrorResult(400, e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResult);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResult handleEx(Exception e) {
        return new ErrorResult(500, "Internal Server Error");
    }


    record MemberDto(
            String memberId,
            String name) { }
}
