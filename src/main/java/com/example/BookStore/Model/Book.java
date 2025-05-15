package com.example.BookStore.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document("Books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    private String id;
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
    private List<String> imageUrl;
    private Date createAt;
    private Date updateAt;
}
