package com.epam.bookservice.service;

import com.epam.bookservice.dto.BookDto;
import com.epam.bookservice.entity.Book;
import com.epam.bookservice.exception.BookNotFoundException;
import com.epam.bookservice.exception.DuplicateBookException;
import com.epam.bookservice.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> allBooks = bookRepository.findAll();
        List<BookDto> allBooksDto = new ArrayList<>();
        allBooks.forEach(book -> allBooksDto.add(modelMapper.map(book,BookDto.class)));
        return allBooksDto;
    }

    @Override
    public BookDto getBookById(int bookId) throws BookNotFoundException {
        Optional<Book> book = bookRepository.findById(bookId);
        if(book.isEmpty()){
            throw new BookNotFoundException("Book with this Id Not Found");
        }
        return modelMapper.map(book.get() , BookDto.class);
    }

    @Override
    public void saveBook(BookDto bookDto) throws DuplicateBookException {
        Book book = modelMapper.map(bookDto , Book.class);
        if(bookRepository.existsById(book.getId())){
            throw new DuplicateBookException("Book Already Exist");
        }
        bookRepository.save(book);
    }

    @Override
    public void updateBook(int bookId, BookDto bookDto) throws BookNotFoundException {
        BookDto bookDto1 = getBookById(bookId);
        bookDto1.setBookName(bookDto.getBookName());
        bookDto1.setAuthorName(bookDto.getAuthorName());
        bookDto1.setPublisherName(bookDto.getPublisherName());
        Book book = modelMapper.map(bookDto1 , Book.class);
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(int bookId) throws BookNotFoundException {
        BookDto bookDto = getBookById(bookId);
        Book book = modelMapper.map(bookDto , Book.class);
        bookRepository.delete(book);
    }

}
