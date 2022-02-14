package com.epam.libraryservice.service;

import com.epam.libraryservice.client.UserClient;
import com.epam.libraryservice.dto.UserDto;
import com.epam.libraryservice.service.interfaces.LibraryService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;
    @Mock
    LibraryService libraryService;
    @Mock
    UserClient userClient;

    @Test
    @DisplayName("getAllUsers should return all user")
    public void getAllUserShouldReturnAllUsers(){
        UserDto userDto = new UserDto();
        when(userClient.getAllUsers()).thenReturn(List.of(userDto));
        List<UserDto> allUsers = userService.getAllUsers();
        Assertions.assertEquals(userDto , allUsers.get(0));
    }

    @Test
    @DisplayName("getUser should return user by userName")
    public void getUserShouldReturnUserByUserName()  {
        UserDto userDto = new UserDto();
        when(userClient.getUserByUserName(anyString())).thenReturn(userDto);
        UserDto user = userService.getUserByUserName("aditya");
        Assertions.assertEquals(userDto , user);
    }

    @Test
    @DisplayName("saveUser should save user")
    public void saveUserShouldSaveUser(){
        UserDto userDto = new UserDto();
        Assertions.assertDoesNotThrow(()->userService.saveUser(userDto));


    }

    @Test
    @DisplayName("updateUser should update user")
    public void updateUserShouldUpdateUserIfExist(){
        UserDto userDto = new UserDto();
        Assertions.assertDoesNotThrow(()->userService.updateUser("aditya",userDto));
    }

    @Test
    @DisplayName("deleteUser should delete user")
    public void deleteUserShouldDeleteUserIfExist(){
        Assertions.assertDoesNotThrow(()->userService.deleteUser("aditya"));
    }
}