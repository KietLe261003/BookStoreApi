package com.example.BookStore.Controller;

import com.example.BookStore.Dto.ApiResponse;
import com.example.BookStore.Dto.CreateUser;
import com.example.BookStore.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("")
    public ResponseEntity<ApiResponse> getAllUsers() {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("OK")
                .data(userService.findAll())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping("")
    public ResponseEntity<ApiResponse> addUser(@RequestBody CreateUser createUser) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("OK")
                .data(userService.addUser(createUser))
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
