package com.mycompany.librarysystem.web.error;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse {
    @JsonProperty(namespace = "title")
    private String title;
    @JsonProperty(namespace = "status_code")
    private Integer statusCode;
    @JsonProperty(namespace = "message")

    private String message;

    public ExceptionResponse(String title, int statusCode, String message) {
        this.title = title;
        this.statusCode = statusCode;
        this.message = "Caused by : " + message;
    }

    public ExceptionResponse() {
        this.title = HttpStatus.INTERNAL_SERVER_ERROR.toString();
        this.statusCode = 500;
        this.message = "Unfortunately action failed";
    }

    public ExceptionResponse(String message) {
        this.message = message;
    }

    public ExceptionResponse(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public ExceptionResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExceptionResponse that = (ExceptionResponse) o;
        return statusCode == that.statusCode && Objects.equals(title, that.title) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, statusCode, message);
    }

    @Override
    public String toString() {
        return "ExceptionResponse{" +
                "title='" + title + '\'' +
                ", statusCode=" + statusCode +
                ", message='" + message + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}