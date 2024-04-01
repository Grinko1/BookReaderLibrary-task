package com.example.libraryApp.book;

import com.example.libraryApp.book.dto.BookWithReaderDtoRequest;
import com.example.libraryApp.book.dto.BookWithReaderDtoResponse;
import com.example.libraryApp.book.utils.BookMapper;
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
    private final BookMapper bookMapper;

    @GetMapping
    public ResponseEntity<List<BookWithReaderDtoResponse>> getAllBooks(){
        return new ResponseEntity<>(service.getAllBooks(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookWithReaderDtoResponse> getById(@PathVariable("id") Integer id){
      return responseEntityWrapperForBook(service.getById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<BookWithReaderDtoResponse> save(@RequestBody BookWithReaderDtoRequest book){
        return responseEntityWrapperForBook(service.save(book), HttpStatus.CREATED);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<BookWithReaderDtoResponse> update(@PathVariable("id") Integer id, @RequestBody BookWithReaderDtoRequest book){
        book.setId(id);
        return responseEntityWrapperForBook(service.update(book), HttpStatus.OK);
    }
    @DeleteMapping("/id")
    public HttpStatus deleteById(@PathVariable("id") Integer id){
        service.getById(id);
        return HttpStatus.OK;
    }
    public ResponseEntity<BookWithReaderDtoResponse> responseEntityWrapperForBook(BookWithReaderDtoResponse book, HttpStatus status){
        return new ResponseEntity<>(book, status);
    }

}
