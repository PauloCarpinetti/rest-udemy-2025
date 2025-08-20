package com.github.paulocarpinetti.rest_udemy_2025.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourseNotFoundException extends RuntimeException {

    public ResourseNotFoundException(String message) { super(message); }
}
