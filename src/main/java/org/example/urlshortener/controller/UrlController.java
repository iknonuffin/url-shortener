package org.example.urlshortener.controller;

import org.example.urlshortener.dto.ShortenRequest;
import org.example.urlshortener.dto.ShortenResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlController {

    @PostMapping("/shorten")
    public ShortenResponse shorten(ShortenRequest shortenRequest) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
