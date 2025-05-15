package com.example.BookStore.Service;

import com.example.BookStore.Base_exception.AppException;
import com.example.BookStore.Base_exception.ErrorCode;
import com.example.BookStore.Dto.Book.CreateBook;
import com.example.BookStore.Mapper.BookMapper;
import com.example.BookStore.Model.Book;
import com.example.BookStore.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookMapper bookMapper;
    String uploadPath = "D:/ProjectCode/SpringBoot/Uploads/Book/";

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    public Book getBookById(String id) {
        return bookRepository.findById(id).orElseThrow(()-> new AppException(ErrorCode.Book_Not_Found));
    }
    public Book addBook(CreateBook createBook) throws IOException {
        Book book =bookMapper.toBook(createBook);
        List<String> imageUrls = new ArrayList<>();
        for (MultipartFile image : createBook.getImages()) {
            String imageName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
            File imageFile = new File(uploadPath + imageName);
            image.transferTo(imageFile); // Lưu file
            imageUrls.add("/Uploads/Book/" + imageName); // Lưu URL file
        }
        book.setImageUrl(imageUrls);
        book.setCreateAt(new Date());
        book.setUpdateAt(new Date());
        return bookRepository.save(book);
    }
    public String DeleteBook(String id) {
        bookRepository.deleteById(id);
        return "Deleted Book";
    }
}
