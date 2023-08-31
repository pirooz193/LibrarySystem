package com.mycompany.librarysystem.web.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class BorrowingLimitException extends HttpClientErrorException {

    public BorrowingLimitException(String text) {
        super(HttpStatus.NOT_ACCEPTABLE, text + " - Borrowing limit has exceeded");
    }
}
