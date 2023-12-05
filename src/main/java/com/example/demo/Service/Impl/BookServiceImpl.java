package com.example.demo.Service.Impl;

import com.example.demo.Models.Book;
import com.example.demo.Models.Dto.BookDto;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Service.BookService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    @Override
    public List<BookDto> getAllBooks() {

        List<Book> books=bookRepository.findAll();
        return books.stream()
                .map(this::mapBookToBookDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto getBookById(Long id) {
        Optional<Book> optionalBook=bookRepository.findById(id);
        return optionalBook.map(this::mapBookToBookDTO).orElse(null);
    }

    @Override
    public BookDto addBook(BookDto bookDto) {
        Book book =mapBookDTOToBook(bookDto);
        return mapBookToBookDTO(bookRepository.save(book));
    }



    private BookDto mapBookToBookDTO(Book book){

        BookDto bookDto=new BookDto();

        bookDto.setId(book.getId());
        bookDto.setName(book.getName());
        bookDto.setAuthor(book.getAuthor());

        return bookDto;

    }

    private Book mapBookDTOToBook(BookDto bookDto){

        Book book=new Book();

        book.setId(bookDto.getId());
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());

        return book;

    }

}
