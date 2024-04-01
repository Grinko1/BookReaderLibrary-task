package com.example.libraryApp.person;

import com.example.libraryApp.person.dto.PersonWithBooksDto;
import com.example.libraryApp.person.utils.PersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/people")
public class PersonController {
    private final PersonService personService;
    private final PersonMapper personMapper;

    @GetMapping
    public ResponseEntity<List<PersonWithBooksDto>> getAll() {
        return new ResponseEntity<>(personService.getPeople(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonWithBooksDto> getById(@PathVariable("id") Integer id) {
        PersonWithBooksDto personDto = personMapper.mapPersonToPersonDto(personService.getById(id));
//        return new ResponseEntity<>(personDto, HttpStatus.OK);
        return responseEntityWrapperForPerson(personDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PersonWithBooksDto> save(@RequestBody PersonEntity person) {
//        return new ResponseEntity<>(personService.saveOrUpdate(person), HttpStatus.CREATED);
        return responseEntityWrapperForPerson(personService.saveOrUpdate(person), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PersonWithBooksDto> update(@PathVariable("id") Integer id, @RequestBody PersonEntity person) {
        person.setId(id);
//        return new ResponseEntity<>(personService.saveOrUpdate(person), HttpStatus.OK);
        return responseEntityWrapperForPerson(personService.saveOrUpdate(person), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteById(@PathVariable("id") Integer id) {
        personService.deleteById(id);
        return HttpStatus.OK;
    }

    private ResponseEntity<PersonWithBooksDto> responseEntityWrapperForPerson(PersonWithBooksDto person, HttpStatus status) {
        return new ResponseEntity<>(person, status);
    }
}
