package com.example.BookStore.Base_exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    Book_Not_Found(999,"Book Not found In database", HttpStatus.NOT_FOUND),
    User_Not_Found(999,"User Not found In database", HttpStatus.NOT_FOUND),
    Login_Failed(1000,"Login Failed", HttpStatus.UNAUTHORIZED),
    Verify_Failed(1001,"Verify Failed", HttpStatus.UNAUTHORIZED),
    ;

    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    private Integer code;
    private String message;
    private HttpStatus httpStatus;
}