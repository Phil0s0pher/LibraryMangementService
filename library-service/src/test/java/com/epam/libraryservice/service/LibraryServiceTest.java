package com.epam.libraryservice.service;

import com.epam.libraryservice.dto.BookDto;
import com.epam.libraryservice.dto.LibraryDto;
import com.epam.libraryservice.dto.UserDto;
import com.epam.libraryservice.entity.Library;
import com.epam.libraryservice.exception.LimitExceededException;
import com.epam.libraryservice.exception.NotFoundException;
import com.epam.libraryservice.repository.LibraryRepository;
import com.epam.libraryservice.service.interfaces.BookService;
import com.epam.libraryservice.service.interfaces.LibraryService;
import com.epam.libraryservice.service.interfaces.UserService;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
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

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LibraryServiceTest {

    @InjectMocks
    LibraryServiceImpl libraryService;
    @Mock
    UserService userService;
    @Mock
    BookService bookService;

    @Mock
    LibraryRepository libraryRepository;

    @Mock
    ModelMapper modelMapper;
    Library library;
    LibraryDto libraryDto;

    @Before
    public void setUp(){
        library = new Library();
        libraryDto = new LibraryDto();
        when(modelMapper.map(library , LibraryDto.class)).thenReturn(libraryDto);
    }

    @Test
    @DisplayName("saveLibrary should save library")
    public void saveLibraryShouldSaveLibrary(){
        UserDto userDto = new UserDto("","","");
        BookDto bookDto = new BookDto();
        when(bookService.getBookById(anyInt())).thenReturn(bookDto);
        when(userService.getUserByUserName(anyString())).thenReturn(userDto);
        Assertions.assertDoesNotThrow(()->libraryService.saveLibrary("aditya" , 1));
    }

    @Test
    @DisplayName("saveLibrary should throw exception if limit exceeded")
    public void saveLibraryShouldThrowExceptionIfLimitExceeded(){
        when(libraryRepository.countByUserName(anyString())).thenReturn(10);
        Assertions.assertThrows(LimitExceededException.class ,()->libraryService.saveLibrary("aditya" , 1));
    }

    @Test
    @DisplayName("deleteLibrary should throw exception if not found")
    public void deleteLibraryShouldThrowExceptionIfNotFound(){
        when(libraryRepository.findByUserNameAndBookId(anyString() , anyInt())).thenReturn(List.of());
        Assertions.assertThrows(NotFoundException.class ,()->libraryService.deleteLibrary("aditya" , 1));
    }

    @Test
    @DisplayName("deleteLibrary should delete")
    public void deleteLibraryShouldDelete(){
        when(libraryRepository.findByUserNameAndBookId(anyString() , anyInt())).thenReturn(List.of(library));
        Assertions.assertDoesNotThrow(()->libraryService.deleteLibrary("aditya" , 1));
    }

    @Test
    @DisplayName("getAllLibraryByUserName should give libraries by username")
    public void getAllLibraryByUserNameShouldFiveLibrariesByUserName(){
        when(libraryRepository.findByUserName(anyString() )).thenReturn(List.of(library));
        Assertions.assertDoesNotThrow(()->libraryService.getAllLibraryByUserName("aditya" ));
    }
    @Test
    @DisplayName("deleteByBookId should delete by book id")
    public void deleteByBookIdShouldDeleteByBookId(){
        Assertions.assertDoesNotThrow(()->libraryService.deleteByBookId(1));
    }
}
