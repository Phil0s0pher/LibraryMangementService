package com.epam.libraryservice.controller;


import com.epam.libraryservice.exception.LimitExceededException;
import com.epam.libraryservice.exception.NotFoundException;
import com.epam.libraryservice.service.interfaces.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class LibraryController {

    @Autowired
    LibraryService libraryService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/library/users/{userName}/books/{bookId}")
    public void saveLibrary(@PathVariable String userName , @PathVariable int bookId) throws LimitExceededException {
        libraryService.saveLibrary(userName,bookId);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/library/users/{userName}/books/{bookId}")
    public void deleteLibrary(@PathVariable String userName , @PathVariable int bookId) throws NotFoundException {
        libraryService.deleteLibrary(userName,bookId);
    }

}
