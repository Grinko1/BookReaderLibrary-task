package com.example.libraryApp.person.utils;

import com.example.libraryApp.person.PersonEntity;
import com.example.libraryApp.person.dto.PersonDto;
import org.springframework.stereotype.Service;

@Service
public class PersonMapper {
    public PersonDto mapPersonToPersonDto(PersonEntity person) {
        return PersonDto.builder()
                .id(person.getId())
                .fio(person.getFio())
                .yearOfBirth(person.getYearOfBirth())
                .build();

    }
}
