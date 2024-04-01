package com.example.libraryApp.book.utils;

import com.example.libraryApp.book.BookEntity;
import com.example.libraryApp.book.dto.BookInfoDto;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {

    public BookInfoDto mapBookToBookInfoDto(BookEntity book){
        return BookInfoDto.builder()
                .name(book.getName())
                .author(book.getAuthor())
                .year(book.getYear())
                .build();
    }
}
