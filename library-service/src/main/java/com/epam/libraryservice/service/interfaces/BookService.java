package com.epam.libraryservice.service.interfaces;


import com.epam.libraryservice.dto.BookDto;

import java.util.List;

public interface BookService {
    List<BookDto> getAllBooks();

    BookDto getBookById(int bookId) ;

    void saveBook(BookDto bookDto)  ;

    void updateBook(int bookId, BookDto bookDto)  ;

    void deleteBook(int bookId)  ;
}
