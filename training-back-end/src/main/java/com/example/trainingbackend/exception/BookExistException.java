package com.example.trainingbackend.exception;

/**
 * 書籍が存在する例外。
 *
 * @since   1.5.0
 * @since   Nov 22, 2023
 * @version 1.5.0
 * @author  Van Nguyen
 */
public class BookExistException extends RuntimeException {

    public BookExistException(String m) {
        super(m);
    }
}
