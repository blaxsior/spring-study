package com.example.mvc.basic.requestmapping;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {
    @GetMapping
    public String getUsers() {
        return "get user";
    }
    @GetMapping("/{userId}")
    public String findUser(@PathVariable("userId") String userId) {
        return "get userId=" + userId;
    }
    @PostMapping
    public String addUser() {
        return "post User";
    }
    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable("userId") String userId) {
        return "patch userId=" + userId;
    }
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") String userId) {
        return "delete userId=" + userId;
    }
}
