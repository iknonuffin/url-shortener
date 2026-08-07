package org.example.urlshortener.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.urlshortener.dto.ShortenRequest;
import org.example.urlshortener.dto.ShortenResponse;
import org.example.urlshortener.entity.UrlMapping;
import org.example.urlshortener.service.UrlService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @PostMapping("/shorten")
    public ShortenResponse shorten(@Valid @RequestBody ShortenRequest shortenRequest) {
        UrlMapping result = urlService.shorten(shortenRequest.url());

        String baseUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .build()
                .toUriString();

        return new ShortenResponse(baseUrl + "/" + result.getShortCode());
    }
}