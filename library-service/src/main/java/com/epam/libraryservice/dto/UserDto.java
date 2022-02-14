package com.epam.libraryservice.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String userName;
    private String email;
    private String name;
    List<BookDto> books;

    public UserDto(String userName, String email, String name) {
        this.userName = userName;
        this.email = email;
        this.name = name;
    }
}
