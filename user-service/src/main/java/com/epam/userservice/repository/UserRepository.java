package com.epam.userservice.repository;

import com.epam.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, String> {
    User findUserByUserName(String userName);

    User deleteUserByUserName(String userName);
}
