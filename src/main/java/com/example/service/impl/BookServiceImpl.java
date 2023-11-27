package com.example.service.impl;

import com.example.dto.BookDto;
import com.example.model.Book;
import com.example.mapper.BookMapper;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.example.repository.BookRepository;
import com.example.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        Book book = BookMapper.mapToBook(bookDto);
        Book savedBook = bookRepository.save(book);
        return BookMapper.mapToBookDto(savedBook);
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(BookMapper::mapToBookDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> findAll(int page, int value) {
        Pageable pageable = PageRequest.of(page-1, value);
        Page<Book> bookPage = bookRepository.findAll(pageable);

        return bookPage.getContent()
                .stream()
                .map(BookMapper::mapToBookDto)
                .collect(Collectors.toList());
    }

    public List<BookDto> findBySearchCriteria(String search, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        Specification<Book> book = (root, query, criteriaBuilder) -> {
            String likeSearch = "%" + search + "%";

            Predicate forName = criteriaBuilder.like(root.get("name"), likeSearch);
            Predicate forDescription = criteriaBuilder.like(root.get("description"), likeSearch);

            return criteriaBuilder.or(forName, forDescription);
        };

        Page<Book> bookPage = bookRepository.findAll(book, pageable);

        return bookPage.getContent()
                .stream()
                .map(BookMapper::mapToBookDto)
                .collect(Collectors.toList());
    }


    @Override
    public BookDto findById(long id) {
        Book book = bookRepository.findById(id).orElse(null);
        assert book != null;
        return BookMapper.mapToBookDto(book);
    }

    @Override
    public BookDto update(BookDto bookDto) {
        Book book = BookMapper.mapToBook(bookDto);
        Book updateBook = bookRepository.save(book);
        return BookMapper.mapToBookDto(updateBook);
    }

    @Override
    public void deleteById(long id) {
        if(this.findById(id) != null) {
            bookRepository.deleteById(id);
        }
    }
}
