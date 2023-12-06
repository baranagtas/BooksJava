package com.example.demo.Service;

import com.example.demo.Models.Dto.BookDto;

import java.util.List;

public interface BookService {

    List<BookDto> getAllBooks();

    BookDto getBookById(Long id);

    BookDto addBook(BookDto bookDto);

}
