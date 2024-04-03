package com.example.libraryApp.book;

import com.example.libraryApp.book.dto.BookWithReaderDtoRequest;
import com.example.libraryApp.book.dto.BookWithReaderDtoResponse;
import com.example.libraryApp.exceptions.NotFoundException;
import com.example.libraryApp.person.PersonEntity;
import com.example.libraryApp.person.PersonService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;
    private final PersonService personService;
    private final ModelMapper modelMapper;


    public List<BookWithReaderDtoResponse> getAllBooks() {
        return repository.findAll().stream().map((book) -> modelMapper.map(book, BookWithReaderDtoResponse.class)).collect(Collectors.toList());
    }

    public List<BookEntity> getBooksByReaderId(Integer id) {
        return repository.findByReaderId(id);
    }

    public BookWithReaderDtoResponse getById(Integer id) {
        BookEntity book = repository.findById(id).orElseThrow(() -> new NotFoundException("Book", "bookId", id));
        return modelMapper.map(book, BookWithReaderDtoResponse.class);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }


    public BookWithReaderDtoResponse saveOrUpdate(BookWithReaderDtoRequest dto) {
        PersonEntity reader = null;
        if (dto.getReader_id() != null) {
            reader = modelMapper.map(personService.getById(dto.getReader_id()), PersonEntity.class);
        }
        BookEntity book = modelMapper.map(dto, BookEntity.class);
        book.setReader(reader);

        return modelMapper.map(repository.save(book), BookWithReaderDtoResponse.class);

    }
}
