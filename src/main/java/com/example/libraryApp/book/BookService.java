package com.example.libraryApp.book;

import com.example.libraryApp.book.dto.BookDto;
import com.example.libraryApp.person.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;
    private final PersonService personService;


    public List<BookEntity> getAllBooks(){
        return repository.findAll();
    }
    public List<BookEntity> getBooksByReaderId(Integer id){
        return repository.findByReaderId(id);
    }
    public BookEntity getById(Integer id){
        return repository.findById(id).orElseThrow(()-> new RuntimeException("Book with id " + id  + " doesn't exist"));
    }
    public BookEntity save(BookDto dto){
        BookEntity book = BookEntity.builder()
                .name(dto.getName())
                .author(dto.getAuthor())
                .year(dto.getYear())
                .reader(personService.getById(dto.getPerson_id()))
                .build();
        return repository.save(book);
    }
    public BookEntity update(BookEntity book){
        return repository.save(book);
    }
    public void delete(Integer id){
        repository.deleteById(id);
    }
}
