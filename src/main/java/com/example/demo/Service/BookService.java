package com.example.demo.Service;

import com.example.demo.Models.Dto.BookDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    List<BookDto> getAllBooks();

    BookDto getBookById(Long id);

    BookDto addBook(BookDto bookDto);

}
