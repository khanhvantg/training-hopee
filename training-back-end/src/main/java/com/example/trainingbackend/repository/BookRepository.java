package com.example.trainingbackend.repository;

import com.example.trainingbackend.model.Book;
import com.example.trainingbackend.response.BookResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * 書籍リポジトリ。
 *
 * @since   1.5.0
 * @since   Nov 22, 2023
 * @version 1.5.0
 * @author  Van Nguyen
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    //    @Query("SELECT b FROM Book b WHERE b.title = ?1")
    List<Book> findByTitle(String title);

    @Query("SELECT b FROM Book b WHERE b.title LIKE %?1%")
    List<Book> findByTitleLike(String title);

    @Query("SELECT b FROM Book b WHERE b.author LIKE %?1%")
    List<Book> findByAuthorLike(String author);

    @Query("SELECT new com.example.trainingbackend.response.BookResponse(b.author, b.title) FROM Book b")
    List<BookResponse> findBook();

    List<Book> findByAuthor(String author);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("UPDATE Book b SET b.price = :price WHERE b.author = :author")
    void updatePriceBookByAuthor(@Param("author") String author,
                                 @Param("price") BigDecimal price);
}
