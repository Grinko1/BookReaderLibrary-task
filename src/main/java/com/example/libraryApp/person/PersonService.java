package com.example.libraryApp.person;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository repository;


    public List<PersonEntity> getPeople(){
        return repository.findAllWithBooks();
    }
    public PersonEntity getById(Integer id){
        return repository.findByIdWithBooks(id).orElseThrow(()-> new RuntimeException("Person with id: " + id + " doesn't found"));
    }
    public PersonEntity save(PersonEntity person){
        return repository.save(person);
    }
    public PersonEntity update(PersonEntity person){
        return repository.save(person);
    }
    public void deleteById(Integer id){
        repository.deleteById(id);
    }
}
