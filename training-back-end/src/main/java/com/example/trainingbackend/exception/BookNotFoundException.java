package com.example.trainingbackend.exception;

/**
 * 書籍見つからない例外。
 *
 * @since   1.5.0
 * @since   Nov 22, 2023
 * @version 1.5.0
 * @author  Van Nguyen
 */
public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String m) {
        super(m);
    }
}
