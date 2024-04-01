package com.example.libraryApp.book;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    List<BookEntity> findByReaderId(Integer id);
    @Modifying
    @Transactional
    @Query("UPDATE BookEntity b SET b.reader = null WHERE b.reader.id = :personId")
    void updateAllByPersonId(@Param("personId") Integer personId);
}
