package com.example.trainingbackend.model;

import com.example.trainingbackend.validation.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * 書籍モデル。
 *
 * @since   1.5.0
 * @since   Nov 22, 2023
 * @version 1.5.0
 * @author  Van Nguyen
 */
@Data
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    /** ID。 */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /** タイトル。 */
    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Please provide a title!")
    private String title;

    /** 著者。 */
    @Author
    @Column(nullable = false)
    @NotEmpty(message = "Please provide a author!")
    private String author;

    /** 値段。 */
    @Column(nullable= false)
    @NotNull(message = "Please provide a price!")
    @DecimalMin("1.00")
    private BigDecimal price;
}
