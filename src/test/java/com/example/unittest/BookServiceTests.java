package com.example.unittest;

import com.example.unittest.dto.Book;
import com.example.unittest.entity.BookEntity;
import com.example.unittest.repository.BookRepository;
import com.example.unittest.service.BookServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTests {

    @Mock
    private BookRepository bookRepository;

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    @DisplayName("get book")
    public void getBook(){
        // create mock
        BookEntity mockBookEntity = new BookEntity();
        mockBookEntity.setId(1L);
        mockBookEntity.setTitle("title");
        mockBookEntity.setAuthor("author");

        Book expectBook = new Book();
        expectBook.setId(1L);
        expectBook.setTitle("title");
        expectBook.setAuthor("author");

        // mock repo
        when(bookRepository.getReferenceById(any())).thenReturn(mockBookEntity);

        // test service
        Book book = bookService.getBook(1L);

        assertThat(book).isEqualTo(expectBook);
    }

    @Test
    @DisplayName("save book")
    public void saveBook(){
        // create mock
        BookEntity mockBookEntity = new BookEntity();
        mockBookEntity.setId(1L);
        mockBookEntity.setTitle("title");
        mockBookEntity.setAuthor("author");

        Book expectBook = new Book();
        expectBook.setId(1L);
        expectBook.setTitle("title");
        expectBook.setAuthor("author");

        // mock repo
        when(bookRepository.save(mockBookEntity)).thenReturn(mockBookEntity);

        // test service
        Book book = bookService.saveBook(expectBook);

        assertThat(book).isEqualTo(expectBook);
    }

    @Test
    @DisplayName("delete book")
    public void deleteBook(){
        // create mock
        Long deleteId = 1L;

        // mock repo
//        when(bookRepository.deleteById(1L)).thenReturn(true);

        // test service
        boolean res = bookService.deleleBook(deleteId);

        verify(bookRepository).deleteById(deleteId);
        assertThat(res).isTrue();
    }
}
