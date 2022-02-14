package com.epam.userservice.controller;

import com.epam.userservice.dto.UserDto;
import com.epam.userservice.exception.DuplicateUserException;
import com.epam.userservice.exception.UserNotFoundException;
import com.epam.userservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
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
                        .get("/users/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("getUser should return user by userName with Ok Status")
    public void getUserShouldReturnUserByUserNameWithOkStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/aditya")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("getUserByUserName should not found status if user not exist")
    public void getUserByUserNameShouldReturnNotFoundStatusIfUserNotExist() throws Exception {
        doThrow(UserNotFoundException.class).when(userService).getUserByUserName(anyString());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/aditya")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("saveUser should save user with ok status")
    public void saveUserShouldSaveUserWithOkStatus() throws Exception {
        UserDto user = new UserDto("","","");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users/users")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("saveUser should conflict status if user already exist")
    public void saveUserShouldReturnConflictStatusIfUserAlreadyExist() throws Exception {
        doThrow(DuplicateUserException.class).when(userService).saveUser(any());
        UserDto user = new UserDto("","","");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users/users")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("updateUser should throw exception if user not exist")
    public void updateUserShouldGiveNotFoundStatusIfUserNotExist() throws Exception {
        doThrow(UserNotFoundException.class).when(userService).updateUser(anyString() ,any());
        UserDto user = new UserDto("","","");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/users/users/aditya")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("updateUser should update user and give ok status")
    public void updateUserShouldUpdateUserIfExistAndGiveOkStatus() throws Exception {
        UserDto user = new UserDto("","","");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/users/users/aditya")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("deleteUser should throw exception if user not exist")
    public void deleteUserShouldNotFoundStatusIfUserNotExist() throws Exception {
        doThrow(UserNotFoundException.class).when(userService).deleteUser(anyString());
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/users/users/aditya")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("deleteUser should delete user with ok status")
    public void deleteUserShouldDeleteUserIfExistWithOkStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/users/users/aditya")
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
