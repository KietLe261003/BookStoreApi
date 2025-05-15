package com.example.BookStore.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("User")
public class User {

    @Id
    private String id;

    private String email;
    private String password;
    private String role;

    private String fullName;
    private String phoneNumber;
    private String address;
}
