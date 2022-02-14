package com.epam.libraryservice.service.interfaces;

import com.epam.libraryservice.dto.LibraryDto;
import com.epam.libraryservice.exception.LimitExceededException;
import com.epam.libraryservice.exception.NotFoundException;

import java.util.List;

public interface LibraryService {

    void saveLibrary(String userName , int bookId) throws LimitExceededException;
    void deleteLibrary(String userName , int bookId) throws NotFoundException;
    List<LibraryDto> getAllLibraryByUserName(String userName);
    void deleteByBookId(int bookId);
}