package com.epam.libraryservice.controller;

import com.epam.libraryservice.dto.UserDto;
import com.epam.libraryservice.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/library/users")
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/library/users/{userName}")
    public UserDto getUserByUserName(@PathVariable String userName)  {
        return userService.getUserByUserName(userName);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/library/users")
    public void saveUser(@RequestBody UserDto userDto)    {
        userService.saveUser(userDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/library/users/{userName}")
    public void updateUser(@PathVariable String userName ,@RequestBody UserDto userDto)    {
        userService.updateUser(userName,userDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/library/users/{userName}")
    public void deleteUser(@PathVariable String userName)    {
        userService.deleteUser(userName);
    }

}

