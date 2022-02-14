package com.epam.userservice.service;

import com.epam.userservice.dto.UserDto;
import com.epam.userservice.entity.User;
import com.epam.userservice.exception.DuplicateUserException;
import com.epam.userservice.exception.UserNotFoundException;
import com.epam.userservice.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    @InjectMocks
    UserServiceImpl userService;
    @Mock
    UserRepository userRepository;
    @Mock
    ModelMapper modelMapper;
    UserDto userDto;
    User user;

    @Before
    public void setUp(){
        userDto = new UserDto("","","");
        user = new User("","","");
        when(modelMapper.map(userDto , User.class)).thenReturn(user);
        when(modelMapper.map(user , UserDto.class)).thenReturn(userDto);
    }


    @Test
    @DisplayName("getAllUsers should return all user")
    public void getAllUserShouldReturnAllUsers(){
        when(userRepository.findAll()).thenReturn(List.of(user,user));
        Assertions.assertEquals(List.of(userDto , userDto) , userService.getAllUsers());
    }
    @Test
    @DisplayName("getUser should return user by userName")
    public void getUserShouldReturnUserByUserName() throws UserNotFoundException {
        when(userRepository.findById(anyString())).thenReturn(java.util.Optional.of(user));
        Assertions.assertEquals(userDto, userService.getUserByUserName(""));
    }

    @Test
    @DisplayName("getUser should throw exception if user not exist")
    public void getUserShouldThrowExceptionIfUserNotExist(){
        when(userRepository.findById(anyString())).thenReturn(java.util.Optional.empty());
        Assertions.assertThrows(UserNotFoundException.class , ()->userService.getUserByUserName(""));
    }

    @Test
    @DisplayName("saveUser should save user")
    public void saveUserShouldSaveUser(){
        Assertions.assertDoesNotThrow(()->userService.saveUser(userDto));
    }
    @Test
    @DisplayName("saveUser should throw exception if user already exist")
    public void saveUserShouldThrowExceptionIfUserAlreadyExist(){
        when(userRepository.existsById(anyString())).thenReturn(true);
        Assertions.assertThrows(DuplicateUserException.class ,()->userService.saveUser(userDto));
    }

    @Test
    @DisplayName("updateUser should throw exception if user not exist")
    public void updateUserShouldThrowExceptionIfUserNotExist(){
        when(userRepository.findById(anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(UserNotFoundException.class ,()->userService.updateUser(userDto.getUserName() ,userDto));
    }
    @Test
    @DisplayName("updateUser should update user")
    public void updateUserShouldUpdateUserIfExist(){
        when(userRepository.findById(anyString())).thenReturn(Optional.of(user));
        Assertions.assertDoesNotThrow(()->userService.updateUser(userDto.getUserName() ,userDto));
    }

    @Test
    @DisplayName("deleteUser should throw exception if user not exist")
    public void deleteUserShouldThrowExceptionIfUserNotExist(){
        when(userRepository.findById(anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(UserNotFoundException.class ,()->userService.deleteUser(user.getUserName()));
    }

    @Test
    @DisplayName("deleteUser should delete user")
    public void deleteUserShouldDeleteUserIfExist(){
        when(userRepository.findById(anyString())).thenReturn(Optional.of(user));
        Assertions.assertDoesNotThrow(()->userService.deleteUser(user.getUserName()));
    }

}
