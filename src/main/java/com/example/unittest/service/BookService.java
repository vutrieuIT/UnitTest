package com.example.unittest.service;

import com.example.unittest.dto.Book;

public interface BookService {
    Book getBook(Long id);
    Book saveBook(Book book);
    boolean deleleBook(Long id);
}
