package com.example.libraryApp.person;

import com.example.libraryApp.book.BookEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "person")
@Data
@NoArgsConstructor
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String fio;
    @Column(name = "year_of_birth")
    private int yearOfBirth;
    @OneToMany(mappedBy = "reader")
    List<BookEntity> books;
}
