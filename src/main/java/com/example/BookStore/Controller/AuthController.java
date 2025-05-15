package com.example.BookStore.Controller;


import com.example.BookStore.Dto.ApiResponse;
import com.example.BookStore.Dto.User.LoginRequest;
import com.example.BookStore.Dto.User.TokenCheck;
import com.example.BookStore.Service.AuthService;
import com.nimbusds.jose.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("")
    public ResponseEntity<ApiResponse> Login(@RequestBody LoginRequest loginRequest) {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Login Successful")
                .data(authService.Login(loginRequest.getEmail(), loginRequest.getPassword()))
                .build();
        return ResponseEntity.ok(response);
    }
    @PostMapping("/checktoken")
    public ResponseEntity<ApiResponse> checkToken(@RequestBody TokenCheck authorization) throws ParseException, JOSEException {
        String token = authorization.getToken();
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Login Successful")
                .data(authService.introspect(token))
                .build();
        return ResponseEntity.ok(response);
    }



}