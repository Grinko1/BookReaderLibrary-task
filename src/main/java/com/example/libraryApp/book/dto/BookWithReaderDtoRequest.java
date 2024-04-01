package com.example.libraryApp.book.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookWithReaderDtoRequest {
    private Integer id;
    private String name;
    private String author;
    private int year;
    private Integer reader_id;
}
