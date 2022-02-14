package com.epam.libraryservice.client;

import com.epam.libraryservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/users")
    List<UserDto> getAllUsers();

    @GetMapping("/users/{userName}")
    UserDto getUserByUserName(@PathVariable String userName);

    @PostMapping("/users")
    void saveUser(@RequestBody UserDto userDto);

    @PutMapping("/users/{userName}")
    void updateUser(@PathVariable String userName ,@RequestBody UserDto userDto);

    @DeleteMapping("/users/{userName}")
    void deleteUser(@PathVariable String userName);
}
