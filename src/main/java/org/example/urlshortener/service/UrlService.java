package org.example.urlshortener.service;

import lombok.AllArgsConstructor;
import org.example.urlshortener.entity.UrlMapping;
import org.example.urlshortener.exception.UrlNotFoundException;
import org.example.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UrlService {
    private final Base62Service base62Service;
    private final UrlRepository urlRepository;

    @Transactional
    public UrlMapping shorten(String url) {
        UrlMapping urlMapping = new UrlMapping(url);

        urlRepository.save(urlMapping);

        urlMapping.setShortCode(
                base62Service.encode(urlMapping.getId())
        );

        return urlMapping;
    }

    public String getOriginalUrl(String shortCode) {
        UrlMapping urlMapping = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new UrlNotFoundException(shortCode));

        return urlMapping.getOriginalUrl();
    }
}
