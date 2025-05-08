package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
//    boolean existsByEmail(String email);
    //1 вариант SQL запроса
    @Query(value = "select * from users where email= :email", nativeQuery = true)
    Optional<User> findByUserEmail(String email);
    // 2 вариант SQL запроса
//    @Query(value = "select u from User u where u.email = :email")
//    User findByEmail(@Param(value = "email") String email);
    //3 вариант
//    User findByEmailAndAgeAfterAndBirthday(String email, int age, LocalDate birthday);
}
