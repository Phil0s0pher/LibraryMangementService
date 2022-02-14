package com.epam.bookservice.repository;

import com.epam.bookservice.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book, Integer> {

    Book findBookById(int bookId);

    Book deleteBookById(int bookId);
}
