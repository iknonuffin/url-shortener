package org.example.urlshortener.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record ShortenRequest(
        @NotBlank(message = "URL is required")
        @URL(message = "Invalid URL")
        String url
) {}
