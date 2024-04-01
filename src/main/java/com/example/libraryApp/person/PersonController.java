package com.example.libraryApp.person;

import com.example.libraryApp.book.BookService;
import com.example.libraryApp.book.dto.BookInfoDto;
import com.example.libraryApp.book.utils.BookMapper;
import com.example.libraryApp.person.dto.PersonDto;
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
    private final BookService bookService;
    private final BookMapper bookMapper;
    private final PersonMapper personMapper;

    @GetMapping
    public ResponseEntity<List<PersonDto>> getAll(){
        List<PersonDto> people = personService.getPeople().stream().map(personMapper::mapPersonToPersonDto).collect(Collectors.toList());
        return new ResponseEntity<>(people, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getById(@PathVariable("id") Integer id){
        List<BookInfoDto> books = bookService.getBooksByReaderId(id).stream().map(bookMapper::mapBookToBookInfoDto).collect(Collectors.toList());
        PersonDto personDto = personMapper.mapPersonToPersonDto(personService.getById(id));
        personDto.setBooks(books);
        System.out.println(books);
        return new ResponseEntity<>(personDto, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<PersonDto> save(@RequestBody PersonEntity person){
        return new ResponseEntity<>(personMapper.mapPersonToPersonDto(personService.save(person)), HttpStatus.CREATED);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<PersonDto> update(@PathVariable("id") Integer id, @RequestBody PersonEntity person){
        person.setId(id);
        return new ResponseEntity<>(personMapper.mapPersonToPersonDto(personService.update(person)), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public HttpStatus deleteById(@PathVariable("id") Integer id){
        personService.deleteById(id);
        return HttpStatus.OK;
    }
}
