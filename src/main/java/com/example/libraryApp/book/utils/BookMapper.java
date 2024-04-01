package com.example.libraryApp.book.utils;

import com.example.libraryApp.book.BookEntity;
import com.example.libraryApp.book.dto.BookInfoDto;
import com.example.libraryApp.book.dto.BookWithReaderDtoResponse;
import com.example.libraryApp.person.dto.PersonInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookMapper {

    public BookInfoDto mapBookToBookInfoDto(BookEntity book) {
        return BookInfoDto.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .year(book.getYear())
                .build();
    }

    public BookWithReaderDtoResponse mapBookToBookWithReaderDtoResponse(BookEntity book) {
        PersonInfoDto person = null;
        if (book.getReader() != null) {
           person = PersonInfoDto.builder()
                    .id(book.getReader().getId())
                    .fio(book.getReader().getFio())
                    .yearOfBirth(book.getReader().getYearOfBirth())
                    .build();;
        }
        return BookWithReaderDtoResponse.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .year(book.getYear())
                .reader(person)
                .build();
    }
}
