package com.epam.libraryservice.controller;

import com.epam.libraryservice.dto.BookDto;
import com.epam.libraryservice.service.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/library/books")
    public List<BookDto> getAllBooks(){
        return bookService.getAllBooks();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/library/books/{bookId}")
    public BookDto getBookById(@PathVariable int bookId)  {
        return bookService.getBookById(bookId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/library/books")
    public void saveBook(@RequestBody BookDto bookDto)   {
        bookService.saveBook(bookDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/library/books/{bookId}")
    public void updateBook(@PathVariable int bookId ,@RequestBody BookDto bookDto)   {
        bookService.updateBook(bookId,bookDto);
    }
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/library/books/{bookId}")
    public void deleteBook(@PathVariable int bookId)   {
        bookService.deleteBook(bookId);
    }
}
