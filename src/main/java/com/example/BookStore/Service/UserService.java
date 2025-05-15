package com.example.BookStore.Service;

import com.example.BookStore.Base_exception.AppException;
import com.example.BookStore.Base_exception.ErrorCode;
import com.example.BookStore.Dto.CreateUser;
import com.example.BookStore.Mapper.UserMapper;
import com.example.BookStore.Model.User;
import com.example.BookStore.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public List<User> findAll() {
        return userRepository.findAll();
    }
    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(()-> new AppException(ErrorCode.User_Not_Found));
    }
    public User addUser(CreateUser createUser) {
        User user = userMapper.toUser(createUser);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(createUser.getPassword()));
        user.setRole("User");
        return userRepository.save(user);
    }
}
