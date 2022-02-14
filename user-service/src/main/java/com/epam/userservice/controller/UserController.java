package com.epam.userservice.controller;

import com.epam.userservice.dto.UserDto;
import com.epam.userservice.exception.DuplicateUserException;
import com.epam.userservice.exception.UserNotFoundException;
import com.epam.userservice.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    private static final String USER_ADDED="User added successfully!!";
    private static final String USER_DELETED= "User deleted successfully!!";
    private static final String USER_UPDATED="User updated successfully!!";

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userName}")
    public UserDto getUserByUserName(@PathVariable String userName) throws UserNotFoundException {
        return userService.getUserByUserName(userName);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users")
    public ResponseEntity<Object> saveUser(@RequestBody UserDto userDto) throws DuplicateUserException {
        userService.saveUser(userDto);
        return new ResponseEntity<>(USER_ADDED, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{userName}")
    public ResponseEntity<Object> updateUser(@PathVariable String userName ,@RequestBody UserDto userDto) throws  UserNotFoundException {
        userService.updateUser(userName,userDto);
        return new ResponseEntity<>(USER_UPDATED, HttpStatus.ACCEPTED);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{userName}")
    public ResponseEntity<Object> deleteUser(@PathVariable String userName) throws  UserNotFoundException {
        userService.deleteUser(userName);
        return new ResponseEntity<>(USER_DELETED, HttpStatus.ACCEPTED);
    }
}
