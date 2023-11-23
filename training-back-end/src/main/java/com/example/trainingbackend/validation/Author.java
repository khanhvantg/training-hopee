package com.example.trainingbackend.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 著者アノテーション。
 *
 * @since   1.5.0
 * @since   Nov 22, 2023
 * @version 1.5.0
 * @author  Van Nguyen
 */
@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = AuthorValidator.class)
@Documented
public @interface Author {

    String message() default "Author is not allowed.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
