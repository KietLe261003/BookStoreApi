package com.example.BookStore.Mapper;

import com.example.BookStore.Dto.CreateUser;
import com.example.BookStore.Model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User toUser(CreateUser createUser);
}
