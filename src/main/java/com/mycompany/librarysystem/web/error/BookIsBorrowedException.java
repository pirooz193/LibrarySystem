package com.mycompany.librarysystem.web.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class BookIsBorrowedException extends HttpClientErrorException {
    public BookIsBorrowedException(String text) {
        super(HttpStatus.FORBIDDEN, text + " - is borrowed before.");
    }
}
