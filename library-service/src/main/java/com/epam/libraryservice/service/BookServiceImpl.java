package com.epam.libraryservice.service;

import com.epam.libraryservice.client.BookClient;
//import com.epam.libraryservice.controller.BookController;
import com.epam.libraryservice.dto.BookDto;
import com.epam.libraryservice.service.interfaces.BookService;
import com.epam.libraryservice.service.interfaces.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    LibraryService libraryService;
    @Autowired
    BookClient bookClient;

    @Override
    public List<BookDto> getAllBooks() {
        return bookClient.getAllBooks();
    }

    @Override
    public BookDto getBookById(int bookId) {
        return bookClient.getBookById(bookId);
    }

    @Override
    public void saveBook(BookDto bookDto) {
        bookClient.saveBook(bookDto);

    }

    @Override
    public void updateBook(int bookId, BookDto bookDto) {
        bookClient.updateBook(bookId,bookDto);

    }

    @Override
    public void deleteBook(int bookId) {
        libraryService.deleteByBookId(bookId);
        bookClient.deleteBook(bookId);
    }
}

