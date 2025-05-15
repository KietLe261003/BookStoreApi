package com.example.BookStore.Base_exception;

import com.example.BookStore.Dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse res = new ApiResponse();

        res.setCode(errorCode.getCode());
        res.setMessage(errorCode.getMessage());

        return ResponseEntity.status(errorCode.getHttpStatus()).body(res);
    }
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity handleRuntimeException(RuntimeException e) {
        ApiResponse Res = ApiResponse.builder()
                .code(400)
                .message("Request bad")
                .data(e.getMessage()).build();
        return ResponseEntity.status(400).body(Res);
    }
}