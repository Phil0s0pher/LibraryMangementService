package com.epam.libraryservice.controller;

import com.epam.libraryservice.dto.UserDto;
import com.epam.libraryservice.service.interfaces.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
public class UserControllerTest {
    @MockBean
    UserService userService;
    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("getAllUsers should return all user with ok status")
    public void getAllUserShouldReturnAllUsersWithOkStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/library/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("getUser should return user by userName with Ok Status")
    public void getUserShouldReturnUserByUserNameWithOkStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/library/users/aditya")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("saveUser should save user with ok status")
    public void saveUserShouldSaveUserWithOkStatus() throws Exception {
        UserDto user = new UserDto();
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/library/users")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("updateUser should update user and give ok status")
    public void updateUserShouldUpdateUserIfExistAndGiveOkStatus() throws Exception {
        UserDto user = new UserDto();
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/library/users/aditya")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("deleteUser should delete user with ok status")
    public void deleteUserShouldDeleteUserIfExistWithOkStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/library/users/aditya")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
