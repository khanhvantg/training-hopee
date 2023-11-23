package com.example.trainingbackend.service;

import com.example.trainingbackend.exception.BookExistException;
import com.example.trainingbackend.exception.BookNotFoundException;
import com.example.trainingbackend.model.Book;
import com.example.trainingbackend.repository.BookRepository;
import com.example.trainingbackend.repository.BookRepositoryMyBatis;
import com.example.trainingbackend.response.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 書籍サービス。
 *
 * @since   1.5.0
 * @since   Nov 22, 2023
 * @version 1.5.0
 * @author  Van Nguyen
 */
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookRepositoryMyBatis bookRepositoryMyBatis;

    public List<BookResponse> findAll() {
        return  bookRepository.findBook();
    }

    public Book findOneById(Long id) {
        //jpa
        //        return bookRepository.findById(id)
        //                             .orElseThrow(
        //                                 () -> new BookNotFoundException("Not found book with id = " + id)
        //                             );

        //mybatis
        if(bookRepositoryMyBatis.getBookById(id)==null) {
            throw new BookNotFoundException("Not found book with id = " + id);
        }
        return bookRepositoryMyBatis.getBookById(id);
    }

    public List findByTitleLike(String bookTitle) {
        List<Book> list = bookRepository.findByTitleLike(bookTitle);
        if( list.isEmpty()) {
            throw new BookNotFoundException("Not Find Books With Title is " + bookTitle);
        }
        return list;
    }

    public List findByAuthor(String bookAuthor) {
        List<Book> list = bookRepository.findByAuthorLike(bookAuthor);
        if( list.isEmpty()) {
            throw new BookNotFoundException("Not Find Books With Author is " + bookAuthor);
        }
        return list;
    }

    public Book create(Book book) {
        //jpa
        //        if (!bookRepository.findById(book.getId()).isEmpty()) {
        //            throw new BookExistException("Exist a book with id = " + book.getId());
        //        }
        //        if (!bookRepository.findByTitle(book.getTitle()).isEmpty()) {
        //            throw new BookExistException("Exist a book with title is " + book.getTitle());
        //        }
        //        return bookRepository.save(book);

        //mybatis
        if (bookRepositoryMyBatis.getBookById(book.getId()) != null) {
            throw new BookExistException("Exist a book with id = " + book.getId());
        }
        if (bookRepositoryMyBatis.getBookByTitle(book.getTitle()) != null) {
            throw new BookExistException("My Batis Exist a book with title is " + book.getTitle());
        }
        bookRepositoryMyBatis.insertBook(book);
        return bookRepositoryMyBatis.getBookById(book.getId());
    }

    public Book saveUpdate(Book book) {
        bookRepository.findById(book.getId())
                      .orElseThrow(() -> new BookNotFoundException("Not found book with id = "+ book.getId()));
        if (!bookRepository.findByTitle(book.getTitle()).isEmpty()) {
            throw new BookExistException("Exist a book with title is " + book.getTitle());
        }
        return bookRepository.save(book);
    }

    public void deleteById(Long id) {
        //jpa
        //        bookRepository.findById(id)
        //                      .orElseThrow(() -> new BookNotFoundException("Not found book with id = "+ id));
        //        bookRepository.deleteById(id);

        //mybatis
        if (bookRepositoryMyBatis.getBookById(id) == null) {
            throw new BookNotFoundException("Not found book with id = "+ id);
        }
        bookRepositoryMyBatis.deleteById(id);

    }

    @Transactional
    public List<Book> saveUpdatePriceByAuthor(Book book) {
        //jpa
        //        if (bookRepository.findByAuthor(book.getAuthor()).isEmpty()) {
        //            throw new BookExistException("Not found book with Author is " + book.getAuthor());
        //        }
        //        bookRepository.updatePriceBookByAuthor(book.getAuthor(), book.getPrice());
        //        return bookRepository.findByAuthor(book.getAuthor());

        //mybatis
        if (bookRepositoryMyBatis.getBookByAuthor(book).isEmpty()) {
            throw new BookExistException("Not found book with Author is " + book.getAuthor());
        }
        bookRepositoryMyBatis.updateBook(book);
        return bookRepositoryMyBatis.getBookByAuthor(book);

    }
}
