package com.example.BookStore.Mapper;

import com.example.BookStore.Dto.Book.CreateBook;
import com.example.BookStore.Model.Book;
import org.mapstruct.Mapper;

@Mapper
public interface BookMapper {
    Book toBook (CreateBook createBook);
}
