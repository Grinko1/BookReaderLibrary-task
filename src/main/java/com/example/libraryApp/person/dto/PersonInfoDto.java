package com.example.libraryApp.person.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonInfoDto {
    private Integer id;
    private String fio;
    private int yearOfBirth;
}
