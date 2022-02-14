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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {
    @Autowired
    LibraryRepository libraryRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;

    @Override
    public void saveLibrary(String userName, int bookId) throws LimitExceededException {
        if(libraryRepository.countByUserName(userName)>3){
            throw new LimitExceededException("Your Library Limit is exceeded");
        }
        BookDto bookDto = bookService.getBookById(bookId);
        UserDto userDto = userService.getUserByUserName(userName);
        Library library = new Library(userDto.getUserName() , bookDto.getBookId());
        libraryRepository.save(library);
    }

    @Override
    public void deleteLibrary(String userName, int bookId) throws NotFoundException {
        List<Library> libraries = libraryRepository.findByUserNameAndBookId(userName , bookId);
        if(libraries.isEmpty()){
            throw new NotFoundException("Book id for this username not found");
        }
        libraryRepository.delete(libraries.get(0));
    }

    @Override
    public List<LibraryDto> getAllLibraryByUserName(String userName) {
        List<Library> libraries = libraryRepository.findByUserName(userName);
        List<LibraryDto> libraryDto = new ArrayList<>();
        libraries.forEach(library -> libraryDto.add(modelMapper.map(library , LibraryDto.class)));
        return libraryDto;
    }

    @Override
    public void deleteByBookId(int bookId) {
        List<Library> libraries = libraryRepository.findByBookId(bookId);
        libraries.forEach(library -> libraryRepository.delete(library));
    }
}
