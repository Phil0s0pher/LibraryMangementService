package com.epam.libraryservice.repository;

import com.epam.libraryservice.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface LibraryRepository extends JpaRepository<Library, Integer> {
    int countByUserName(String userName);

    List<Library> findByUserNameAndBookId(String userName, int bookId);

    List<Library> findByUserName(String userName);

    List<Library> findByBookId(int bookId);
}
