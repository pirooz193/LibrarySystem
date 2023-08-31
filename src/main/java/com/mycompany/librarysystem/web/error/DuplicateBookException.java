package com.mycompany.librarysystem.web.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class DuplicateBookException extends HttpClientErrorException {
    public DuplicateBookException(String text) {
        super(HttpStatus.FORBIDDEN, text + " - has exist");
    }
}
