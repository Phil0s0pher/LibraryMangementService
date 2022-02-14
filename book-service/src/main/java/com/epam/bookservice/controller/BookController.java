package com.epam.bookservice.controller;

import com.epam.bookservice.dto.BookDto;
import com.epam.bookservice.entity.Book;
import com.epam.bookservice.exception.BookNotFoundException;
import com.epam.bookservice.exception.DuplicateBookException;
import com.epam.bookservice.service.BookServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {
    @Autowired
    private BookServiceImpl bookService;

    private static final String BOOK_ADDED= "Book added successfully!!";
    private static final String BOOK_UPDATED= "Book updated successfully!!";
    private static final String BOOK_DELETED= "Book deleted successfully!!";


    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getAllBooks(){
        log.info("Into the getAllBooks methods of Book Controller");
        List<BookDto> bookList= bookService.getAllBooks();
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> addBook(@RequestBody BookDto bookDto) throws DuplicateBookException {
        log.info("Into the save book method of Book controller");
        bookService.saveBook(bookDto);
        return new ResponseEntity<>(BOOK_ADDED, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> findBookById(@PathVariable("id") int bookId) throws BookNotFoundException {
        BookDto fetchedBook= bookService.getBookById(bookId);
        return new ResponseEntity<>(fetchedBook, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> deleteBookById(@PathVariable("id") int bookId) throws BookNotFoundException {
        bookService.deleteBook(bookId);
        return new ResponseEntity<>(BOOK_DELETED, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> updateBook(@PathVariable("id") int bookId, @RequestBody BookDto updateBook) throws BookNotFoundException {
        bookService.updateBook(bookId, updateBook);
        return new ResponseEntity<>(BOOK_UPDATED,HttpStatus.ACCEPTED);
    }
}
