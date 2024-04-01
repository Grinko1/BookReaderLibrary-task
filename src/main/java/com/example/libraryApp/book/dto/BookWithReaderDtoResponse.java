package com.example.libraryApp.book.dto;

import com.example.libraryApp.person.dto.PersonInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookWithReaderDtoResponse {
    private Integer id;
    private String name;
    private String author;
    private int year;
    private PersonInfoDto reader;
}
