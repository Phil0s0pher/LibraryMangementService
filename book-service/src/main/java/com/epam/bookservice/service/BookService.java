package com.epam.bookservice.service;

import com.epam.bookservice.dto.BookDto;
import com.epam.bookservice.exception.BookNotFoundException;
import com.epam.bookservice.exception.DuplicateBookException;

import java.util.List;

public interface BookService{
        List<BookDto> getAllBooks();

        BookDto getBookById(int bookId) throws BookNotFoundException;

        void saveBook(BookDto bookDto) throws DuplicateBookException;

        void updateBook(int bookId, BookDto bookDto) throws BookNotFoundException;

        void deleteBook(int bookId) throws BookNotFoundException;
}
