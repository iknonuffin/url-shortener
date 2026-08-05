package org.example.urlshortener.exception;

import lombok.Getter;

@Getter
public class UrlNotFoundException extends RuntimeException {

    private final String code;

    public UrlNotFoundException(String code) {
        this.code = code;
    }

}
