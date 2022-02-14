package com.epam.libraryservice.client;

import com.epam.libraryservice.dto.BookDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "book-service")
public interface BookClient {

    @GetMapping("/books/")
    List<BookDto> getAllBooks();

    @GetMapping("/books/{id}")
    BookDto getBookById(@PathVariable int id);

    @PostMapping("/books")
    void saveBook(@RequestBody BookDto bookDto);

    @PutMapping("/books/{id}")
    void updateBook(@PathVariable int id ,@RequestBody BookDto bookDto);

    @DeleteMapping("/books/{id}")
    void deleteBook(@PathVariable int id);
}
