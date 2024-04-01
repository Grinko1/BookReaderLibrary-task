package com.example.libraryApp.person.utils;


import com.example.libraryApp.book.dto.BookInfoDto;
import com.example.libraryApp.book.utils.BookMapper;
import com.example.libraryApp.person.PersonEntity;
import com.example.libraryApp.person.dto.PersonInfoDto;
import com.example.libraryApp.person.dto.PersonWithBooksDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonMapper {
    private final BookMapper bookMapper;

    public PersonWithBooksDto mapPersonToPersonDto(PersonEntity person) {
        List<BookInfoDto> books;
        if (person.getBooks() != null) {
            books = person.getBooks().stream().map(bookMapper::mapBookToBookInfoDto).collect(Collectors.toList());
        } else {
            books = null;
        }
        return PersonWithBooksDto.builder()
                .id(person.getId())
                .fio(person.getFio())
                .yearOfBirth(person.getYearOfBirth())
                .books(books)
                .build();

    }
    public PersonInfoDto mapPersonToPersonInfoDto(PersonEntity person){
        return PersonInfoDto.builder()
                .id(person.getId())
                .fio(person.getFio())
                .yearOfBirth(person.getYearOfBirth())
                .build();
    }
}
