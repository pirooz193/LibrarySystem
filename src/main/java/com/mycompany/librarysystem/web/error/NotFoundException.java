package com.mycompany.librarysystem.web.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class NotFoundException extends HttpClientErrorException {

    public NotFoundException(String text) {
        super(HttpStatus.NOT_FOUND, text + " - Not found");
    }
}
