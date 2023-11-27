package com.example.controller;

import com.example.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.service.BookService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin("*")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/create")
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto bookDto) {
        try {
            BookDto saveBookDto = bookService.createBook(bookDto);
            return new ResponseEntity<>(saveBookDto,HttpStatus.CREATED);
        }catch (Exception e){
            System.err.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<List<BookDto>> getAllPagination(@PathVariable("page") int page,
                                                @RequestParam(required = false) String search) {
        try {
            List<BookDto> bookDtos = new ArrayList<>();
            if (search != null && !search.isEmpty()) {
                bookDtos = bookService.findBySearchCriteria(search, page , 5);
            }
            else {
                bookDtos = bookService.findAll(page, 5);
            }
            return new ResponseEntity<>(bookDtos, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<BookDto>> getAll() {
        try {
            List<BookDto> bookDtos = bookService.findAll();

            return new ResponseEntity<>(bookDtos, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") Long id) {
        try {
            BookDto bookDto = bookService.findById(id);
            return new ResponseEntity<>(bookDto,HttpStatus.OK);
        }catch (Exception e){
            System.err.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<BookDto> update(@Valid @RequestBody BookDto bookDto) {
        try {
            BookDto updateBookDto = bookService.update(bookDto);
            return new ResponseEntity<>(updateBookDto,HttpStatus.OK);
        }catch (Exception e){
            System.err.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
            bookService.deleteById(id);
            return new ResponseEntity<>("Deleted Success",HttpStatus.OK);
        }catch (Exception e){
            System.err.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

}
