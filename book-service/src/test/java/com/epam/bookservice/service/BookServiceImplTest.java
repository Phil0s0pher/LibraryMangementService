package com.epam.bookservice.service;

import com.epam.bookservice.dto.BookDto;
import com.epam.bookservice.entity.Book;
import com.epam.bookservice.exception.BookNotFoundException;
import com.epam.bookservice.exception.DuplicateBookException;
import com.epam.bookservice.repository.BookRepository;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {
    @InjectMocks
    BookServiceImpl bookService;
    @Mock
    BookRepository bookRepository;
    @Mock
    ModelMapper modelMapper;
    BookDto bookDto;
    Book book;

    @Before
    public void setUp(){
        bookDto = new BookDto(1,"","","");
        book = new Book(1,"","","");
        when(modelMapper.map(bookDto , Book.class)).thenReturn(book);
        when(modelMapper.map(book , BookDto.class)).thenReturn(bookDto);
    }

    @Test
    @DisplayName("getAllBooks should return all Books")
    public void getAllBooksShouldReturnAllBooks(){
        when(bookRepository.findAll()).thenReturn(List.of(book,book));
        Assertions.assertEquals(List.of(bookDto , bookDto) , bookService.getAllBooks());
    }

    @Test
    @DisplayName("getBook should return Book by Id")
    public void getBookShouldReturnBookById() throws BookNotFoundException {
        when(bookRepository.findById(anyInt())).thenReturn(java.util.Optional.of(book));
        Assertions.assertEquals(bookDto, bookService.getBookById(1));
    }

    @Test
    @DisplayName("getBook should throw exception if Book not exist")
    public void getBookShouldThrowExceptionIfBookNotExist(){
        when(bookRepository.findById(anyInt())).thenReturn(java.util.Optional.empty());
        Assertions.assertThrows(BookNotFoundException.class , ()->bookService.getBookById(1));
    }

    @Test
    @DisplayName("saveBook should save Book")
    public void saveBookShouldSaveBook(){
        Assertions.assertDoesNotThrow(()->bookService.saveBook(bookDto));
    }

    @Test
    @DisplayName("saveBook should throw exception if Book already exist")
    public void saveBookShouldThrowExceptionIfBookAlreadyExist(){
        when(bookRepository.existsById(anyInt())).thenReturn(true);
        Assertions.assertThrows(DuplicateBookException.class ,()->bookService.saveBook(bookDto));
    }

    @Test
    @DisplayName("updateBook should throw exception if Book not exist")
    public void updateBookShouldThrowExceptionIfBookNotExist(){
        when(bookRepository.findById(anyInt())).thenReturn(Optional.empty());
        Assertions.assertThrows(BookNotFoundException.class ,()->bookService.updateBook(bookDto.getId() ,bookDto));
    }

    @Test
    @DisplayName("updateBook should update Book")
    public void updateBookShouldUpdateBookIfExist(){
        when(bookRepository.findById(anyInt())).thenReturn(Optional.of(book));
        Assertions.assertDoesNotThrow(()->bookService.updateBook(bookDto.getId() ,bookDto));
    }

    @Test
    @DisplayName("deleteBook should throw exception if Book not exist")
    public void deleteBookShouldThrowExceptionIfBookNotExist(){
        when(bookRepository.findById(anyInt())).thenReturn(Optional.empty());
        Assertions.assertThrows(BookNotFoundException.class ,()->bookService.deleteBook(book.getId()));
    }

    @Test
    @DisplayName("deleteBook should delete Book")
    public void deleteBookShouldDeleteBookIfExist(){
        when(bookRepository.findById(anyInt())).thenReturn(Optional.of(book));
        Assertions.assertDoesNotThrow(()->bookService.deleteBook(book.getId()));
    }
}
