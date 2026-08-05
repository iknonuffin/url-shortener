package org.example.urlshortener.exception;

public record ErrorResponse(
        int status,
        String message
) {}
