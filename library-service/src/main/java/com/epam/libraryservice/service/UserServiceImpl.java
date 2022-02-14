package com.epam.libraryservice.service;

import com.epam.libraryservice.client.UserClient;
import com.epam.libraryservice.dto.BookDto;
import com.epam.libraryservice.dto.LibraryDto;
import com.epam.libraryservice.dto.UserDto;
import com.epam.libraryservice.service.interfaces.BookService;
import com.epam.libraryservice.service.interfaces.LibraryService;
import com.epam.libraryservice.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    LibraryService libraryService;
    @Autowired
    BookService bookService;
    @Autowired
    UserClient userClient;

    @Override
    public List<UserDto> getAllUsers() {
        return userClient.getAllUsers();
    }
    @Override
    public UserDto getUserByUserName(String userName) {
        UserDto userDto = userClient.getUserByUserName(userName);
        List<LibraryDto> libraries = libraryService.getAllLibraryByUserName(userName);
        List<BookDto> allBooks = new ArrayList<>();
        libraries.forEach(libraryDto -> allBooks.add(bookService.getBookById(libraryDto.getBookId())));
        userDto.setBooks(allBooks);
        return userDto;
    }

    @Override
    public void saveUser(UserDto userDto) {
        userClient.saveUser(userDto);
    }

    @Override
    public void updateUser(String userName, UserDto userDto) {
        userClient.updateUser(userName , userDto);
    }

    @Override
    public void deleteUser(String userName) {
        userClient.deleteUser(userName);
    }


}
