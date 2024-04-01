package com.example.libraryApp.book;

import com.example.libraryApp.book.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService service;

    @GetMapping
    public ResponseEntity<List<BookEntity>> getAllBooks(){
        return new ResponseEntity<>(service.getAllBooks(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookEntity> getById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<BookEntity> save(@RequestBody BookDto book){
        return new ResponseEntity<>(service.save(book), HttpStatus.CREATED);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<BookEntity> update(@PathVariable("id") Integer id, @RequestBody BookEntity book){
        book.setId(id);
        return new ResponseEntity<>(service.update(book), HttpStatus.OK);
    }
    @DeleteMapping("/id")
    public HttpStatus deleteById(@PathVariable("id") Integer id){
        service.getById(id);
        return HttpStatus.OK;
    }

}
