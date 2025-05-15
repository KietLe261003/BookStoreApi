package com.example.BookStore.Dto.Book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBook {

    private String title;
    private String author;
    private String description;
    private String publisher;
    private int publicationYear;
    private String language;
    private List<String> categories;
    private double price;
    private int stockQuantity;
    private String isbn;
    private List<MultipartFile> images;
}
