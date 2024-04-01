package com.example.libraryApp.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {
    @Query("SELECT DISTINCT p FROM PersonEntity p LEFT JOIN FETCH p.books ORDER BY p.id ASC")
    List<PersonEntity> findAllWithBooks();
    @Query("SELECT DISTINCT p FROM PersonEntity p LEFT JOIN FETCH p.books WHERE p.id = :id")
    Optional<PersonEntity> findByIdWithBooks( Integer id);
}
