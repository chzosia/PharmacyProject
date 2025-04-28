package org.example.pharmacy.controller;

import org.example.pharmacy.controller.dto.CreateUserRequestDto;
import org.example.pharmacy.controller.dto.CreateUserResponseDto;
import org.example.pharmacy.controller.dto.UserResponseDto;
import org.example.pharmacy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "{id}")
    public UserResponseDto getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping()
    public CreateUserResponseDto createUser(@RequestBody CreateUserRequestDto user) {
        return userService.createUser(user);
    }

}
