package com.example.mapper;

import com.example.dto.BookDto;
import com.example.model.Book;

public class BookMapper {

    public static BookDto mapToBookDto(Book book){
        return new BookDto(book.getId(), book.getName(), book.getDescription());
    }

    public static Book mapToBook(BookDto bookDto){
        return new Book(bookDto.getId(), bookDto.getName(), bookDto.getDescription());
    }
}
