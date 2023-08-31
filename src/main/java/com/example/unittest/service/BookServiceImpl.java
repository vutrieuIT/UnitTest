package com.example.unittest.service;

import com.example.unittest.dto.Book;
import com.example.unittest.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    @Override
    public Book getBook(Long id) {
        return null;
    }

    @Override
    public Book saveBook(Book book) {
        return null;
    }

    @Override
    public boolean deleleBook(Long id) {
        return true;
    }
}
