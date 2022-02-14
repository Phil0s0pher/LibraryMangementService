package com.epam.userservice.service;

import com.epam.userservice.dto.UserDto;
import com.epam.userservice.exception.DuplicateUserException;
import com.epam.userservice.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto getUserByUserName(String userName) throws UserNotFoundException;

    void saveUser(UserDto user) throws DuplicateUserException;

    void updateUser(String userName, UserDto user) throws UserNotFoundException;

    void deleteUser(String userName) throws UserNotFoundException;
}
