package com.example.libraryApp.person;

import com.example.libraryApp.book.BookRepository;
import com.example.libraryApp.exceptions.NotFoundException;
import com.example.libraryApp.person.dto.PersonWithBooksDto;
import com.example.libraryApp.person.utils.PersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final BookRepository bookRepository;
    private final PersonMapper personMapper;


    public List<PersonWithBooksDto> getPeople(){
//        return personRepository.findAllWithBooks().stream().map(personMapper::mapPersonToPersonDto).collect(Collectors.toList());
        return personRepository.findAll().stream().map(personMapper::mapPersonToPersonDto).collect(Collectors.toList());
    }
    public PersonEntity getById(Integer id){
       return personRepository.findByIdWithBooks(id).orElseThrow(()-> new NotFoundException("Person: " , "personId" , id));

    }
    public PersonWithBooksDto saveOrUpdate(PersonEntity person){
        return personMapper.mapPersonToPersonDto(personRepository.save(person));
    }
    public void deleteById(Integer id){
        bookRepository.updateAllByPersonId(id);
        personRepository.deleteById(id);
    }
}
