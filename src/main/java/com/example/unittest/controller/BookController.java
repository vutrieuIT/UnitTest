package com.example.unittest.controller;

import com.example.unittest.service.BookServiceImpl;
import com.example.unittest.dto.Book;
import com.example.unittest.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookServiceImpl bookService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getBook(@PathVariable("id") Long id){
        Book book = bookService.getBook(id);
        return ResponseEntity.ok(book);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveBook(@RequestBody Book book){
        Message message = new Message();
        if(book.getId() == null){
            message.setMessage("Request Body : book null");
            return ResponseEntity.badRequest().body(message);
        }
        Book responseBook = bookService.saveBook(book);
        return ResponseEntity.ok(responseBook);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id){
        Message message = new Message();
        if(bookService.deleleBook(id)) {
            message.setMessage("done");
            return ResponseEntity.ok(message);
        }
        else {
            message.setMessage("fail");
            return ResponseEntity.badRequest().body(message);
        }
    }
}
