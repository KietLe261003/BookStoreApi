package com.example.BookStore.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {
    Integer code;
    String message;
    Object data;
}