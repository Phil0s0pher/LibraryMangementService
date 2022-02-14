package com.epam.libraryservice.controller;

import com.epam.libraryservice.dto.BookDto;
import com.epam.libraryservice.service.interfaces.BookService;
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
@WebMvcTest(value = BookController.class)
public class BookControllerTest {
    @MockBean
    BookService bookService;
    @MockBean
    UserService userService;
    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("getAllBooks should return all Book with ok status")
    public void getAllBooksShouldReturnAllBooksWithOkStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/library/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("getBook should return Book by Id with Ok Status")
    public void getBookShouldReturnBookByIdWithOkStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/library/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("saveBook should save Book with ok status")
    public void saveBookShouldSaveBookWithOkStatus() throws Exception {
        BookDto bookDto = new BookDto(1,"","","");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/library/books")
                        .content(asJsonString(bookDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("updateBook should update Book and give ok status")
    public void updateBookShouldUpdateBookIfExistAndGiveOkStatus() throws Exception {
        BookDto bookDto = new BookDto(1,"","","");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/library/books/1")
                        .content(asJsonString(bookDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("deleteBook should delete Book with ok status")
    public void deleteBookShouldDeleteBookIfExistWithOkStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/library/books/1")
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
