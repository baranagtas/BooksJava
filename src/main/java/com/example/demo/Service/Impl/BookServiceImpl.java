package com.example.demo.Service.Impl;

import com.example.demo.Models.Book;
import com.example.demo.Models.Dto.BookDto;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Repository.CustomerRepository;
import com.example.demo.Service.BookService;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
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
        if (optionalBook.isPresent()){
            return mapBookToBookDTO(optionalBook.get());
        }
        else {
            throw new NotFoundException("Book not found with id: " + id);
        }
    }

    @Override
    public BookDto addBook(BookDto bookDto) {
        if (bookDto.getId() != null){
            throw new BadRequestException("New book should not have an id.");
        }
        Book book=mapBookDTOToBook(bookDto);
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
