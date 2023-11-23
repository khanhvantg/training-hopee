package com.example.trainingbackend.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class BookResponse {

    private String author;

    private String title;
}
