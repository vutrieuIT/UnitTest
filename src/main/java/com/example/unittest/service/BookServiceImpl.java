package com.example.unittest.service;

import com.example.unittest.dto.Book;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    @Override
    public Book getBook(Long id) {
        Book mockBook = new Book();
        mockBook.setId(1L);
        mockBook.setTitle("title");
        mockBook.setAuthor("author");
        return mockBook;
    }

    @Override
    public Book saveBook(Book book) {
        Book mockBook = new Book();
        mockBook.setId(1L);
        mockBook.setTitle("title");
        mockBook.setAuthor("author");
        return mockBook;
    }

    @Override
    public boolean deleleBook(Long id) {
        if(id == 1L) return true;
        else return false;
    }
}
