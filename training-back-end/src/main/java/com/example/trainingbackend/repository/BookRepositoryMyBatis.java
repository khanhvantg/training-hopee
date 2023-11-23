package com.example.trainingbackend.repository;

import com.example.trainingbackend.model.Book;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BookRepositoryMyBatis {

    @Select("SELECT * FROM BOOK WHERE id = #{id}") Book getBookById(@Param("id") Long id);

    @Select("SELECT * FROM BOOK WHERE title = #{title}")
    Book getBookByTitle(@Param("title") String title);

    @Insert("Insert into book(id, title, author, price) values (#{id},#{title},#{author},#{price})")
    int insertBook(Book book);

    @Update("Update book set price = #{price} where author = #{Van}")
    int updateBook(Book book);

    @Select("SELECT * FROM BOOK WHERE author = #{author}") List<Book> getBookByAuthor(Book book);

    @Delete("Delete from book where id = #{id}")
    void deleteById(@Param("id") Long id);
}
