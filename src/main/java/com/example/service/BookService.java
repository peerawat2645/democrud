package com.example.service;

import com.example.dto.BookDto;
import com.example.model.Book;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {
    BookDto createBook(BookDto bookDto);

    List<BookDto> findAll();

    List<BookDto> findAll(int page, int value);

    List<BookDto> findBySearchCriteria(String search, int page, int size);

    BookDto findById(long id);

    BookDto update (BookDto bookDto);

    void deleteById(long id);
}
