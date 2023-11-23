package com.example.trainingbackend.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

/**
 * 著者バリデータ。
 *
 * @since   1.5.0
 * @since   Nov 22, 2023
 * @version 1.5.0
 * @author  Van Nguyen
 */
public class AuthorValidator implements ConstraintValidator<Author, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        List<String> authors = Arrays.asList("Van", "Nguyen");

        return authors.contains(value);
    }
}
