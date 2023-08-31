package com.example.unittest.service;

import com.example.unittest.dto.Book;
import com.example.unittest.entity.BookEntity;
import com.example.unittest.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Book getBook(Long id) {
        BookEntity bookEntity = bookRepository.getReferenceById(id);
        Book book = new Book();
        book.setId(bookEntity.getId());
        book.setTitle(bookEntity.getTitle());
        book.setAuthor(bookEntity.getAuthor());
        return book;
    }

    @Override
    public Book saveBook(Book book) {
        BookEntity bookEntity = bookRepository.save(modelMapper.map(book, BookEntity.class));
        return modelMapper.map(bookEntity, Book.class);
    }

    @Override
    public boolean deleleBook(Long id) {
        try {
            bookRepository.deleteById(id);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
