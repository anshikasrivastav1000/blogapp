package com.blogApp.advice;

import lombok.Builder;

import org.springframework.http.HttpStatus;

import java.util.List;


@Builder
public class ApiError {
    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getSubErrors() {
        return subErrors;
    }

    public void setSubErrors(List<String> subErrors) {
        this.subErrors = subErrors;
    }

    private HttpStatus status;
    private String message;
    private List<String> subErrors;
}

