package com.example.trainingbackend.controller;

import com.example.trainingbackend.model.Book;
import com.example.trainingbackend.repository.BookRepository;
import com.example.trainingbackend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * 書籍API。
 *
 * @since   1.5.0
 * @since   Nov 22, 2023
 * @version 1.5.0
 * @author  Van Nguyen
 */
@RestController
@RequestMapping("/api/books")
@Validated
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List> findAll() {
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findOne(@PathVariable @Min(3) long id) {
        return new ResponseEntity<>(bookService.findOneById(id), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/title/{bookTitle}")
    public ResponseEntity<List> findByTitle(@PathVariable String bookTitle) {
        return new ResponseEntity<>(bookService.findByTitleLike(bookTitle), HttpStatus.OK);
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List> findByAuthor(@PathVariable String author) {
        return new ResponseEntity<>(bookService.findByAuthor(author), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> create(@Validated @RequestBody Book book) {
        return new ResponseEntity<>(bookService.create(book), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Book> updateBook(@Validated @RequestBody Book book) {
        return new ResponseEntity<>(bookService.saveUpdate(book), HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/customUpdate")
    public ResponseEntity<List> updateBookByAuthor(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.saveUpdatePriceByAuthor(book), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.deleteById(id);
    }
}
