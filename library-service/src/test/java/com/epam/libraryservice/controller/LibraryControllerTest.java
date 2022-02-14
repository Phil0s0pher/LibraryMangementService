package com.epam.libraryservice.controller;

import com.epam.libraryservice.exception.LimitExceededException;
import com.epam.libraryservice.exception.NotFoundException;
import com.epam.libraryservice.service.interfaces.BookService;
import com.epam.libraryservice.service.interfaces.LibraryService;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = LibraryController.class)
public class LibraryControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    LibraryService libraryService;
    @MockBean
    UserService userService;
    @MockBean
    BookService bookService;

    @Test
    @DisplayName("saveLibrary should save Library with ok status")
    public void saveLibraryShouldSaveLibraryWithOkStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/library/users/aditya/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("saveLibrary should give error for limit exceeded with Conflict status")
    public void saveLibraryShouldGiveErrorForLimitExceededWithConflictStatus() throws Exception {
        doThrow(LimitExceededException.class).when(libraryService).saveLibrary(anyString() , anyInt());
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/library/users/aditya/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("deleteLibrary should delete Library with ok status")
    public void deleteLibraryShouldDeleteLibraryWithOkStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/library/users/spiderman/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("deleteLibrary should give error if not exist with not found status")
    public void deleteLibraryShouldGiveErrorIfNotExistWithNotFoundStatus() throws Exception {
        doThrow(NotFoundException.class).when(libraryService).deleteLibrary(anyString() , anyInt());
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/library/users/spiderman/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
