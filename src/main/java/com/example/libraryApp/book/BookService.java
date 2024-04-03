package com.example.libraryApp.book;

import com.example.libraryApp.book.dto.BookWithReaderDtoRequest;
import com.example.libraryApp.book.dto.BookWithReaderDtoResponse;
import com.example.libraryApp.book.utils.BookMapper;
import com.example.libraryApp.exceptions.NotFoundException;
import com.example.libraryApp.person.PersonEntity;
import com.example.libraryApp.person.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;
    private final PersonService personService;
    private final BookMapper bookMapper;


    public List<BookWithReaderDtoResponse> getAllBooks() {
        return repository.findAll().stream().map(bookMapper::mapBookToBookWithReaderDtoResponse).collect(Collectors.toList());
    }

    public List<BookEntity> getBooksByReaderId(Integer id) {
        return repository.findByReaderId(id);
    }

    public BookWithReaderDtoResponse getById(Integer id) {
        BookEntity book = repository.findById(id).orElseThrow(() -> new NotFoundException("Book", "bookId", id));
        ;
        return bookMapper.mapBookToBookWithReaderDtoResponse(book);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }


    public BookWithReaderDtoResponse saveOrUpdate(BookWithReaderDtoRequest dto) {
        PersonEntity reader = null;
        if (dto.getReader_id() != null) {
            reader = personService.getById(dto.getReader_id());
        }
        BookEntity book = BookEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .author(dto.getAuthor())
                .year(dto.getYear())
                .reader(reader)
                .build();
        return bookMapper.mapBookToBookWithReaderDtoResponse(repository.save(book));
    }
}
