package com.epam.bookservice.controller;

import com.epam.bookservice.dto.BookDto;
import com.epam.bookservice.exception.BookNotFoundException;
import com.epam.bookservice.exception.DuplicateBookException;
import com.epam.bookservice.service.BookService;
import com.epam.bookservice.service.BookServiceImpl;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BookController.class)
public class BookControllerTest {
    @MockBean
    BookServiceImpl bookService;
    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("getAllBooks should return all Book with ok status")
    public void getAllBooksShouldReturnAllBooksWithOkStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/books/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("getBook should return Book by Id with Ok Status")
    public void getBookShouldReturnBookByIdWithOkStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("getBookByID should not found status if Book not exist")
    public void getBookByIdShouldReturnNotFoundStatusIfBookNotExist() throws Exception {
        doThrow(BookNotFoundException.class).when(bookService).getBookById(anyInt());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("saveBook should save Book with ok status")
    public void saveBookShouldSaveBookWithOkStatus() throws Exception {
        BookDto bookDto = new BookDto(1,"","","");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/books/books")
                        .content(asJsonString(bookDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("saveBook should conflict status if Book already exist")
    public void saveBookShouldReturnConflictStatusIfBookAlreadyExist() throws Exception {
        doThrow(DuplicateBookException.class).when(bookService).saveBook(any());
        BookDto bookDto = new BookDto(1,"","","");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/books/books/1")
                        .content(asJsonString(bookDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("updateBook should throw exception if Book not exist")
    public void updateBookShouldGiveNotFoundStatusIfBookNotExist() throws Exception {
        doThrow(BookNotFoundException.class).when(bookService).updateBook(anyInt() ,any());
        BookDto bookDto = new BookDto(1,"","","");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/books/books/1")
                        .content(asJsonString(bookDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("updateBook should update Book and give ok status")
    public void updateBookShouldUpdateBookIfExistAndGiveOkStatus() throws Exception {
        BookDto bookDto = new BookDto(1,"","","");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/books/1")
                        .content(asJsonString(bookDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("deleteBook should throw exception if Book not exist")
    public void deleteBookShouldNotFoundStatusIfBookNotExist() throws Exception {
        doThrow(BookNotFoundException.class).when(bookService).deleteBook(anyInt());
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("deleteBook should delete Book with ok status")
    public void deleteBookShouldDeleteBookIfExistWithOkStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/books/1")
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
