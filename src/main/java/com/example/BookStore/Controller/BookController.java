package com.example.BookStore.Controller;

import com.example.BookStore.Dto.ApiResponse;
import com.example.BookStore.Dto.Book.CreateBook;
import com.example.BookStore.Model.Book;
import com.example.BookStore.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAllBooks() {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("OK")
                .data(bookService.getAllBooks())
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getBookById(@PathVariable("id") String id) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("OK")
                .data(bookService.getBookById(id))
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping("")
    public ResponseEntity<ApiResponse> addBook(@ModelAttribute CreateBook createBook) throws IOException {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("OK")
                .data(bookService.addBook(createBook))
                .build();
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBook(@PathVariable("id") String id) {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("OK")
                .data(bookService.DeleteBook(id))
                .build();
        return ResponseEntity.ok(response);
    }

}
