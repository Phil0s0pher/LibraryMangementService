package com.epam.libraryservice.service;

import com.epam.libraryservice.client.BookClient;
import com.epam.libraryservice.dto.BookDto;
import com.epam.libraryservice.service.interfaces.LibraryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {

    @InjectMocks
    BookServiceImpl bookService;
    @Mock
    LibraryService libraryService;
    @Mock
    BookClient bookClient;
    @Test
    @DisplayName("getAllBooks should return all Books")
    public void getAllBooksShouldReturnAllBooks(){
        BookDto bookDto = new BookDto();
        when(bookClient.getAllBooks()).thenReturn(List.of(bookDto));
        List<BookDto> allBooks = bookService.getAllBooks();
        Assertions.assertEquals(bookDto , allBooks.get(0));
    }

    @Test
    @DisplayName("getBook should return Book by Id")
    public void getBookShouldReturnBookById() {
        BookDto bookDto = new BookDto();
        when(bookClient.getBookById(anyInt())).thenReturn(bookDto);
        BookDto book = bookService.getBookById(1);
        Assertions.assertEquals(bookDto , book);
    }

    @Test
    @DisplayName("saveBook should save Book")
    public void saveBookShouldSaveBook(){
        BookDto bookDto = new BookDto();
        Assertions.assertDoesNotThrow(()-> bookService.saveBook(bookDto));


    }

    @Test
    @DisplayName("updateBook should update Book")
    public void updateBookShouldUpdateBookIfExist(){
        BookDto bookDto = new BookDto();
        Assertions.assertDoesNotThrow(()-> bookService.updateBook(1,bookDto));
    }

    @Test
    @DisplayName("deleteBook should delete Book")
    public void deleteBookShouldDeleteBookIfExist(){
        Assertions.assertDoesNotThrow(()-> bookService.deleteBook(1));
    }
}
