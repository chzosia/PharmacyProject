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
        //TODO: implemenet get user
        //Create user DTO
        //Create user service
        //Create user entuty: username (unique), password, email (unique), phone number (unique),  id
        //Create user repository
        //Return user from db with id
        return userService.getUser(id);
    }

    @PostMapping()
    public CreateUserResponseDto createUser(@RequestBody CreateUserRequestDto user) {
        //TODO: Implement create user (only admin can do it)
        //Create create user DTO
        //Create create user in service, validate data, hash the password and save hashed password
        return userService.createUser(user);
    }

//    @PostMapping("/login")
//    public String loginUser() {
//        //TODO: Implement login user
//        //Create login DTO
//        //Create login service with user verification
//        //Return user login DTO with token
//        return jwtService.createToken("user");
//    }
}
