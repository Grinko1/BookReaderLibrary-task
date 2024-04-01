package com.example.libraryApp.person.dto;


import com.example.libraryApp.book.dto.BookInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonWithBooksDto {
    private Integer id;
    private String fio;
    private int yearOfBirth;
    private List<BookInfoDto> books;

}

