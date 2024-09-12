package com.kursovaya_2.kirianov.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ListOfQuestionsHasBeenExceeded extends RuntimeException{
    public ListOfQuestionsHasBeenExceeded(String message) {
        super(message);
    }
}
