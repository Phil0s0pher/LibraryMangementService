package com.epam.libraryservice.service.interfaces;

import com.epam.libraryservice.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto getUserByUserName(String userName);

    void saveUser(UserDto userDto);

    void updateUser(String userName, UserDto userDto);

    void deleteUser(String userName);
}
